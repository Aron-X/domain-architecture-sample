package com.aron.sample.domain.account.model;

import com.aron.sample.domain.account.model.vo.GroupId;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Group {

  private GroupId id;

  private String groupName;

  private String displayText;

  private String type;

  private String url;

  private String image;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private List<Role> availableRoles;

  public void setAvailableRoles(
      List<Role> availableRoles) {
    this.availableRoles = availableRoles;
  }
}
