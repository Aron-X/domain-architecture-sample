package com.aron.sample.application.service;

import com.aron.sample.port.in.AssignRolesUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class AssignRolesServiceImpl implements AssignRolesUseCase {

    @Override
    @Transactional
    public void assignRoles(String networkId, List<Long> roleIds) {
        //xxx
    }
}
