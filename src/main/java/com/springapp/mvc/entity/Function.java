package com.springapp.mvc.entity;

import com.springapp.common.entity.BaseEntity;

import java.util.Date;
import java.sql.Timestamp;

public class Function extends BaseEntity{

	//序号
	private Long id;
	//版本编号
	private Integer version;
	//菜单编码
	private String functionId;
	//菜单名称
	private String functionName;
	//菜单类型
	private Short functionType;
	//父菜单编码
	private String functionParentId;
	//菜单URL
	private String functionUrl;
	//排序编码
	private Integer orderNo;
	//图标
	private String functionLogo;
	//按钮位置
	private String buttonPosition;
	//备注
	private String remark;

	public Function() {}

	public Function(Long id, String functionId, String functionName, Short functionType, String functionParentId, String functionUrl, Integer orderNo, String functionLogo, String buttonPosition, String remark) {
		this.id = id;
		this.version = 0;
		this.functionId = functionId;
		this.functionName = functionName;
		this.functionType = functionType;
		this.functionParentId = functionParentId;
		this.functionUrl = functionUrl;
		this.orderNo = orderNo;
		this.functionLogo = functionLogo;
		this.buttonPosition = buttonPosition;
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

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionType(Short functionType) {
		this.functionType = functionType;
	}

	public Short getFunctionType() {
		return functionType;
	}

	public void setFunctionParentId(String functionParentId) {
		this.functionParentId = functionParentId;
	}

	public String getFunctionParentId() {
		return functionParentId;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setFunctionLogo(String functionLogo) {
		this.functionLogo = functionLogo;
	}

	public String getFunctionLogo() {
		return functionLogo;
	}

	public void setButtonPosition(String buttonPosition) {
		this.buttonPosition = buttonPosition;
	}

	public String getButtonPosition() {
		return buttonPosition;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
}
