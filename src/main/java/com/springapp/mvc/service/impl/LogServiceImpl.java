package com.springapp.mvc.service.impl;

import com.springapp.common.busilog.dao.SysLogDao;
import com.springapp.common.entity.SysLog;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.common.service.BaseServiceImpl;
import com.springapp.mvc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("logService")
@Transactional
public class LogServiceImpl extends BaseServiceImpl implements LogService{

	@Autowired
	SysLogDao sysLogDao;

	@Override
	public void initData() {
		Date date = new Timestamp(System.currentTimeMillis());
		SysLog log1 = new SysLog(1L, date, "admin", (short) 1, (short) 1);
		SysLog log2 = new SysLog(2L, date, "2", (short) 2, (short) 2);

		List<SysLog> sysLogs = sysLogDao.listBy(null);
		for (SysLog sysLog : sysLogs) {
			sysLogDao.deleteById(sysLog.getId());
		}

		sysLogDao.insert(log1);
		sysLogDao.insert(log2);
	}

	@Override
	public PageBean getLogs(PageParam pageParam, Map<String, Object> paramMap) {
		PageBean pageBean = sysLogDao.listPage(pageParam, paramMap);
		return pageBean;
	}

	@Override
	public List<SysLog> getLogs() {
		List<SysLog> result = sysLogDao.listBy(null);
		return result;
	}

	public void addLog(SysLog sysLog) {
		sysLogDao.insert(sysLog);
	}

}
