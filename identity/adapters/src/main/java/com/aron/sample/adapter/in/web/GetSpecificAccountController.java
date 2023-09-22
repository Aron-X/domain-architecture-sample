package com.aron.sample.adapter.in.web;

import com.aron.sample.port.dto.SpecificAccountDTO;
import com.aron.sample.port.dto.query.GetSpecificAccountQry;
import com.aron.sample.port.in.GetSpecificAccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-test/v1/report")
@Slf4j
@Validated
public class GetSpecificAccountController {

    private final GetSpecificAccountUseCase getSpecificAccountUseCase;

    @GetMapping("/specific-accounts")
    public List<SpecificAccountDTO> getAccountAndRefresh(@Valid GetSpecificAccountQry query) {
        return getSpecificAccountUseCase.getSpecificAccountBy(query);
    }
}
