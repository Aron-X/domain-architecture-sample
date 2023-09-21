package com.aron.sample.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenType {
  /**
   * Used by Portal.
   **/
  BEARER("Bearer");

  private String value;
}
