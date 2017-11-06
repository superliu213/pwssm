package com.springapp.common.busilog.service;

import com.springapp.common.busilog.dao.SysLogDao;
import com.springapp.common.entity.SysLog;
import com.springapp.common.exceptions.ApplicationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 同步记录日志。对应的数据库表是SYS_LOG。 日志消息的最大长度，受到数据库设计的限制。
 * 建议：在Oracle库中，取Varchar2数据类型的最大长度（2000个字符）， UTF8编码情况下，汉字可以存储2000/3*2=1333个字符。
 * 如果需要记录的日志消息长度超过数据库能容纳的长度，Oracle下会抛出异常。
 */

@Component("syncLogService")
public class SyncLogService implements ILogService {

    @Autowired
    SysLogDao sysLogDao;

    private Log logger = LogFactory.getLog (SyncLogService.class);

	public static final short LOG_ERROR = 3;
	public static final short LOG_WARNING = 2;
	public static final short LOG_INFO = 1;

	@Override
	public void doBizLog(Short LogType, String message, String userId, String ipAddress) {
		logger.info(message);
		log(LogType, LOG_INFO, userId, message, ipAddress);
	}

	@Override
	public void doBizWarningLog(Short LogType, String message, String userId, String ipAddress) {
		logger.warn(message);
		log(LogType, LOG_WARNING, userId, message, ipAddress);
	}

	@Override
	public void doBizWarningLog(Short LogType, String message, String userId, Throwable exception, String ipAddress) {
		logger.warn(message, exception);
		log(LogType, LOG_WARNING, userId, message, ipAddress);
	}

	@Override
	public void doBizErrorLog(Short LogType, String message, String userId, String ipAddress) {
		this.logger.error(message);
		log(LogType, LOG_ERROR, userId, message, ipAddress);
	}

	@Override
	public void doBizErrorLog(Short LogType, String message, String userId, Throwable exception, String ipAddress) {
		logger.error(message, exception);
		log(LogType, LOG_ERROR, userId, message, ipAddress);
	}

	private void log(Short LogType, Short logLevel, String userId, String message, String ipAddress) {
		SysLog sysLog = getSysLog(LogType, logLevel, userId, message, ipAddress);
		if (sysLog == null) {
			logger.debug("日志实体为空。");
			throw new ApplicationException("创建SysLog对象失败");
		}

        sysLogDao.insert(sysLog);
	}

	/**
	 * 模板方法，派生类可以创建TsyOpLog的子类
	 *
	 * @return
	 */
	protected SysLog createLog() {
		return new SysLog();
	}

	private SysLog getSysLog(Short logType, Short logLevel, String userId, String message, String ipAddress) {
		SysLog log = createLog();
		log.setOccurTime(new Timestamp(System.currentTimeMillis()));
		log.setOperatorId(userId);
		log.setLogType(logType);
		log.setLogLevel(logLevel);
		log.setLogDesc(message);
		log.setIpAddress(ipAddress);
		return log;
	}

}
