package com.springapp.mvc.dao.impl;

import com.springapp.common.dao.BaseDaoImpl;
import com.springapp.mvc.dao.UserGroupDao;
import com.springapp.mvc.entity.UserGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userGroupDao")
public class UserGroupDaoImpl extends BaseDaoImpl<UserGroup> implements UserGroupDao {

    @Override
    public void deleteByUserId(String userId) {
        super.getSqlSession().delete(getStatement("deleteByUserId"),userId);
    }

    @Override
    public void deleteByGroupId(String groupId) {
        super.getSqlSession().delete(getStatement("deleteByGroupId"),groupId);

    }

    @Override
    public List<String> getUserGroupByUserId(String userId) {
        List<String> reuslt = super.getSqlSession().selectList(getStatement("getUserGroupByUserId"),userId);
        return reuslt;
    }

}
