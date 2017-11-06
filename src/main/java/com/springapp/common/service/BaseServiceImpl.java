package com.springapp.common.service;

import com.springapp.common.busilog.service.SyncLogService;
import com.springapp.common.dao.BaseDao;
import com.springapp.common.entity.BaseEntity;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Service 基类实现
 *
 */
public abstract class BaseServiceImpl {

	@Autowired
	protected SyncLogService dbLog;

	protected Logger logger = LoggerFactory.getLogger(getClass());

}
