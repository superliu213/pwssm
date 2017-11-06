package com.springapp.mvc.controllers;

import com.alibaba.fastjson.JSON;
import com.springapp.common.controller.BaseController;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.mvc.entity.Function;
import com.springapp.mvc.service.FunctionService;
import com.springapp.mvc.vo.DataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/function")
public class FunctionController extends BaseController {

	@Autowired
	FunctionService functionService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletResponse response, HttpServletRequest request,
					   @RequestParam Map<String, Object> paramMap, Integer page, Integer pageSize) {

		String result = "";
		String message = "查询成功";
		DataDto dto = new DataDto();

		PageParam pageParam = new PageParam(page, pageSize);

		PageBean pageBean = functionService.getFunctionPageBean(pageParam, paramMap);
		dto.setTotalItem(pageBean.getTotalCount());
		dto.setData(pageBean.getRecordList());

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

		Function function = getEntity(paramMap);

		functionService.addFunction(function);

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

		Function function = getEntity(paramMap);

		functionService.updateFunction(function);

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

		functionService.removeFunctionByKey(id);

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

		functionService.initData();

		result = JSON.toJSONString(dto);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagefunction", method = RequestMethod.POST)
	public String pagefunction(HttpServletResponse response, HttpServletRequest request,
							   String form, String userId) {
		String result = "";
		DataDto dto = new DataDto();

		List<String> buttonPosition = functionService.getButtonPosition(form, userId);
		dto.setData(buttonPosition);

		result = JSON.toJSONString(dto);
		return result;
	}

	private Function getEntity(Map<String, Object> paramMap){
		Function entity = new Function();

		Long id = (Long) getColumnValue("id", Long.class, paramMap);
		Integer version = (Integer) getColumnValue("version", Integer.class ,paramMap);
		String remark = (String) getColumnValue("remark", String.class, paramMap);
		String buttonPosition = (String) getColumnValue("buttonPosition", String.class, paramMap);
		String functionId = (String) getColumnValue("functionId", String.class, paramMap);
		String functionLogo = (String) getColumnValue("functionLogo", String.class, paramMap);
		String functionName = (String) getColumnValue("functionName", String.class, paramMap);
		String functionParentId = (String) getColumnValue("functionParentId", String.class, paramMap);
		Short functionType = (Short) getColumnValue("functionType", Short.class, paramMap);
		String functionUrl = (String) getColumnValue("functionUrl", String.class, paramMap);
		Integer orderNo = (Integer) getColumnValue("orderNo", Integer.class, paramMap);

		entity.setId(id);
		entity.setVersion(version);
		entity.setRemark(remark);
		entity.setButtonPosition(buttonPosition);
		entity.setFunctionId(functionId);
		entity.setFunctionLogo(functionLogo);
		entity.setFunctionName(functionName);
		entity.setFunctionParentId(functionParentId);
		entity.setFunctionType(functionType);
		entity.setFunctionUrl(functionUrl);
		entity.setOrderNo(orderNo);

		return  entity;
	}

}