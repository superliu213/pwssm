package com.springapp.mvc.entity;

import com.springapp.common.entity.BaseEntity;

import java.util.Date;
import java.sql.Timestamp;

public class Group extends BaseEntity{

	//序号
	private Long id;
	//版本编号
	private Integer version;
	//机构编码
	private String groupId;
	//机构名称
	private String groupName;
	//机构层号
	private Short groupLever;
	//父机构编码
	private String groupParentId;
	//排序编码
	private Integer orderNo;
	//备注
	private String remark;

	public Group() {}

	public Group(Long id, Integer version, String groupId, String groupName, Short groupLever, String groupParentId, Integer orderNo, String remark) {
		this.id = id;
		this.version = version;
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupLever = groupLever;
		this.groupParentId = groupParentId;
		this.orderNo = orderNo;
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

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupLever(Short groupLever) {
		this.groupLever = groupLever;
	}

	public Short getGroupLever() {
		return groupLever;
	}

	public void setGroupParentId(String groupParentId) {
		this.groupParentId = groupParentId;
	}

	public String getGroupParentId() {
		return groupParentId;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
}
