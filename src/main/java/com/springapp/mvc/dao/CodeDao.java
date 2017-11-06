package com.springapp.mvc.dao;

import com.springapp.common.dao.BaseDao;
import com.springapp.mvc.entity.Columns;
import com.springapp.mvc.entity.Tables;

import java.util.List;

public interface CodeDao extends BaseDao {

    public Tables getTable(String tableName);

    public List<Columns> getColumns(String tableName);

}
