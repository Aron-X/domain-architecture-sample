package com.arom.sample.security;

import com.aron.sample.domain.token.AccountMeta;
import com.aron.sample.exception.BizError;
import com.aron.sample.exception.BizException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public final class SecurityContext {

  private SecurityContext() {

  }

  public static AccountMeta getCurrentAccountMeta() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
        .map(org.springframework.security.core.context.SecurityContext::getAuthentication)
        .map(Authentication::getPrincipal)
        .map(p -> (AccountMeta) p)
        .orElseThrow(() -> new BizException(BizError.UNAUTHORIZED));
  }
}
