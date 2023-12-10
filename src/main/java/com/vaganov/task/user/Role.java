package com.vaganov.task.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vaganov.task.user.Permission.EXECUTOR_CREATE;
import static com.vaganov.task.user.Permission.EXECUTOR_DELETE;
import static com.vaganov.task.user.Permission.EXECUTOR_READ;
import static com.vaganov.task.user.Permission.EXECUTOR_UPDATE;
import static com.vaganov.task.user.Permission.USER_CREATE;
import static com.vaganov.task.user.Permission.USER_DELETE;
import static com.vaganov.task.user.Permission.USER_READ;
import static com.vaganov.task.user.Permission.USER_UPDATE;


@RequiredArgsConstructor
public enum Role {

  USER(
          Set.of(
                  USER_READ,
                  USER_UPDATE,
                  USER_DELETE,
                  USER_CREATE,
                  EXECUTOR_READ,
                  EXECUTOR_UPDATE,
                  EXECUTOR_DELETE,
                  EXECUTOR_CREATE
          )
  ),
  EXECUTOR(
          Set.of(
              EXECUTOR_READ,
              EXECUTOR_UPDATE,
              EXECUTOR_DELETE,
              EXECUTOR_CREATE
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
