package com.aron.sample.adapter.out.persistence.mapper;

import com.aron.sample.adapter.out.persistence.query.RoleQuery;
import com.aron.sample.adapter.out.persistence.table.RoleTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RoleMapper {

  List<RoleTable> findAllBy(@Param("query") RoleQuery query);

  Optional<RoleTable> findBy(@Param("query") RoleQuery query);

  List<RoleTable> findAll();

}
