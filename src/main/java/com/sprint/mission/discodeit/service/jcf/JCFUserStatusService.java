package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.dto.userStatusDto.UserStatusRequest;
import com.sprint.mission.discodeit.dto.userStatusDto.UserStatusResponse;
import com.sprint.mission.discodeit.dto.userStatusDto.UserStatusUpdateRequest;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.exception.DiscodeitRuntimeException;
import com.sprint.mission.discodeit.exception.ExceptionType;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.service.UserStatusService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JCFUserStatusService implements UserStatusService {

    private final UserStatusRepository userStatusRepository;
    private final UserRepository userRepository;

    @Override
    public UserStatusResponse create(UserStatusRequest request) {
        UUID userUuid = request.userUuid();
        if (!userRepository.existsById(userUuid)) {
            throw new DiscodeitRuntimeException(ExceptionType.USER_NOT_FOUND);
        }
        UserStatus userStatus = new UserStatus(userUuid, request.activityAt());
        userStatusRepository.save(userStatus);
        return UserStatusResponse.from(userStatus);
    }

    @Override
    public UserStatusResponse findUserStatusByUserId(UUID userId) {
        UserStatus userStatus = userStatusRepository.findByUserId(userId)
            .orElseThrow(() -> new DiscodeitRuntimeException(ExceptionType.USER_NOT_FOUND));
        return UserStatusResponse.from(userStatus);
    }

    @Override
    public List<UserStatusResponse> findAll() {
        return userStatusRepository.readAll().stream().map(UserStatusResponse::from).toList();
    }

    @Override
    public void delete(UserStatusRequest request) {
        userRepository.delete(request.uuid());
    }

    @Override
    public UserStatusResponse update(UUID userStatusId, UserStatusUpdateRequest request) {
        UserStatus userStatus = userStatusRepository.read(userStatusId)
            .orElseThrow(() -> new DiscodeitRuntimeException(ExceptionType.INVALID_INFO));
        UserStatus updatedUserStatus = userStatus.refresh(request.newLastActiveAt());
        userStatusRepository.save(updatedUserStatus);
        return UserStatusResponse.from(updatedUserStatus);
    }

    @Override
    public UserStatusResponse updateByUserId(UUID userId, UserStatusUpdateRequest request) {
        UserStatus userStatus = userStatusRepository.findByUserId(userId)
            .orElseThrow(() -> new DiscodeitRuntimeException(ExceptionType.USER_NOT_FOUND));
        UserStatus updatedUserStatus = userStatus.refresh(request.newLastActiveAt());
        userStatusRepository.save(updatedUserStatus);
        return UserStatusResponse.from(updatedUserStatus);
    }
}
