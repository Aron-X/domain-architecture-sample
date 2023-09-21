package com.aron.sample.domain.account.model;

import com.aron.sample.domain.account.model.vo.GroupId;
import com.aron.sample.domain.account.model.vo.RoleId;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

  private RoleId id;
  private String name;
  private GroupId group;
  private LocalDateTime updateAt;
  private LocalDateTime createdAt;
}
