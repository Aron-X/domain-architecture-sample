package com.aron.sample.domain.token;

import com.aron.sample.domain.account.model.Account;
import com.aron.sample.domain.account.model.Group;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountMeta {

  private AccountMetaId id;
  private String name;
  private String firstName;
  private String lastName;
  private String networkId;
  private String email;
  private List<RoleInfo> roles;

  public static AccountMeta create(Account account, List<RoleInfo> roles) {
    return AccountMeta.builder()
        .id(new AccountMetaId(account.getId().getValue()))
        .name(account.getName())
        .firstName(account.getFirstName())
        .lastName(account.getLastName())
        .networkId(account.getNetworkId())
        .email(account.getEmail())
        .roles(roles)
        .build();
  }

  @Builder
  @Data
  public static class RoleInfo {

    private Group group;
    private List<String> values;

    public static RoleInfo from(Group group, List<String> values) {
      return RoleInfo.builder().group(group).values(values).build();
    }

  }
}
