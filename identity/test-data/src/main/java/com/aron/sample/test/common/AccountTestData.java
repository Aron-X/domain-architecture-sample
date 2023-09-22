package com.aron.sample.test.common;

import com.aron.sample.domain.account.model.Account;

public class AccountTestData {

    public static Account defaultAccount(){
        return Account.builder()
                .name("test")
                .networkId("test-id")
                .build();
    }
}
