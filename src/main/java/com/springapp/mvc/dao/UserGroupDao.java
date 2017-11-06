package com.springapp.mvc.dao;

import com.springapp.common.dao.BaseDao;
import com.springapp.mvc.entity.UserGroup;

import java.util.List;

public interface UserGroupDao extends BaseDao<UserGroup>{

    void deleteByUserId(String userId);

    void deleteByGroupId(String groupId);

    List<String> getUserGroupByUserId(String userId);

}
