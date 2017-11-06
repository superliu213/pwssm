package com.springapp.mvc.dao;

import com.springapp.common.dao.BaseDao;
import com.springapp.mvc.entity.Function;

import java.util.List;
import java.util.Map;

public interface FunctionDao extends BaseDao<Function> {

    List<Function> getFunctionsNoButtonByAdmin(Map<String, Object> paramMap);

    List<Function> getFunctionsNoButtonByUserId(Map<String, Object> paramMap);

    List<String> getButtonPosition(Map<String, Object> paramMap);

    List getFunctionUsed(Long id);

}
