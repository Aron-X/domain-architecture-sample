package com.aron.sample.port.dto.command;

import com.aron.sample.infrastructre.validator.NotNullOrEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignRolesCmd {

  @NotNullOrEmpty
  private List<Long> roles;

}
