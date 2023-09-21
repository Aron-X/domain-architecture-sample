package com.aron.sample.adapter.in.web;

import com.aron.sample.application.dto.command.AssignRolesCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@Slf4j
@Validated
public class AssignRolesController {

//  private final IdentityApplicationService identityApplicationService;

//  @GetMapping("/info")
//  public AccountResponse getAccountAndRefresh() {
//    return identityApplicationService.getAccountAndRefresh();
//  }
//

    @GetMapping("/helloWorld")
    public String hello() {
        return "Hello, welcome to domain architecture sample!";
    }

    @PostMapping("/assign/{accountId}/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    public void assignRoles(@PathVariable("accountId") @NotEmpty String networkId,
                            @RequestBody @Valid AssignRolesCmd request) {
//    identityApplicationService.assignRoles(networkId, request.getRoles());
    }

}
