package com.springapp.mvc.dao.impl;

import com.springapp.common.dao.BaseDaoImpl;
import com.springapp.mvc.dao.UserRoleDao;
import com.springapp.mvc.entity.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements UserRoleDao {

    @Override
    public void deleteByUserId(String userId) {
        super.getSqlSession().delete(getStatement("deleteByUserId"),userId);
    }

    @Override
    public void deleteByRoleId(String roleId) {
        super.getSqlSession().delete(getStatement("deleteByRoleId"),roleId);
    }

    @Override
    public List<String> getRoleList(String userId) {
        List<String> result = super.getSqlSession().selectList(getStatement("getRoleList"), userId);
        return result;
    }
}
