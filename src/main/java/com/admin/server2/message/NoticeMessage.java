package com.admin.server2.message;

public class NoticeMessage extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 消息内容
	 */
	private String message;

	
	
	
	public NoticeMessage(Integer type,String message) {
		this.message = message;
		this.type=type;
	}

	public NoticeMessage() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
