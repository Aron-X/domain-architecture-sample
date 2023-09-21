package com.aron.sample.port.dto;

import com.aron.sample.domain.account.model.Group;
import com.aron.sample.domain.token.AccountMetaId;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountMetaDTO {

    private AccountMetaId id;
    private String name;
    private String firstName;
    private String lastName;
    private String networkId;
    private String email;
    private List<RoleInfoDTO> roles;

    @Builder
    @Data
    public static class RoleInfoDTO {

        private Group group;
        private List<String> values;

        public static RoleInfoDTO from(Group group, List<String> values) {
            return RoleInfoDTO.builder().group(group).values(values).build();
        }
    }
}
