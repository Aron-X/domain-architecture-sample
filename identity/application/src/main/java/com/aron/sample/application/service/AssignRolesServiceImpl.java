package com.aron.sample.application.service;

import com.aron.sample.port.in.AssignRolesUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AssignRolesServiceImpl implements AssignRolesUseCase {

    @Override
    public void assignRoles(String networkId, List<Long> roleIds) {
        //xxx
    }
}
