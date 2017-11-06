package com.springapp.mvc.dao.impl;


import com.springapp.common.dao.BaseDaoImpl;
import com.springapp.mvc.dao.RoleDao;
import com.springapp.mvc.entity.Role;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
}
