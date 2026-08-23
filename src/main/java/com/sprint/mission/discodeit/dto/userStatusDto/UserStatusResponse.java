package com.sprint.mission.discodeit.dto.userStatusDto;

import com.sprint.mission.discodeit.entity.UserStatus;
import java.time.Instant;
import java.util.UUID;

public record UserStatusResponse(
    UUID id,
    Instant createdAt,
    Instant updatedAt,
    UUID userId,
    Instant lastActiveAt,
    boolean isOnline
) {

    public static UserStatusResponse from(UserStatus userStatus) {
        return new UserStatusResponse(userStatus.getUuid(), userStatus.getCreatedAt(),
            userStatus.getUpdatedAt(), userStatus.getUserUuid(), userStatus.getLastActiveAt(),
            userStatus.isOnline());
    }
}
