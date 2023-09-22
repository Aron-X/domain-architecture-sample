package com.aron.sample.domain.account.model.vo;

import com.aron.sample.domain.account.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AssignRoleInfo {

  private String networkId;
  private List<Role> roles;
}
