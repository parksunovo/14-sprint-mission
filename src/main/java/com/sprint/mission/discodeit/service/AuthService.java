package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.userDto.UserRequest;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.exception.DiscodeitRuntimeException;
import com.sprint.mission.discodeit.exception.ExceptionType;
import com.sprint.mission.discodeit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    public User login(UserRequest userRequest) {
        String name = userRequest.name();
        String password = userRequest.password();

        User user = userRepository.readAll().stream()
            .filter(m -> m.getName().equals(name))
            .findAny()
            .orElseThrow(() -> new DiscodeitRuntimeException(ExceptionType.USER_NOT_FOUND));
        if (!user.getPassword().equals(password)) {
            throw new DiscodeitRuntimeException(ExceptionType.INVALID_INFO);
        }
        return user;
    }
}
