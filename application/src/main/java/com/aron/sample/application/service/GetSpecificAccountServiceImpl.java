package com.aron.sample.application.service;

import com.aron.sample.port.dto.SpecificAccountDTO;
import com.aron.sample.port.dto.query.GetSpecificAccountQry;
import com.aron.sample.port.in.GetSpecificAccountUseCase;
import com.aron.sample.port.out.LoadSpecificAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class GetSpecificAccountServiceImpl implements GetSpecificAccountUseCase {

    private final LoadSpecificAccountPort loadSpecificAccountPort;

    @Override
    public List<SpecificAccountDTO> getSpecificAccountBy(GetSpecificAccountQry query) {
        return loadSpecificAccountPort.getSpecificAccountBy(query);
    }
}
