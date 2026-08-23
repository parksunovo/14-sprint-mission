package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.dto.messageDto.MessageCreateRequest;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private final UUID id;
    private final Instant createdAt;
    private final UUID authorId;
    private final UUID channelId;
    private List<UUID> attachmentsIds;
    private Instant updatedAt;
    private String content;


    private Message(String content, UUID userId, UUID channelId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.content = content;
        this.authorId = userId;
        this.channelId = channelId;
    }

    public static Message create(MessageCreateRequest messageCreateRequest) {
        return new Message(messageCreateRequest.content(), messageCreateRequest.authorId(),
            messageCreateRequest.channelId());
    }


    public Message update(String updateContent) {
        this.content = updateContent;
        this.updatedAt = Instant.now();
        return this;
    }

    @Override
    public String toString() {
        return "내용 : " + this.content;
    }

}
