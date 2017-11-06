package com.springapp.mvc.entity;

import com.springapp.common.entity.BaseEntity;

import java.util.Date;
import java.sql.Timestamp;

public class User extends BaseEntity{

	//序号
	private Long id;
	//版本编号
	private Integer version;
	//用户编码
	private String userId;
	//用户密码
	private String userPassWord;
	//用户名称
	private String userName;
	//电话号码
	private String userTelephone;
	//用户邮箱
	private String userEmail;
	//出生日期
	private Date userBirthday;
	//身份证号
	private String userIdCard;
	//是否有效
	private Short ifValid;
	//用户有效期
	private Date userValidityPeriod;
	//密码有效期
	private Date pwValidityPeriod;
	//备注
	private String remark;

	public User() {}

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

	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}

	public String getUserPassWord() {
		return userPassWord;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}

	public String getUserTelephone() {
		return userTelephone;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}

	public String getUserIdCard() {
		return userIdCard;
	}

	public void setIfValid(Short ifValid) {
		this.ifValid = ifValid;
	}

	public Short getIfValid() {
		return ifValid;
	}

	public void setUserValidityPeriod(Date userValidityPeriod) {
		this.userValidityPeriod = userValidityPeriod;
	}

	public Date getUserValidityPeriod() {
		return userValidityPeriod;
	}

	public void setPwValidityPeriod(Date pwValidityPeriod) {
		this.pwValidityPeriod = pwValidityPeriod;
	}

	public Date getPwValidityPeriod() {
		return pwValidityPeriod;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
}
