package com.aron.sample.adapter.out.api.auth0;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class Auth0Configuration {

  @Value("${identity.auth.client-id:null}")
  private String clientId;
  @Value("${identity.auth.client-secret:null}")
  private String clientSecret;
  @Value("${identity.auth.grant-type:null}")
  private String grantType;
  @Value("${identity.auth.redirect-uri:null}")
  private String redirectUri;
  @Value("${identity.auth.domain:null}")
  private String domain;
  @Value("${identity.auth.public-key:null}")
  private String publicKey;
}
