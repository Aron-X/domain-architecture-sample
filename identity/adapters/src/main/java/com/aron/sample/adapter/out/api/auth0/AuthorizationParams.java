package com.aron.sample.adapter.out.api.auth0;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
class AuthorizationParams {

  private String code;
  private String clientId;
  private String clientSecret;
  private String redirectUri;
  private String domain;
}
