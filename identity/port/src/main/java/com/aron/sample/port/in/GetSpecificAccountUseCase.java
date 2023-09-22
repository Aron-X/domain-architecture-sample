package com.aron.sample.port.in;

import com.aron.sample.port.dto.SpecificAccountDTO;
import com.aron.sample.port.dto.query.GetSpecificAccountQry;

import java.util.List;

public interface GetSpecificAccountUseCase {

    List<SpecificAccountDTO> getSpecificAccountBy(GetSpecificAccountQry query);
}
