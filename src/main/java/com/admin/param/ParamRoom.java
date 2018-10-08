package com.admin.param;

import java.io.Serializable;

public class ParamRoom implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 房间ID
	 */
	private String roomId;
	
	/**
     * 操作开始时间
     */
    private String czkssj;
    /**
     * 操作结束时间
     */
    private String czjssj;
	
	/**
	 * 当前页
	 */
	private int current;
	/**
	 * 当前页显示条数
	 */
	private int size;
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getCzkssj() {
		return czkssj;
	}
	public void setCzkssj(String czkssj) {
		this.czkssj = czkssj;
	}
	public String getCzjssj() {
		return czjssj;
	}
	public void setCzjssj(String czjssj) {
		this.czjssj = czjssj;
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
