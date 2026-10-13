package com.sprint.mission.discodeit.user.application.required;

import com.sprint.mission.discodeit.user.domain.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

  User save(User user);

  void delete(User user);

  List<User> findAll();

  Optional<User> findById(UUID userId);

  List<User> findAllById(List<UUID> userIds);

  Optional<User> findByUsername(String username);

}
