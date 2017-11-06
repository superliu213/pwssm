package com.springapp.mvc.controllers;

import com.alibaba.fastjson.JSON;
import com.springapp.common.controller.BaseController;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.mvc.entity.Group;
import com.springapp.mvc.entity.Role;
import com.springapp.mvc.service.GroupService;
import com.springapp.mvc.service.RoleService;
import com.springapp.mvc.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/group")
public class GroupController extends BaseController {

	@Autowired
	RoleService roleService;

	@Autowired
	GroupService groupService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletResponse response, HttpServletRequest request,
					   @RequestParam Map<String, Object> paramMap, Integer page, Integer pageSize) {

		String result = "";
		String message = "";
		DataDto dto = new DataDto();

		PageParam pageParam = new PageParam(page, pageSize);

		PageBean pageBean = groupService.getGroupPageBean(pageParam, paramMap);
		dto.setTotalItem(pageBean.getTotalCount());
		dto.setData(pageBean.getRecordList());

		dto.setMessage(message);
		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletResponse response, HttpServletRequest request,
					  @RequestParam Map<String, Object> paramMap) {

		String result = "";
		String message = "添加成功";
		DataDto dto = new DataDto();

		Group group = getEntity(paramMap);

		groupService.addGroup(group);

		dto.setMessage(message);
		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletResponse response, HttpServletRequest request,
						 @RequestParam Map<String, Object> paramMap) {

		String result = "";
		String message = "更新成功";
		DataDto dto = new DataDto();

		Group group = getEntity(paramMap);

		groupService.updateGroup(group);

		dto.setMessage(message);
		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(HttpServletResponse response, HttpServletRequest request,
						 Long id) {

		String result = "";
		String message = "删除成功";
		DataDto dto = new DataDto();

		groupService.removeGroupByKey(id);

		dto.setMessage(message);
		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public String init(HttpServletResponse response, HttpServletRequest request) {
		String result = "";
		DataDto dto = new DataDto();
		String message = "重置成功";
		dto.setMessage(message);

		groupService.initData();

		result = JSON.toJSONString(dto);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public String role(HttpServletResponse response, HttpServletRequest request,
					   String groupId) {

		String result = "";

		List<String> groupRole = groupService.getGroupRole(groupId);

		CheckTreeDto dto = checkRoleTreeDto(groupRole);

		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/grouprole", method = RequestMethod.POST)
	public String grouprole(HttpServletResponse response, HttpServletRequest request,
							String groupId, String idstr) {
		String result = null;
		String message = "保存成功";
		DataDto dto = new DataDto();

		String[] ids = idstr.split(",");

		groupService.groupRole(groupId, ids);

		dto.setMessage(message);
		result = JSON.toJSONString(dto);

		return result;
	}

	private CheckTreeDto checkRoleTreeDto(List<String> ids) {

		CheckTreeDto dto = CheckTreeDto.getInstance();

		dto.setCheckbox(new TreeStyle());

		List<String> list = new ArrayList<>();
		list.add("checkbox");
		dto.setPlugins(list);

		List<Object> coreList = new ArrayList<>();
		TreeCode treeCode = new TreeCode();
		dto.setCore(treeCode);
		treeCode.setData(coreList);

		int num = 1;

		List<Role> dbRole = roleService.getAllRoles();

		for (Role role : dbRole) {
			Children children = new Children();
			coreList.add(children);
			children.setState(new State(true));
			children.setText(role.getRoleDesc());
			children.setId(num + "");
			children.setData(role.getRoleId());
			checkSelected(ids, children);
			num++;
		}

		return dto;
	}

	private void checkSelected(List<String> ids, Children obj) {
		if (ids != null) {
			for (String id : ids) {
				if (id.equals(obj.getData())) {
					obj.getState().setSelected(true);
					break;
				}
			}
		}
	}

	private Group getEntity(Map<String, Object> paramMap){
		Group entity = new Group();

		Long id = (Long) getColumnValue("id", Long.class, paramMap);
		Integer version = (Integer) getColumnValue("version", Integer.class ,paramMap);
		String remark = (String) getColumnValue("remark", String.class, paramMap);
		Integer orderNo = (Integer) getColumnValue("orderNo", Integer.class, paramMap);
		String groupId = (String) getColumnValue("groupId", String.class, paramMap);
		Short groupLever = (Short) getColumnValue("groupLever", Short.class, paramMap);
		String groupName = (String) getColumnValue("groupName", String.class, paramMap);
		String groupParentId = (String) getColumnValue("groupParentId", String.class, paramMap);

		entity.setId(id);
		entity.setVersion(version);
		entity.setRemark(remark);
		entity.setOrderNo(orderNo);
		entity.setGroupId(groupId);
		entity.setGroupLever(groupLever);
		entity.setGroupName(groupName);
		entity.setGroupParentId(groupParentId);

		return  entity;
	}

}