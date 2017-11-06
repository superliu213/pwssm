package com.springapp.mvc.entity;

import com.springapp.common.entity.BaseEntity;

import java.util.Date;
import java.sql.Timestamp;

public class Role extends BaseEntity{

	//序号
	private Long id;
	//版本编号
	private Integer version;
	//角色编码
	private String roleId;
	//角色描述
	private String roleDesc;
	//备注
	private String remark;

	public Role() {}

	public Role(Long id, String roleId, String roleDesc, String remark) {
		this.id = id;
		this.version = 0;
		this.roleId = roleId;
		this.roleDesc = roleDesc;
		this.remark = remark;
	}

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

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
}
