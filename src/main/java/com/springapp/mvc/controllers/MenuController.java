package com.springapp.mvc.controllers;

import com.alibaba.fastjson.JSON;
import com.springapp.common.controller.BaseController;
import com.springapp.mvc.entity.Function;
import com.springapp.mvc.service.FunctionService;
import com.springapp.mvc.vo.MenuDto;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class MenuController extends BaseController {

	@Autowired
	FunctionService functionService;

	@ResponseBody
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String menu(HttpServletResponse response, HttpServletRequest request,
					   String userId) {

		String result = "";

		MenuDto dto = getMenuDto(userId);

		result = JSON.toJSONString(dto);

		return result;
	}

	// 数据库获取菜单数据
	private MenuDto getMenuDto(String user) {
		MenuDto menuDto = new MenuDto();

		List<MenuDto> list = new ArrayList<>();
		menuDto.setSysMenuList(list);

		Map<String, MenuDto> map = new LinkedHashMap<>();

		List<Function> dbFunction = functionService.getFunctionsNoButton(user);

		for (Function function : dbFunction) {
			if (function.getFunctionType() < 3) {
				MenuDto temp = new MenuDto();
				if (!("").equals(function.getFunctionLogo())) {
					temp.setLogoTag(function.getFunctionLogo());
				}
				temp.setMenuName(function.getFunctionName());
				temp.setUrl(function.getFunctionUrl());
				temp.setId(function.getFunctionId());
				temp.setParentId(function.getFunctionParentId());
				map.put(function.getFunctionId(), temp);
			}
		}

		for (String i : map.keySet()) {
			MenuDto cMenuDto = map.get(i);

			if (("-1").equals(cMenuDto.getParentId())) {
				list.add(cMenuDto);
			} else {
				MenuDto pMenuDto = map.get(cMenuDto.getParentId());

				if (pMenuDto != null && pMenuDto.getSysMenuList() != null) {
					pMenuDto.getSysMenuList().add(cMenuDto);
				} else {
					List<MenuDto> templist = new ArrayList<>();
					pMenuDto.setSysMenuList(templist);
					templist.add(cMenuDto);
				}
			}
		}

		return menuDto;
	}

	// 使用前端菜单数据
	private MenuDto getMenuDtoByJson() {
		MenuDto dto = null;

		URL baseUrl = Thread.currentThread().getContextClassLoader().getResource("");
		File file = new File(baseUrl.getPath());
		String dataFilePath = file.getParentFile().getParent() + "/postloan/mock/menu.json";

		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(dataFilePath);
			String text = IOUtils.toString(inputStream, "utf-8");
			dto = JSON.parseObject(text, MenuDto.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dto;
	}
}
