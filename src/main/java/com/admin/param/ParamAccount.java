package com.admin.param;

import com.admin.entity.account.Qpaccountdb;

public class ParamAccount extends Qpaccountdb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bOnline;
	
	private String nState;
	/**
	 * 当前页
	 */
	private int current;
	/**
	 * 当前页显示条数
	 */
	private int size;
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
	public String getbOnline() {
		return bOnline;
	}
	public void setbOnline(String bOnline) {
		this.bOnline = bOnline;
	}
	public String getnState() {
		return nState;
	}
	public void setnState(String nState) {
		this.nState = nState;
	}
	
	
}
