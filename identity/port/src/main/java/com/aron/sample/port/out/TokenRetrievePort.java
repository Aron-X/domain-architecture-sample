package com.aron.sample.port.out;


import com.aron.sample.port.dto.AccountMetaDTO;

public interface TokenRetrievePort {

  /**
   * xxxxx
   * @param userNameSpace
   * @param token
   * @return
   */
  AccountMetaDTO getBy(String userNameSpace, String token);
}

