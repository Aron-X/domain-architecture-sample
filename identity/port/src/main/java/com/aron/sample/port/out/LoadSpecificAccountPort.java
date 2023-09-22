package com.aron.sample.port.out;

import com.aron.sample.port.dto.SpecificAccountDTO;
import com.aron.sample.port.dto.query.GetSpecificAccountQry;

import java.util.List;

public interface LoadSpecificAccountPort {

    List<SpecificAccountDTO> getSpecificAccountBy(GetSpecificAccountQry query);
}
