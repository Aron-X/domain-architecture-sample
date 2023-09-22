package com.aron.sample.port.in;

import java.util.List;

public interface AssignRolesUseCase {
    /**
     * xxxx
     * @param networkId
     * @param roleIds
     */
    void assignRoles(String networkId, List<Long> roleIds);
}
