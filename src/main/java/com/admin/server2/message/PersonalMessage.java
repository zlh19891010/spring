package com.admin.server2.message;

public class PersonalMessage extends BaseMessage {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 消息内容
	 */
	private String message;



	public PersonalMessage(Integer type,String roleId, String message) {
		this.type=type;
		this.roleId = roleId;
		this.message = message;

	}


	public PersonalMessage() {
	}






	public String getRoleId() {

		return roleId;
	}



	public void setRoleId(String roleId) {

		this.roleId = roleId;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
