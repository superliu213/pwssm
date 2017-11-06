package com.springapp.mvc.service;

import com.springapp.common.entity.SysLog;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;

import java.util.List;
import java.util.Map;

public interface LogService {

	public PageBean getLogs(PageParam pageParam, Map<String, Object> paramMap);

	List<SysLog> getLogs();

	void initData();

}
