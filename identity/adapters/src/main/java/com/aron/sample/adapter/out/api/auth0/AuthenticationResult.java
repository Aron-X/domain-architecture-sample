package com.aron.sample.adapter.out.api.auth0;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
class AuthenticationResult implements Serializable {

  private static final long serialVersionUID = -4995259617554335922L;

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("id_token")
  private String idToken;
  @JsonProperty("scope")
  private String scope;
  @JsonProperty("expires_in")
  private int expiresIn;
  @JsonProperty("token_type")
  private String tokenType;

}
