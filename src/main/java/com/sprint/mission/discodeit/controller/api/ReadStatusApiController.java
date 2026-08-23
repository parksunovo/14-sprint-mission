package com.sprint.mission.discodeit.controller.api;

import com.sprint.mission.discodeit.dto.readStatus.ReadStatusCreateRequest;
import com.sprint.mission.discodeit.dto.readStatus.ReadStatusResponse;
import com.sprint.mission.discodeit.dto.readStatus.ReadStausRequest;
import com.sprint.mission.discodeit.service.ReadStatusService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/readStatuses")
@RequiredArgsConstructor
public class ReadStatusApiController {

    private final ReadStatusService readStatusService;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<ReadStatusResponse> createReadStausByChannel(
        @RequestBody ReadStatusCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(readStatusService.create(request));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{readStatusId}")
    public ResponseEntity<ReadStatusResponse> updateReadStatusByChannel(
        @PathVariable UUID readStatusId, @RequestBody ReadStausRequest request) {

        ReadStatusResponse update = readStatusService.update(request, readStatusId);

        return ResponseEntity.ok(update);


    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<ReadStatusResponse>> getReadStatusByUser(
        @RequestParam(name = "userId") UUID userId) {
        return ResponseEntity.ok(readStatusService.findByUserId(userId));
    }
}
