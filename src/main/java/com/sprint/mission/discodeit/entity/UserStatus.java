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
    private Instant activityAt;

    public UserStatus(UUID userUuid, Instant activityAt) {
        this.uuid = UUID.randomUUID();
        this.userUuid = userUuid;
        this.createdAt = Instant.now();
        this.activityAt = activityAt;
        this.isOnline = isOnline();
    }

    public static UserStatus create(User user) {
        return new UserStatus(user.getId(), user.getCreatedAt());
    }


    public UserStatus refresh(Instant instant) {
        this.activityAt = instant;
        this.isOnline = isOnline();
        return this;
    }

    public Boolean isOnline() {
        Instant instant = Instant.now().minus(Duration.ofMinutes(5));

        return activityAt.isAfter(instant);
    }

}
