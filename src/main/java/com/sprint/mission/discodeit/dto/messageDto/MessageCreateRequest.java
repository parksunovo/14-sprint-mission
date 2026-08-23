package com.sprint.mission.discodeit.dto.messageDto;

import java.util.UUID;

public record MessageCreateRequest(
    UUID authorId,
    UUID channelId,
    String content
) {

}
