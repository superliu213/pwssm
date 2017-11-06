package com.springapp.mvc.entity;

import com.springapp.common.entity.BaseEntity;

import java.util.Date;
import java.sql.Timestamp;

public class UserGroup extends BaseEntity{

	//序号
	private Long id;
	//版本编号
	private Integer version;
	//用户编码
	private String userId;
	//机构编码
	private String groupId;

	public UserGroup() {}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupId() {
		return groupId;
	}
}
