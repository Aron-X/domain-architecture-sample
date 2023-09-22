package com.aron.sample.adapter.out.persistence.table;

import com.aron.sample.domain.account.model.Account;
import com.aron.sample.domain.account.model.vo.AccountId;
import com.aron.sample.domain.account.model.vo.AccountState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTable {

  private Long id;
  private String name;
  private String firstName;
  private String lastName;
  private String networkId;
  private String email;
  private AccountState state;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static AccountTable from(Account account) {
    return AccountTable.builder()
        .id(account.getId().getValue())
        .name(account.getName())
        .firstName(account.getFirstName())
        .lastName(account.getLastName())
        .networkId(account.getNetworkId())
        .email(account.getEmail())
        .state(account.getState())
        .createdAt(account.getCreatedAt())
        .updatedAt(account.getUpdatedAt())
        .build();
  }

  public Account toModel() {
    return Account.builder()
        .id(new AccountId(getId()))
        .name(getName())
        .firstName(getFirstName())
        .lastName(getLastName())
        .networkId(getNetworkId())
        .email(getEmail())
        .state(getState())
        .createdAt(getCreatedAt())
        .updatedAt(getUpdatedAt())
        .build();
  }

}
