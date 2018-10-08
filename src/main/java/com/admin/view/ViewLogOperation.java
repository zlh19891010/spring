package com.admin.view;

import java.io.Serializable;

public class ViewLogOperation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String id;
	
	private String type;
	
	private String status;
	
	private String account;
	
	private String sendContent;
	
	private String returnContent;
	
	private String createTime;
	
	private String operaterCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public String getReturnContent() {
		return returnContent;
	}

	public void setReturnContent(String returnContent) {
		this.returnContent = returnContent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOperaterCode() {
		return operaterCode;
	}

	public void setOperaterCode(String operaterCode) {
		this.operaterCode = operaterCode;
	}
	
	
	
}
