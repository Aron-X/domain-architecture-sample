package com.aron.sample.adapter.in.web;

import com.aron.sample.port.dto.command.AssignRolesCmd;
import com.aron.sample.port.in.AssignRolesUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-test/v1/accounts")
@Slf4j
@Validated
public class AssignRolesController {

    private final AssignRolesUseCase assignRolesPort;

    @GetMapping("/helloWorld")
    public String hello() {
        return "Hello, welcome to domain architecture sample!";
    }

    @PostMapping("/assign/{accountId}/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    public void assignRoles(@PathVariable("accountId") @NotEmpty String networkId,
                            @RequestBody @Valid AssignRolesCmd request) {
        assignRolesPort.assignRoles(networkId, request.getRoles());
    }

}
