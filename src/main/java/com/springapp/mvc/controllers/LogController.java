package com.springapp.mvc.controllers;

import com.alibaba.fastjson.JSON;
import com.springapp.common.controller.BaseController;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.mvc.service.LogService;
import com.springapp.mvc.vo.DataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/log")
public class LogController extends BaseController {

	@Autowired
	LogService logService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletResponse response, HttpServletRequest request,
					   @RequestParam Map<String, Object> paramMap, Integer page, Integer pageSize) {

		String result = "";
		String message = "";
		DataDto dto = new DataDto();

		PageParam pageParam = new PageParam(page, pageSize);

		PageBean pageBean = logService.getLogs(pageParam, paramMap);
		dto.setTotalItem(pageBean.getTotalCount());
		dto.setData(pageBean.getRecordList());

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

		logService.initData();

		result = JSON.toJSONString(dto);
		return result;
	}

}
