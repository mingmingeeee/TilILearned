package com.ssafy.member.model;

public class MemberDto {
	
	private String userId;
	private String userName;
	private String userPwd;
	private String emailId;
	private String emailDomain;
	private String joinDate;
	
	public MemberDto() {}

	public MemberDto(String userId, String userName, String userPwd, String emailId, String emailDomain,
			String joinDate) {
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.joinDate = joinDate;
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
		StringBuilder builder = new StringBuilder();
		builder.append("MemberDto [userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userPwd=");
		builder.append(userPwd);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", emailDomain=");
		builder.append(emailDomain);
		builder.append(", joinDate=");
		builder.append(joinDate);
		builder.append("]");
		return builder.toString();
	}

}
