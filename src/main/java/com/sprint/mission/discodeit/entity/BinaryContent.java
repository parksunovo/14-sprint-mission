package com.sprint.mission.discodeit.entity;

import java.time.Instant;
import java.util.UUID;

public class BinaryContent {

    private UUID uuid;
    private final Instant createdAt = Instant.now();
    private Instant updatedAt;

}
