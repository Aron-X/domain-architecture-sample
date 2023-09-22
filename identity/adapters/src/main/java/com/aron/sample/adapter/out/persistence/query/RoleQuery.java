package com.aron.sample.adapter.out.persistence.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class RoleQuery {

  private String roleName;
  private Long roleId;
  private List<Long> roleIds;
  private List<String> roleNames;


  public boolean isEmpty() {
    return StringUtils.isEmpty(roleName)
        && roleId == null
        && CollectionUtils.isEmpty(roleIds)
        && CollectionUtils.isEmpty(roleNames);
  }

}
