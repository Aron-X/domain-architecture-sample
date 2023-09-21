package com.aron.sample.application.factory;

import com.aron.sample.domain.account.model.Account;
import com.aron.sample.domain.account.model.vo.AccountId;
import com.aron.sample.domain.account.model.vo.AccountState;
import com.aron.sample.port.out.GetSSOInfoPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class AccountFactory {

    public Account createAccountFromSSO(GetSSOInfoPort.AuthAccount authAccount){
        return Account.builder()
                .id(AccountId.generateId())
                .email(authAccount.getEmail())
                .networkId(authAccount.getNetworkId())
                .name(authAccount.getName())
                .firstName(authAccount.getFirstname())
                .lastName(authAccount.getLastname())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .roles(Collections.emptyList())
                .state(AccountState.VALIDATED)
                .build();
    }

}
