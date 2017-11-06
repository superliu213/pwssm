package com.springapp.mvc.service.impl;

import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.common.service.BaseServiceImpl;
import com.springapp.mvc.dao.GroupDao;
import com.springapp.mvc.dao.GroupRoleDao;
import com.springapp.mvc.dao.UserGroupDao;
import com.springapp.mvc.entity.Group;
import com.springapp.mvc.entity.GroupRole;
import com.springapp.mvc.entity.UserGroup;
import com.springapp.mvc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("groupService")
@Transactional
public class GroupServiceImpl extends BaseServiceImpl implements GroupService{

	@Autowired
	GroupDao groupDao;

	@Autowired
	GroupRoleDao groupRoleDao;

    @Autowired
	UserGroupDao userGroupDao;

	@Override
	public PageBean getGroupPageBean(PageParam pageParam, Map<String, Object> paramMap) {
		PageBean pageBean = groupDao.listPage(pageParam, paramMap);
		return pageBean;

	}

	@Override
	public List<Group> getAllGroups() {
		List<Group> result = groupDao.listBy(null);
		return result;
	}

	@Override
	public void removeGroupByKey(Long id) {
        Group group = groupDao.getById(id);
        String groupId = group.getGroupId();
        groupRoleDao.deleteByGroupId(groupId);
//        userGroupDao.deleteByGroupId(groupId);
        groupDao.deleteById(id);
	}

	@Override
	public void addGroup(Group group) {
        groupDao.insert(group);
	}

	@Override
	public void updateGroup(Group group) {
        groupDao.update(group);
	}

	@Override
	public void initData() {
		Group group1 = new Group(1L, 0, "1", "1号线", (short) 1, null, 1, "");
		Group group2 = new Group(2L, 0, "2", "车站1", (short) 2, "1", 1, "");
		Group group3 = new Group(3L, 0, "3", "车站2", (short) 2, "1", 2, "");

		List<Group> groups = groupDao.listBy(null);
		for (Group group : groups) {
			groupDao.deleteById(group.getId());
		}

		groupDao.insert(group1);
        groupDao.insert(group2);
        groupDao.insert(group3);
	}

	@Override
	public List<String> getGroupRole(String groupId) {
        List<String> roleList = groupRoleDao.getRoleList(groupId);

		return roleList;
	}

	@Override
	public void groupRole(String groupId, String[] ids) {
        groupRoleDao.deleteByGroupId(groupId);

		GroupRole[] objs = new GroupRole[ids.length];

		List<GroupRole> listTemp = new ArrayList<>();

		for (int i = 0; i < ids.length; i++) {
			GroupRole groupRole = new GroupRole();
			groupRole.setRoleId(ids[i]);
			groupRole.setGroupId(groupId);
			listTemp.add(groupRole);
		}

        groupRoleDao.insert(listTemp);

	}

	@Override
	public List<String> getUserGroup(String userId) {
        List<String> result = userGroupDao.getUserGroupByUserId(userId);
		return result;
	}

	@Override
	public void userGroup(String userId, String[] ids) {
        userGroupDao.deleteByUserId(userId);

		List<UserGroup> listTemp = new ArrayList<>();

		for (int i = 0; i < ids.length; i++) {
			UserGroup userGroup = new UserGroup();
			userGroup.setGroupId(ids[i]);
			userGroup.setUserId(userId);
            listTemp.add(userGroup);
		}

        userGroupDao.insert(listTemp);
	}
}
