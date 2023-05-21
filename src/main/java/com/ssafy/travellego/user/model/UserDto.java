package com.ssafy.travellego.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class UserDto {

	@ApiModelProperty(value = "회원 아이디")
	private String userId;

	@ApiModelProperty(value = "회원 이름")
	private String userName;

	@ApiModelProperty(value = "회원 비밀번호")
	private String userPwd;

	@ApiModelProperty(value = "회원 이메일")
	private String emailId;

	@ApiModelProperty(value = "회원 가입일")
	private String emailDomain;

	@ApiModelProperty(value = "회원 가입일")
	private String joinDate;

	@ApiModelProperty(value = "회원 권한")
	private int userRole;
	
	
	public UserDto() {
		super();
	}
	
	

	public UserDto(String userId, String userName, String userPwd, String emailId, String emailDomain) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
	}



	public UserDto(String userId, String userName, String emailId, String emailDomain) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
	}



	public UserDto(String userId, String userName, String userPwd, String emailId, String emailDomain,
			String joinDate, int userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.joinDate = joinDate;
		this.userRole = userRole;
	}

	public UserDto(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}


	public int getuserRole() {
		return userRole;
	}
	
	public void setuserRole(int userRole) {
		this.userRole = userRole;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", emailId="
				+ emailId + ", emailDomain=" + emailDomain + ", joinDate=" + joinDate + "]";
	}

}
