package com.arom.sample.security.filter;

import com.arom.sample.security.AuthenticationUtils;
import com.aron.sample.cache.RedisNamespace;
import com.aron.sample.cache.TokenType;
import com.aron.sample.port.dto.AccountMetaDTO;
import com.aron.sample.port.out.TokenRefreshPort;
import com.aron.sample.port.out.TokenRetrievePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserTokenFilter extends AbstractAuthenticationProcessingFilter {

  private final TokenRefreshPort tokenRefreshPort;
  private final TokenRetrievePort tokenRetrievePort;

  public UserTokenFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                         TokenRefreshPort tokenRefreshPort,
                         TokenRetrievePort tokenRetrievePort) {
    super(requiresAuthenticationRequestMatcher);
    this.tokenRefreshPort = tokenRefreshPort;
    this.tokenRetrievePort = tokenRetrievePort;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    if (!requiresAuthentication(request, response)) {
      chain.doFilter(request, response);
      return;
    }
    Authentication authentication = attemptAuthentication(request, response);
    if (authentication != null) {
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(req, res);
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
    return getAuthenticationBy(headerToken);
  }

  private Authentication getAuthenticationBy(String tokenKey) {
    String token = AuthenticationUtils.tokenExtract(tokenKey, TokenType.BEARER);
    AccountMetaDTO accountMeta = tokenRetrievePort.getBy(RedisNamespace.USER_NAME_SPACE, token);
    if (accountMeta != null) {
      tokenRefreshPort.refresh(RedisNamespace.USER_NAME_SPACE, token);
      return AuthenticationUtils.create(accountMeta);
    }
    return null;
  }

}
