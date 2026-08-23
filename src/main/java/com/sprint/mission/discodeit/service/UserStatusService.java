package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.userStatusDto.UserStatusRequest;
import com.sprint.mission.discodeit.dto.userStatusDto.UserStatusResponse;
import com.sprint.mission.discodeit.dto.userStatusDto.UserStatusUpdateRequest;
import java.util.List;
import java.util.UUID;

public interface UserStatusService {

    UserStatusResponse create(UserStatusRequest request);

    UserStatusResponse findUserStatusByUserId(UUID userId);

    List<UserStatusResponse> findAll();

    void delete(UserStatusRequest request);

    UserStatusResponse update(UUID userStatusId, UserStatusUpdateRequest request);

    UserStatusResponse updateByUserId(UUID userId, UserStatusUpdateRequest request);
}
