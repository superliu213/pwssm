package com.springapp.mvc.controllers;

import com.alibaba.fastjson.JSON;
import com.springapp.common.controller.BaseController;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.mvc.entity.Function;
import com.springapp.mvc.entity.Role;
import com.springapp.mvc.service.FunctionService;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/role")
public class RoleController extends BaseController {

	@Autowired
	FunctionService functionService;
	@Autowired
	RoleService roleService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletResponse response, HttpServletRequest request,
					   @RequestParam Map<String, Object> paramMap, Integer page, Integer pageSize) {

		String result = "";
		String message = "查询成功";
		DataDto dto = new DataDto();

		PageParam pageParam = new PageParam(page,pageSize);

		PageBean pageBean = roleService.getPageBean(pageParam, paramMap);
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

		Role role = getEntity(paramMap);

		roleService.addRole(role);

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

		Role role = getEntity(paramMap);

		roleService.updateRole(role);

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

		roleService.removeRoleByKey(id);

		dto.setMessage(message);
		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/function", method = RequestMethod.POST)
	public String function(HttpServletResponse response, HttpServletRequest request,
						   String roleId) {

		String result = "";
		String message = "";

		List<String> roleFunction = null;

		roleFunction = roleService.getRoleFunction(roleId);

		CheckTreeDto dto = checkFunctionTreeDto(roleFunction);

		result = JSON.toJSONString(dto);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/rolefunction", method = RequestMethod.POST)
	public String rolefunction(HttpServletResponse response, HttpServletRequest request,
							   String roleId, String idstr) {
		String result = "";
		String message = "保存成功";
		DataDto dto = new DataDto();

		String[] ids = idstr.split(",");

		roleService.roleFunction(roleId, ids);

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

		roleService.initData();

		result = JSON.toJSONString(dto);
		return result;
	}

	private CheckTreeDto checkFunctionTreeDto(List<String> ids) {

		CheckTreeDto dto = CheckTreeDto.getInstance();

		dto.setCheckbox(new TreeStyle());

		List<String> list = new ArrayList<>();
		list.add("checkbox");
		dto.setPlugins(list);

		List<Object> coreList = new ArrayList<>();
		TreeCode treeCode = new TreeCode();
		dto.setCore(treeCode);
		treeCode.setData(coreList);

		Map<String, Children> childenMap1 = new LinkedHashMap<>();
		Map<String, Children> childenMap2 = new LinkedHashMap<>();

		List<Function> dbFunction = functionService.getAllFunctions();

		for (Function function : dbFunction) {
			Children children = new Children();
			if (function.getFunctionType() < 3) {
				childenMap1.put(function.getFunctionId(), children);
			} else if (function.getFunctionType() >= 3) {
				childenMap2.put(function.getFunctionId(), children);
				children.setIcon("none");
			}

			children.setState(new State(true));
			children.setText(function.getFunctionName());
			children.setId(function.getId() + "");
			children.setData(function.getFunctionId());
			children.setParentId(function.getFunctionParentId() + "");
		}

		for (String s : childenMap2.keySet()) {
			Children cChilden = childenMap2.get(s);

			checkSelected(ids, cChilden);

			Children pChilden = childenMap1.get(cChilden.getParentId());

			if (pChilden != null && pChilden.getChildren() != null) {
				pChilden.getChildren().add(cChilden);
			} else {
				List<Children> tempList = new ArrayList<>();
				tempList.add(cChilden);
				pChilden.setChildren(tempList);
			}
		}

		for (String s : childenMap1.keySet()) {
			Children cChilden = childenMap1.get(s);
			checkSelected(ids, cChilden);

			if (("-1").equals(cChilden.getParentId())) {
				coreList.add(cChilden);
			} else {
				Children pChilden = childenMap1.get(cChilden.getParentId());

				if (pChilden != null && pChilden.getChildren() != null) {
					pChilden.getChildren().add(cChilden);
				} else {
					List<Children> tempList = new ArrayList<>();
					tempList.add(cChilden);
					pChilden.setChildren(tempList);
				}
			}
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

	private Role getEntity(Map<String, Object> paramMap){
		Role entity = new Role();

		Long id = paramMap.get("id").equals("")? 0l :Long.valueOf(paramMap.get("id").toString());
		Integer version = (Integer) getColumnValue("version", Integer.class ,paramMap);
		String remark = paramMap.get("remark").equals("") ? "" : paramMap.get("remark").toString();
		String roleDesc = paramMap.get("roleDesc").equals("") ? "" : paramMap.get("roleDesc").toString();
		String roleId = paramMap.get("roleId").equals("") ? "" : paramMap.get("roleId").toString();

		entity.setId(id);
		entity.setVersion(version);
		entity.setRemark(remark);
		entity.setRoleDesc(roleDesc);
		entity.setRoleId(roleId);

		return  entity;
	}

}