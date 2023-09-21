package com.aron.sample.domain.account.model.vo;

import com.aron.sample.utils.IdGenerator;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GroupId {
  private Long value;

  public static GroupId generateId() {
    return new GroupId(IdGenerator.newId());
  }
}
