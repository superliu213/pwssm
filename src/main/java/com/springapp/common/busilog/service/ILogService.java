package com.springapp.common.busilog.service;

/**
 * 这个接口记录用户操作日志。
 */
public abstract interface ILogService {

	public abstract void doBizLog(Short LogType, String message, String userId, String ipAddress);

	public abstract void doBizWarningLog(Short LogType, String message, String userId, String ipAddress);

	public abstract void doBizErrorLog(Short LogType, String message, String userId, String ipAddress);

	public abstract void doBizWarningLog(Short LogType, String message, String userId, Throwable exception, String ipAddress);

	public abstract void doBizErrorLog(Short LogType, String message, String userId, Throwable exception, String ipAddress);
}
