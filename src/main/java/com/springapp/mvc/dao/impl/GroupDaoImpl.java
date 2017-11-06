package com.springapp.mvc.dao.impl;

import com.springapp.common.dao.BaseDaoImpl;
import com.springapp.mvc.dao.GroupDao;
import com.springapp.mvc.entity.Group;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {

}
