package com.aron.sample.adapter.out.persistence.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class AccountQuery {

  private String networkId;
  private Long accountId;

  public boolean isEmpty() {
    return StringUtils.isEmpty(networkId)
        && accountId == null;
  }

}
