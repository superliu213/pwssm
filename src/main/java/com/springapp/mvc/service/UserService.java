package com.springapp.mvc.service;

import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.mvc.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

	PageBean getUsers(PageParam pageParam, Map<String, Object> paramMap);

	List<User> getAllUsers();

	void removeUserByKey(Long id);

	void addUser(User user);

	void updateUser(User user);

	void initData();
	
	Boolean checkLoginUser(Map<String, Object> paramMap);

	void passwordreset(Long id, Integer version);

	void updatepassword(Long id, String oldPassword, String newPassword, Integer version);

	User getUser(Long id);

}
