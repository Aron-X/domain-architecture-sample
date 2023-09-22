package com.aron.sample.adapter.out.persistence;

import com.aron.sample.adapter.out.persistence.mapper.AccountMapper;
import com.aron.sample.adapter.out.persistence.mapper.AccountRoleRelationMapper;
import com.aron.sample.adapter.out.persistence.query.AccountQuery;
import com.aron.sample.adapter.out.persistence.table.AccountRoleRelationTable;
import com.aron.sample.domain.account.model.Account;
import com.aron.sample.domain.account.model.vo.RoleId;
import com.aron.sample.port.dto.SpecificAccountDTO;
import com.aron.sample.port.dto.query.GetSpecificAccountQry;
import com.aron.sample.port.out.LoadSpecificAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LoadSpecificAccountRepoImpl implements LoadSpecificAccountPort {

    private final AccountMapper accountMapper;
    private final AccountRoleRelationMapper accountRoleRelationMapper;

    @Override
    public List<SpecificAccountDTO> getSpecificAccountBy(GetSpecificAccountQry query) {
        AccountQuery accountQuery = mapRequestQryToMapperQry(query);

        if (accountQuery.isEmpty()) {
            return Collections.emptyList();
        }

        List<Account> accounts = accountMapper.findAccountBy(accountQuery)
                .stream().map(accountTable -> {
                    Account account = accountTable.toModel();
                    fillRoles(account);
                    return account;
                }).collect(Collectors.toList());

        return mapAccountToResponse(accounts);
    }

    private List<SpecificAccountDTO> mapAccountToResponse(List<Account> accounts) {
        //here are mapping process...
        return Collections.emptyList();
    }

    private AccountQuery mapRequestQryToMapperQry(GetSpecificAccountQry query){
        //here are mapping process...
        return AccountQuery.builder()
                .build();
    }

    private void fillRoles(Account account) {
        List<Long> roleIds = accountRoleRelationMapper
                .findAllBy(account.getId().getValue())
                .stream()
                .map(AccountRoleRelationTable::getRoleId)
                .collect(Collectors.toList());
        account.setRoles(roleIds.stream()
                .map(RoleId::new)
                .collect(Collectors.toList()));
    }
}
