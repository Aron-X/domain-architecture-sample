package com.arom.sample.security;

import com.arom.sample.security.filter.UserTokenFilter;
import com.aron.sample.port.out.TokenRefreshPort;
import com.aron.sample.port.out.TokenRetrievePort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(101)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String AUTHORIZED_MATCHER_PATTERN = "/api/v1/**";

  private final UnauthorizedEntryPoint unauthorizedEntryPoint;
  private final TokenRefreshPort tokenRefreshPort;
  private final TokenRetrievePort tokenRetrievePort;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable()
        .antMatcher(AUTHORIZED_MATCHER_PATTERN)
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(unauthorizedEntryPoint)
        .and()
        .addFilterBefore(userTokenFilter(), UsernamePasswordAuthenticationFilter.class);

  }

  private UserTokenFilter userTokenFilter() throws Exception {
    return new UserTokenFilter(
        new AntPathRequestMatcher(AUTHORIZED_MATCHER_PATTERN), tokenRefreshPort, tokenRetrievePort);
  }

}
