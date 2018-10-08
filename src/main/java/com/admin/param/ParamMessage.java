package com.admin.param;

import java.io.Serializable;

public class ParamMessage implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String account;

	private String message;

	private String type;

	private String strnickname;




	public String getStrnickname() {

		return strnickname;
	}


	public void setStrnickname(String strnickname) {

		this.strnickname = strnickname;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String getType() {

		return type;
	}


	public void setType(String type) {

		this.type = type;
	}


}
