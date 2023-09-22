package com.aron.sample.adapter.out.persistence.mapper;

import com.aron.sample.adapter.out.persistence.table.AccountRoleRelationTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountRoleRelationMapper {

  List<AccountRoleRelationTable> findAllBy(@Param("accountId") Long accountId);

  void saveAll(@Param("relations") List<AccountRoleRelationTable> relations);

  void deleteByAccountId(@Param("accountId") Long accountId);

  void deleteByAccountIds(@Param("accountIds") List<Long> collect);

}
