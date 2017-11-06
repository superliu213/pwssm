package com.springapp.mvc.dao;

import com.springapp.common.dao.BaseDao;
import com.springapp.mvc.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao extends BaseDao<User>{

    List<User> getUserByUserIdAndPassword(Map<String, Object> paramMap);

    void updatePassword(Map<String, Object> paramMap);

}
