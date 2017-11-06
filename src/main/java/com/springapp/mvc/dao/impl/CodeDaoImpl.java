package com.springapp.mvc.dao.impl;

import com.springapp.common.dao.BaseDaoImpl;
import com.springapp.mvc.dao.CodeDao;
import com.springapp.mvc.entity.Columns;
import com.springapp.mvc.entity.Tables;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("codeDao")
public class CodeDaoImpl extends BaseDaoImpl implements CodeDao {

    @Override
    public Tables getTable(String tableName) {
        Tables tables = super.getSqlSession().selectOne(getStatement("getTable"), tableName);
        return tables;
    }

    @Override
    public List<Columns> getColumns(String tableName) {
        List<Columns> columns = super.getSqlSession().selectList(getStatement("getColumns"), tableName);
        return columns;
    }
}
