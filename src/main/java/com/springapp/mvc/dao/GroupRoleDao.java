package com.springapp.mvc.dao;

import com.springapp.common.dao.BaseDao;
import com.springapp.mvc.entity.GroupRole;

import java.util.List;

public interface GroupRoleDao extends BaseDao<GroupRole> {

    void deleteByGroupId(String groupId);

    void deleteByRoleId(String roleId) ;

    List<String> getRoleList(String groupId);

}
