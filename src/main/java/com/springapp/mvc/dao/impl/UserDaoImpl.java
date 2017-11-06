package com.springapp.mvc.dao.impl;

import com.springapp.common.dao.BaseDaoImpl;
import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public List<User> getUserByUserIdAndPassword(Map<String, Object> paramMap) {
        List<User> result = getSqlSession().selectList(getStatement("getUserByUserIdAndPassword"), paramMap);
        return result;
    }

    @Override
    public void updatePassword(Map<String, Object> paramMap) {
        super.getSqlSession().update(getStatement("updatePassword"), paramMap);
    }

}
