package com.aron.sample.domain.account.model.vo;

import com.aron.sample.utils.IdGenerator;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RoleId {

  private Long value;

  public static RoleId generateId() {
    return new RoleId(IdGenerator.newId());
  }
}
