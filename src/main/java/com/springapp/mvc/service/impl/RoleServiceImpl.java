package com.springapp.mvc.service.impl;

import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.common.service.BaseServiceImpl;
import com.springapp.mvc.dao.GroupRoleDao;
import com.springapp.mvc.dao.RoleDao;
import com.springapp.mvc.dao.RoleFunctionDao;
import com.springapp.mvc.dao.UserRoleDao;
import com.springapp.mvc.entity.Role;
import com.springapp.mvc.entity.RoleFunction;
import com.springapp.mvc.entity.UserRole;
import com.springapp.mvc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseServiceImpl implements RoleService{

    @Autowired
	RoleDao roleDao;

    @Autowired
	RoleFunctionDao roleFunctionDao;

    @Autowired
	UserRoleDao userRoleDao;

    @Autowired
	GroupRoleDao groupRoleDao;

	@Override
    public PageBean getPageBean(PageParam pageParam, Map<String, Object> paramMap) {
        PageBean pageBean = roleDao.listPage(pageParam, paramMap);
        return pageBean;
	}

	@Override
	public List<Role> getAllRoles() {
        List<Role> result = roleDao.listBy(null);
        return result;
    }

	@Override
	public void removeRoleByKey(Long id) {
        Role role = roleDao.getById(id);
        String roleId = role.getRoleId();
        roleFunctionDao.deleteByRoleId(roleId);
        userRoleDao.deleteByRoleId(roleId);
        groupRoleDao.deleteByRoleId(roleId);
        roleDao.deleteById(id);
	}

	@Override
	public void addRole(Role role) {
        roleDao.insert(role);
	}

	@Override
	public void initData() {
		Role role1 = new Role(1L, "0", "超级管理员", "1");
		Role role2 = new Role(2L, "1", "一号线", "2");

		List<Role> roles = roleDao.listBy(null);
		for (Role role : roles) {
			roleDao.deleteById(role.getId());
		}

		roleDao.insert(role1);
        roleDao.insert(role2);
	}

	@Override
	public void updateRole(Role role) {
        roleDao.update(role);
	}

	@Override
	public List<String> getUserRole(String userId) {
        List<String> result = userRoleDao.getRoleList(userId);
		return result;
	}

	@Override
	public void userRole(String userId, String[] ids) {
        userRoleDao.deleteByUserId(userId);

		List<UserRole> listTemp = new ArrayList<>();

		for (int i = 0; i < ids.length; i++) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(ids[i]);
			userRole.setUserId(userId);
            listTemp.add(userRole);
		}

        userRoleDao.insert(listTemp);

	}

	@Override
	public List<String> getRoleFunction(String roleId) {
        List<String> result = roleFunctionDao.getFunctionList(roleId);
		return result;
	}

	@Override
	public void roleFunction(String roleId, String[] ids) {
        roleFunctionDao.deleteByRoleId(roleId);

		List<RoleFunction> listTemp = new ArrayList<>();

		for (int i = 0; i < ids.length; i++) {
			RoleFunction roleFunction = new RoleFunction();
			roleFunction.setFunctionId(ids[i]);
			roleFunction.setRoleId(roleId);
            listTemp.add(roleFunction);
		}

        roleFunctionDao.insert(listTemp);
	}


}
