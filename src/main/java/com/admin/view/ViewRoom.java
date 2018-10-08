package com.admin.view;

import java.io.Serializable;
import java.util.Set;

public class ViewRoom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 房间ID
	 */
	private String roomId;
	/**
	 * 主键
	 */
	private String guid;
	/**
	 * 拥有者
	 */
	private String owner;
	
	/**
	 * 数据
	 */
	private byte[] Data;
	
	/**
	 * 时间
	 */
	private String Time;
	/**
	 * 人数
	 */
	private String pCount;
	/**
	 * 对局数
	 */
	private String dCount;
	
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 积分
	 */
	private String score;
	/**
	 *庄家
	 */
	private String banker;
	
	private Set<ViewPerson> personList;
	
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public byte[] getData() {
		return Data;
	}
	public void setData(byte[] data) {
		Data = data;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getpCount() {
		return pCount;
	}
	public void setpCount(String pCount) {
		this.pCount = pCount;
	}
	public String getdCount() {
		return dCount;
	}
	public void setdCount(String dCount) {
		this.dCount = dCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getBanker() {
		return banker;
	}
	public void setBanker(String banker) {
		this.banker = banker;
	}
	public Set<ViewPerson> getPersonList() {
		return personList;
	}
	public void setPersonList(Set<ViewPerson> personList) {
		this.personList = personList;
	}
	
	
	
}
