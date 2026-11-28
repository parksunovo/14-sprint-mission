package com.sprint.mission.discodeit.channel.adapter.security;

import com.sprint.mission.discodeit.channel.application.required.RoleChecker;
import com.sprint.mission.discodeit.user.domain.Role;
import java.util.List;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authorization.AuthoritiesAuthorizationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityRoleChecker implements RoleChecker {

  private final AuthoritiesAuthorizationManager manager =
      new AuthoritiesAuthorizationManager();

  public SecurityRoleChecker(RoleHierarchy roleHierarchy) {
    this.manager.setRoleHierarchy(roleHierarchy);
  }

  @Override
  public void checkRole(Role role) {
    String authority = "ROLE_" + role.name();

    manager.verify(
        SecurityContextHolder.getContext()::getAuthentication,
        List.of(authority));
  }
}
