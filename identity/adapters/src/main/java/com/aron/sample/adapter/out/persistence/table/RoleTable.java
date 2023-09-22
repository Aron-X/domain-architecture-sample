package com.aron.sample.adapter.out.persistence.table;

import com.aron.sample.domain.account.model.Role;
import com.aron.sample.domain.account.model.vo.GroupId;
import com.aron.sample.domain.account.model.vo.RoleId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleTable {

  private Long id;
  private String name;
  private Long group;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Role toModel() {
    return Role.builder()
        .id(new RoleId(getId()))
        .name(getName())
        .group(new GroupId(getGroup()))
        .createdAt(getCreatedAt())
        .updateAt(getUpdatedAt())
        .build();
  }
}
