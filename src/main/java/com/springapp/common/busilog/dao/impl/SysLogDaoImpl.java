package com.springapp.common.busilog.dao.impl;


import com.springapp.common.busilog.dao.SysLogDao;
import com.springapp.common.entity.SysLog;
import com.springapp.common.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("logDao")
public class SysLogDaoImpl extends BaseDaoImpl<SysLog> implements SysLogDao {
}
