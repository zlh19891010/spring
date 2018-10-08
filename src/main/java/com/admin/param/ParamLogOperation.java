package com.admin.param;

import java.io.Serializable;

public class ParamLogOperation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String account;
	/**
	 * 当前页
	 */
	private int current;
	/**
	 * 当前页显示条数
	 */
	private int size;
	
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
