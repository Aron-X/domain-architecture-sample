package com.aron.sample.domain.account.model.vo;

import com.aron.sample.infrastructre.utils.IdGenerator;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AccountId {

  private Long value;

  public static AccountId generateId() {
    return new AccountId(IdGenerator.newId());
  }
}
