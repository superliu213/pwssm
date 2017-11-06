package com.springapp.common.entity;

import com.springapp.common.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.Date;

public class SysLog extends BaseEntity {

    private Long id;

	private Date occurTime;

	private String operatorId;

	private Short logType;

	private Short logLevel;

	private String logDesc;

	private String ipAddress;

	public SysLog() {
		super();
	}

	public SysLog(Long id, Date occurTime, String operatorId, Short logType, Short logLevel) {
		super();
		this.id = id;
		this.occurTime = occurTime;
		this.operatorId = operatorId;
		this.logType = logType;
		this.logLevel = logLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Short getLogType() {
		return logType;
	}

	public void setLogType(Short logType) {
		this.logType = logType;
	}

	public Short getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(Short logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "SysLog [id=" + id + ", occurTime=" + occurTime + ", operatorId=" + operatorId + ", logType=" + logType
				+ ", logLevel=" + logLevel + ", logDesc=" + logDesc + ", ipAddress=" + ipAddress + "]";
	}

}
