package com.sprint.mission.discodeit.dto.channelDto;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


public record ChannelResponse(
    UUID id,
    ChannelType type,
    String channelName,
    String description,
    Instant createdAt,
    Instant updatedAt,
    Instant lastMessageAt,
    List<UUID> userIds
) {

    public static ChannelResponse from(
        Channel channel,
        Instant recentMessageAt,
        List<UUID> userIds
    ) {
        return new ChannelResponse(
            channel.getId(),
            channel.getType(),
            channel.getChannelName(),
            channel.getDescription(),
            channel.getCreatedAt(),
            channel.getUpdatedAt(),
            recentMessageAt,
            userIds
        );
    }
}
