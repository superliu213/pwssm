package com.springapp.mvc.service;

import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;

import java.util.Map;

public interface CodeService {

    public PageBean getTables(PageParam pageParam, Map<String, Object> paramMap);

    public byte[] generatorCode(String[] buttonsNames, String[] tableNames);

}
