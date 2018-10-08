package com.admin.server2.message;

public class AuthCodeMessage extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 0 表示 授权码有效  其他值表示无效
	 */
	private Integer code;
	/**
	 * 授权码
	 */
	private String sAuthCode;
	/**
	 * 协议发送过来的
	 */
	private Integer socketId;

	
	
	
	public AuthCodeMessage(Integer type,Integer code, String sAuthCode, Integer socketId) {
		this.type=type;
		this.code = code;
		this.sAuthCode = sAuthCode;
		this.socketId = socketId;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getsAuthCode() {
		return sAuthCode;
	}

	public void setsAuthCode(String sAuthCode) {
		this.sAuthCode = sAuthCode;
	}

	public Integer getSocketId() {
		return socketId;
	}

	public void setSocketId(Integer socketId) {
		this.socketId = socketId;
	}
	
	
}
