package com.springapp.mvc.controllers;

import com.alibaba.fastjson.JSON;
import com.springapp.common.application.ApplicationGlobalNames;
import com.springapp.common.controller.BaseController;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.common.util.MD5Util;
import com.springapp.mvc.entity.Group;
import com.springapp.mvc.entity.Role;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.service.FunctionService;
import com.springapp.mvc.service.GroupService;
import com.springapp.mvc.service.RoleService;
import com.springapp.mvc.service.UserService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@Autowired
	GroupService groupService;

	@Autowired
	RoleService roleService;

	@Autowired
	FunctionService functionService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletResponse response, HttpServletRequest request,
					   @RequestParam Map<String, Object> paramMap, Integer page, Integer pageSize) {

		String result = "";
		String message = "查询成功";
		DataDto dto = new DataDto();

		PageParam pageParam = new PageParam(page,pageSize);

		PageBean pageBean = userService.getUsers(pageParam, paramMap);
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

		User user = getEntity(paramMap);
		user.setUserPassWord(MD5Util.MD5(ApplicationGlobalNames.RESET_PASSWD));

		userService.addUser(user);

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

		User user = getEntity(paramMap);

		userService.updateUser(user);

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

		userService.removeUserByKey(id);

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

		userService.initData();

		result = JSON.toJSONString(dto);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/group", method = RequestMethod.POST)
	public String group(HttpServletResponse response, HttpServletRequest request,
						String userId) {

		String result = "";
		String message = "查询成功";

		List<String> userGroup = null;

		try {
			userGroup = groupService.getUserGroup(userId);
		} catch (Exception e) {
			message = "查询失败";
		}

		CheckTreeDto dto = checkGroupTreeDto(userGroup);

		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/usergroup", method = RequestMethod.POST)
	public String usergroup(HttpServletResponse response, HttpServletRequest request,
							String userId, String idstr) {
		String result = null;
		String message = "保存成功";
		DataDto dto = new DataDto();

		String[] ids = idstr.split(",");

		groupService.userGroup(userId, ids);

		dto.setMessage(message);
		result = JSON.toJSONString(dto);

		return result;
	}

	private CheckTreeDto checkGroupTreeDto(List<String> ids) {

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

		List<Group> allGroups = groupService.getAllGroups();

		for (Group group : allGroups) {
			Children children = new Children();
			coreList.add(children);
			children.setState(new State(true));
			children.setText(group.getGroupName());
			children.setId(num + "");
			children.setData(group.getGroupId());
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

	@ResponseBody
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public String role(HttpServletResponse response, HttpServletRequest request,
					   String userId) {

		String result = "";
		String message = "";

		List<String> userRole = null;

		try {
			userRole = roleService.getUserRole(userId);
		} catch (Exception e) {
			message = "查询失败";
		}

		CheckTreeDto dto = checkRoleTreeDto(userRole);

		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/userrole", method = RequestMethod.POST)
	public String userrole(HttpServletResponse response, HttpServletRequest request,
						   String userId, String idstr) {
		String result = null;
		String message = "保存成功";
		DataDto dto = new DataDto();

		String[] ids = idstr.split(",");

		roleService.userRole(userId, ids);

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

	@ResponseBody
	@RequestMapping(value = "/passwordreset", method = RequestMethod.POST)
	public String passwordreset(HttpServletResponse response, HttpServletRequest request,
								Long id, Integer version) {
		String result = null;
		String message = "重置成功";
		DataDto dto = new DataDto();

		try {
			userService.passwordreset(id,version);
		} catch (Exception e) {
			message = "重置失败";
		}

		dto.setMessage(message);
		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	public String updatepassword(HttpServletResponse response, HttpServletRequest request,
								 Long id, String oldPassword, String newPassword, Integer version) {
		String result = null;
		String message = "更新成功";
		DataDto dto = new DataDto();

		try {
			userService.updatepassword(id, oldPassword, newPassword, version);
		} catch (Exception e) {
			message = "更新失败";
		}

		dto.setMessage(message);
		result = JSON.toJSONString(dto);

		return result;
	}

	private User getEntity(Map<String, Object> paramMap){
		User entity = new User();

		Long id = (Long) getColumnValue("id", Long.class, paramMap);
		Integer version = (Integer) getColumnValue("version", Integer.class ,paramMap);
		String remark = (String) getColumnValue("remark", String.class, paramMap);
		String userPassWord = (String) getColumnValue("userPassWord", String.class, paramMap);
		String userId = (String) getColumnValue("userId", String.class, paramMap);
		String userName = (String) getColumnValue("userName", String.class, paramMap);
		Short ifValid = (Short) getColumnValue("ifValid", Short.class, paramMap);
		Date pwValidityPeriod = (Date) getColumnValue("pwValidityPeriod", Date.class, paramMap);
		Date userBirthday = (Date) getColumnValue("userBirthday", Date.class, paramMap);
		String userEmail = (String) getColumnValue("userEmail", String.class, paramMap);
		String userIdCard = (String) getColumnValue("userIdCard", String.class, paramMap);
		String userTelephone = (String) getColumnValue("userTelephone", String.class, paramMap);
		Date userValidityPeriod = (Date) getColumnValue("userValidityPeriod", Date.class, paramMap);

		entity.setId(id);
		entity.setVersion(version);
		entity.setRemark(remark);
		entity.setUserPassWord(userPassWord);
		entity.setUserId(userId);
		entity.setUserName(userName);
		entity.setIfValid(ifValid);
		entity.setPwValidityPeriod(pwValidityPeriod);
		entity.setUserBirthday(userBirthday);
		entity.setUserEmail(userEmail);
		entity.setUserIdCard(userIdCard);
		entity.setUserTelephone(userTelephone);
		entity.setUserValidityPeriod(userValidityPeriod);

		return  entity;
	}

}
