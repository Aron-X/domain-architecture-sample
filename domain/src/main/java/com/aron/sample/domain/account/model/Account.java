package com.aron.sample.domain.account.model;

import com.aron.sample.domain.account.model.vo.AccountId;
import com.aron.sample.domain.account.model.vo.AccountState;
import com.aron.sample.domain.account.model.vo.RoleId;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Account {

  private AccountId id;
  private String name;
  private String firstName;
  private String lastName;
  private String networkId;
  private String email;
  private List<RoleId> roles;
  private AccountState state;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static Account create(String networkId, List<RoleId> roleIds) {
    return Account.builder()
        .id(AccountId.generateId())
        .networkId(networkId)
        .roles(roleIds)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .state(AccountState.INITIATED)
        .build();
  }

  public void assignRoles(List<RoleId> roles) {
    setRoles(roles);
  }

  public void addRoles(List<RoleId> roles) {
    Objects.requireNonNull(roles);
    if (getRoles() == null) {
      setRoles(new ArrayList<>(roles));
    }
  }

  public boolean isInitiated() {
    return getState() == AccountState.INITIATED;
  }
}
