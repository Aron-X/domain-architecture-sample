package com.aron.sample.adapter.out.persistence.mapper;

import com.aron.sample.adapter.out.persistence.query.AccountQuery;
import com.aron.sample.adapter.out.persistence.table.AccountTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountMapper {

  List<AccountTable> findAccountBy(@Param(value = "query") AccountQuery query);

  void save(@Param(value = "account") AccountTable account);

  void update(@Param(value = "account") AccountTable from);

  void saveAll(@Param(value = "accounts") List<AccountTable> collect);

  List<AccountTable> findAll();
}
