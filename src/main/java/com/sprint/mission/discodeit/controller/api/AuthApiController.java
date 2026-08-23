package com.sprint.mission.discodeit.controller.api;

import com.sprint.mission.discodeit.dto.userDto.UserRequest;
import com.sprint.mission.discodeit.dto.userDto.UserResponse;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthService authService;

    @RequestMapping(method = RequestMethod.POST, value = "/api/auth/login")
    public ResponseEntity<User> login(@RequestBody UserRequest request) {
        User response = authService.login(request);
        return ResponseEntity.ok(response);
    }

}
