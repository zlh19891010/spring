package com.admin.server2.model;

import java.io.Serializable;

public class MessageObj implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//消息类型
	private String type;
	//帐号
	private String account;
	//消息内容
	private String Body;

	public MessageObj(){

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	public MessageObj(String type, String account, String body) {
		this.type = type;
		this.account = account;
		Body = body;
	}



}
