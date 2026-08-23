package com.sprint.mission.discodeit.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ReadStatus {

    private final UUID uuid;
    private final UUID userId;
    private final UUID channelId;
    private final Instant createdAt;
    private Instant recentReadAt;

    private ReadStatus(UUID userId, UUID channelId) {
        this.uuid = UUID.randomUUID();
        this.userId = userId;
        this.channelId = channelId;
        this.createdAt = Instant.now();
    }

    public ReadStatus update(Instant recentReadAt) {
        this.recentReadAt = recentReadAt;
        return this;
    }

    public static ReadStatus create(UUID userId, UUID channelId) {
        return new ReadStatus(userId, channelId);
    }
}
