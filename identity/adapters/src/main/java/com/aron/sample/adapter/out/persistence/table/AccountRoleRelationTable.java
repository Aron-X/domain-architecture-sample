package com.aron.sample.adapter.out.persistence.table;

import com.aron.sample.domain.account.model.vo.AccountId;
import com.aron.sample.infrastructre.utils.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRoleRelationTable {

  private Long id;
  private Long accountId;
  private Long roleId;
  private LocalDateTime createdAt;

  public static AccountRoleRelationTable from(AccountId accountId, Long roleId) {
    return AccountRoleRelationTable.builder()
        .id(IdGenerator.newId())
        .accountId(accountId.getValue())
        .roleId(roleId)
        .createdAt(LocalDateTime.now())
        .build();
  }

}
