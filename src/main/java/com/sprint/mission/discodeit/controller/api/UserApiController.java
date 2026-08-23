package com.sprint.mission.discodeit.controller.api;

import com.sprint.mission.discodeit.dto.userDto.UserCreateRequest;
import com.sprint.mission.discodeit.dto.userDto.UserResponse;
import com.sprint.mission.discodeit.dto.userDto.UserUpdateRequest;
import com.sprint.mission.discodeit.dto.userStatusDto.UserStatusResponse;
import com.sprint.mission.discodeit.dto.userStatusDto.UserStatusUpdateRequest;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.UserStatusService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;
    private final UserStatusService userStatusService;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> userList = userService.readAll();
        return ResponseEntity.ok(userList);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{userId}/userStatus")
    public ResponseEntity<UserStatusResponse> updateUserStatusByUserId(@PathVariable UUID userId,
        @RequestBody UserStatusUpdateRequest request) {

        UserStatusResponse response = userStatusService.updateByUserId(userId, request);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<UserResponse> register(@RequestBody UserCreateRequest request) {
        UserResponse response = userService.create(request);
        return ResponseEntity.status(201).body(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        UserResponse response = userService.findById(id);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteById(@PathVariable UUID userId) {
        userService.delete(userId);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID userId,
        @RequestBody UserUpdateRequest request) {

        UserResponse response = userService.update(userId, request);
        return ResponseEntity.ok(response);
    }

}
