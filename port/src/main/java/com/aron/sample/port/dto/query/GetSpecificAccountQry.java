package com.aron.sample.port.dto.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSpecificAccountQry {
    private String accountId;
    private String name;
    private String sex;
}
