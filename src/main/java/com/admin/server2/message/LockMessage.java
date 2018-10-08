package com.admin.server2.message;

public class LockMessage extends BaseMessage {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String roleId;
	/**
	 * 封号时长 （秒）
	 */
	private Long duration;




	public LockMessage(Integer type,String roleId, Long duration) {
		this.type=type;
		this.roleId = roleId;
		this.duration = duration;
	}

	public LockMessage() {
	}



	public String getRoleId() {

		return roleId;
	}


	public void setRoleId(String roleId) {

		this.roleId = roleId;
	}

	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}


}
