package com.aron.sample.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
public class RestTemplateConfig {

  private final RestTemplateBuilder builder;

  @Bean
  public RestTemplate getRestTemplate() {
    return builder.build();
  }
}
