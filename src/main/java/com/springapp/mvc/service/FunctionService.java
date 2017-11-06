package com.springapp.mvc.service;

import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.mvc.entity.Function;

import java.util.List;
import java.util.Map;

public interface FunctionService {

	public PageBean getFunctionPageBean(PageParam pageParam, Map<String, Object> paramMap);

	List<Function> getAllFunctions();

	List<Function> getFunctionsNoButton(String userId);

	void removeFunctionByKey(Long id);

	void addFunction(Function function);

	void updateFunction(Function function);

	void initData();

	List<String> getButtonPosition(String formName, String user);

}
