package com.sprint.mission.discodeit.auth.adapter.security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.sprint.mission.discodeit.common.exception.DiscodeitRuntimeException;
import com.sprint.mission.discodeit.common.exception.ErrorCode;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  public enum TokenUse {
    ACCESS, REFRESH
  }

  private final MACSigner signer;
  private final MACVerifier verifier;

  public JwtTokenProvider(@Value("${discodeit.jwt.secret}") String secret) throws JOSEException {
    byte[] key = Base64.getDecoder().decode(secret);
    this.signer = new MACSigner(key);
    this.verifier = new MACVerifier(key);
  }

  public String issue(UUID userId, TokenUse use) {
    Instant now = Instant.now();
    Duration ttl = switch (use) {
      case ACCESS -> Duration.ofMinutes(15);
      case REFRESH -> Duration.ofDays(7);
    };

    JWTClaimsSet claims = new JWTClaimsSet.Builder()
        .subject(userId.toString())
        .claim("token_use", use.name())
        .issueTime(Date.from(now))
        .expirationTime(Date.from(now.plus(ttl)))
        .jwtID(UUID.randomUUID().toString())
        .build();

    SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
    try {
      jwt.sign(signer);
      return jwt.serialize();
    } catch (JOSEException e) {
      throw new DiscodeitRuntimeException(ErrorCode.SIGN_FAILED, e);
    }
  }

  public UUID verify(String token, TokenUse expectedUse) {
    try {
      SignedJWT jwt = SignedJWT.parse(token);
      if (!JWSAlgorithm.HS256.equals(jwt.getHeader().getAlgorithm()) ||
          !jwt.verify(verifier)) {
        throw new DiscodeitRuntimeException(ErrorCode.INVALID_JWT);
      }

      JWTClaimsSet claims = jwt.getJWTClaimsSet();
      Date expires = claims.getExpirationTime();
      if (expires == null || !expires.toInstant().isAfter(Instant.now())
          || !expectedUse.name().equals(claims.getStringClaim("token_use"))
          || claims.getSubject() == null) {
        throw new DiscodeitRuntimeException(ErrorCode.INVALID_JWT);
      }
      return UUID.fromString(claims.getSubject());
    } catch (ParseException | JOSEException | IllegalArgumentException e) {
      throw new DiscodeitRuntimeException(ErrorCode.INVALID_JWT);
    }
  }
}
