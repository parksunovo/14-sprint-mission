package com.sprint.mission.discodeit.entity;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserStatus {

    private final UUID uuid;
    private final UUID userUuid;
    private boolean isOnline;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant lastActiveAt;

    public UserStatus(UUID userUuid, Instant lastActiveAt) {
        this.uuid = UUID.randomUUID();
        this.userUuid = userUuid;
        this.createdAt = Instant.now();
        this.lastActiveAt = lastActiveAt;
    }

    public static UserStatus create(User user) {
        return new UserStatus(user.getId(), user.getCreatedAt());
    }


    public UserStatus refresh(Instant instant) {
        this.lastActiveAt = instant;
        this.updatedAt = Instant.now();
        this.isOnline = isOnline();
        return this;
    }

    public Boolean isOnline() {
        Instant instant = Instant.now().minus(Duration.ofMinutes(5));

        return lastActiveAt.isAfter(instant);
    }

}
