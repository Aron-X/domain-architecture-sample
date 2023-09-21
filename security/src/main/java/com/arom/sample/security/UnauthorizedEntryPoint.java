package com.arom.sample.security;

import com.aron.sample.exception.BizError;
import com.aron.sample.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    response.getWriter()
        .println(ToStringBuilder.reflectionToString(ErrorResponse.builder()
                .code(BizError.UNAUTHORIZED.getCode())
                .message(String.format(BizError.UNAUTHORIZED.getMessage(), authException.getMessage()))
                .build(),
            ToStringStyle.JSON_STYLE));

    response.getWriter()
        .flush();
  }

}
