package com.admin.server2.message;

public class UnLockMessage extends BaseMessage {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	private String roleId;




	public UnLockMessage(Integer type,String roleId) {
		this.type=type;
		this.roleId = roleId;
	}


	public UnLockMessage() {
	}



	public String getRoleId() {

		return roleId;
	}



	public void setRoleId(String roleId) {

		this.roleId = roleId;
	}





}
