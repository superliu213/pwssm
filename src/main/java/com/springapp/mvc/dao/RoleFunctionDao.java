package com.springapp.mvc.dao;

import com.springapp.common.dao.BaseDao;
import com.springapp.mvc.entity.RoleFunction;

import java.util.List;

public interface RoleFunctionDao extends BaseDao<RoleFunction>{

    void deleteByRoleId(String roleId);

    void deleteByFunctionId(String functionId);

    List<String> getFunctionList(String roleId);
}
