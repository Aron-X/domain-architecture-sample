package com.arom.sample.security;

import com.aron.sample.infrastructre.cache.TokenType;
import com.aron.sample.port.dto.AccountMetaDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public final class AuthenticationUtils {

  public static final String ROLE_MODULE = "ROLE_MODULE";

  private AuthenticationUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static Authentication create(AccountMetaDTO accountMeta) {
    Optional<AccountMetaDTO.RoleInfoDTO> role = accountMeta.getRoles().stream()
        .filter(roleInfo -> roleInfo.getGroup().getGroupName().equals(ROLE_MODULE))
        .findFirst();

    List<GrantedAuthority> authorities;
    if (role.isPresent()) {
      authorities = createAuthorities(role.get().getValues());
    } else {
      authorities = createAuthorities(emptyList());
    }
    return new PreAuthenticatedAuthenticationToken(accountMeta, StringUtils.EMPTY, authorities);
  }

  private static List<GrantedAuthority> createAuthorities(List<String> roles) {
    if (CollectionUtils.isEmpty(roles)) {
      return emptyList();
    }
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  public static String tokenExtract(String header, TokenType tokenType) {
    if (StringUtils.isBlank(header)) {
      return StringUtils.EMPTY;
    }
    return StringUtils.substringAfter(header, tokenType.getValue()).trim();
  }
}
