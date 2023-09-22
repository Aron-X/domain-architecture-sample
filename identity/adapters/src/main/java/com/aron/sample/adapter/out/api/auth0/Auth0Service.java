package com.aron.sample.adapter.out.api.auth0;

import com.aron.sample.infrastructre.exception.BizError;
import com.aron.sample.infrastructre.exception.BizException;
import com.aron.sample.infrastructre.support.SuppressObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
class Auth0Service {

  private final RestTemplate restTemplate;
  private final SuppressObjectMapper suppressObjectMapper;
  private final Auth0Configuration auth0Configuration;

  public String getIdToken(String code) {
    log.info("start invoke auth0 token by code");
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(getRequestParams(code), getHttpHeaders());
    try {
      ResponseEntity<String> response = restTemplate
          .postForEntity(buildUrl(auth0Configuration.getDomain()), request, String.class);
      log.info("got auth0 response, response status is: {}", response.getStatusCode());
      if (response.getStatusCode() == HttpStatus.OK) {
        AuthenticationResult result = suppressObjectMapper.readValue(response.getBody(), AuthenticationResult.class);
        log.info("response id token is: {}", result.getIdToken());
        return result.getIdToken();
      }
    } catch (RestClientException e) {
      log.error("invoke auth0 failed", e);
    }
    throw new BizException(BizError.INVALID_CODE);
  }

  public String getPublicKey() {
    return auth0Configuration.getPublicKey();
  }

  public String getClientId() {
    return auth0Configuration.getClientId();
  }

  private MultiValueMap<String, String> getRequestParams(String code) {
    MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    requestParams.add("grant_type", "authorization_code");
    requestParams.add("client_id", auth0Configuration.getClientId());
    requestParams.add("client_secret", auth0Configuration.getClientSecret());
    requestParams.add("code", code);
    requestParams.add("redirect_uri", auth0Configuration.getRedirectUri());
    return requestParams;
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    return headers;
  }

  private String buildUrl(String domain) {
    return String.format("https://%s/oauth/token", domain);
  }

}
