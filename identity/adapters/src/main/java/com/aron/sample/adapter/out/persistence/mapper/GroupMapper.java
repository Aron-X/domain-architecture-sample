package com.aron.sample.adapter.out.persistence.mapper;

import com.aron.sample.adapter.out.persistence.table.GroupTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {
  List<GroupTable> findAll();
}
