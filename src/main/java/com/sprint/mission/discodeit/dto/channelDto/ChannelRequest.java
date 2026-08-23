package com.sprint.mission.discodeit.dto.channelDto;

import com.sprint.mission.discodeit.entity.ChannelType;
import java.time.Instant;


public record ChannelRequest(
    Instant updateAt,
    String channelName,
    String description,
    ChannelType type
) {

}
