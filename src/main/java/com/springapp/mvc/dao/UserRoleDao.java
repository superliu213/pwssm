package com.springapp.mvc.dao;

import com.springapp.common.dao.BaseDao;
import com.springapp.mvc.entity.UserRole;

import java.util.List;

public interface UserRoleDao extends BaseDao<UserRole>{

    void deleteByUserId(String userId);

    void deleteByRoleId(String roleId);

    List<String> getRoleList(String userId);

}
