package com.aron.sample.adapter.out.persistence.table;

import com.aron.sample.domain.account.model.Group;
import com.aron.sample.domain.account.model.vo.GroupId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupTable {

  private Long id;

  private String groupName;

  private String displayText;

  private String type;

  private String url;

  private String image;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public Group toModel() {
    return Group.builder()
        .id(new GroupId(id))
        .groupName(groupName)
        .displayText(displayText)
        .type(type)
        .url(url)
        .image(image)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .build();
  }

}
