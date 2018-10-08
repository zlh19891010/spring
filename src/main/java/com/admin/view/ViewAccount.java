package com.admin.view;

import java.io.Serializable;
import java.util.Date;

public class ViewAccount implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账号名
	 */
	private String strAccounts;
	/**
	 * 密码MD5
	 */
	private String strPassword;
	/**
	 * 登入IP地址
	 */
	private String strClientIP;
	/**
	 * 机器识别码
	 */
	private String strMachineID;
	/**
	 * 昵称
	 */
	private String strNickName;
	/**
	 * 头像路径
	 */
	private String strIcon;
	/**
	 * 第三方登入唯一ID
	 */
	private String strOpenID;
	/**
	 * 第三方登入token
	 */
	private String strToken;

	private String sAuthCode;

	/**
	 * 最后登入时间
	 */
	private Date tmLastTime;
	/**
	 * 是否在线 1在线  0不在线
	 */
	private Integer bOnline;
	/**
	 * 房卡数量
	 */
	private Integer nCards;
	/**
	 * nState 账户状态  0正常  1禁止
	 */
	private Integer nState;
	/**
	 * UID
	 */
	private Integer uid;
	/**
	 * 处理过的UID
	 */
	private String suid;

	/**
	 * 性别 1男 2女
	 */
	private Integer nSex;





	public String getSuid() {

		return suid;
	}



	public void setSuid(String suid) {

		this.suid = suid;
	}


	public String getsAuthCode() {

		return sAuthCode;
	}


	public void setsAuthCode(String sAuthCode) {

		this.sAuthCode = sAuthCode;
	}

	public Integer getUid() {

		return uid;
	}

	public void setUid(Integer uid) {

		this.uid = uid;
	}
	public String getStrAccounts() {
		return strAccounts;
	}
	public void setStrAccounts(String strAccounts) {
		this.strAccounts = strAccounts;
	}
	public String getStrPassword() {
		return strPassword;
	}
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	public String getStrClientIP() {
		return strClientIP;
	}
	public void setStrClientIP(String strClientIP) {
		this.strClientIP = strClientIP;
	}
	public String getStrMachineID() {
		return strMachineID;
	}
	public void setStrMachineID(String strMachineID) {
		this.strMachineID = strMachineID;
	}
	public String getStrNickName() {
		return strNickName;
	}
	public void setStrNickName(String strNickName) {
		this.strNickName = strNickName;
	}
	public String getStrIcon() {
		return strIcon;
	}
	public void setStrIcon(String strIcon) {
		this.strIcon = strIcon;
	}
	public String getStrOpenID() {
		return strOpenID;
	}
	public void setStrOpenID(String strOpenID) {
		this.strOpenID = strOpenID;
	}
	public String getStrToken() {
		return strToken;
	}
	public void setStrToken(String strToken) {
		this.strToken = strToken;
	}
	public Date getTmLastTime() {
		return tmLastTime;
	}
	public void setTmLastTime(Date tmLastTime) {
		this.tmLastTime = tmLastTime;
	}
	public Integer getbOnline() {
		return bOnline;
	}
	public void setbOnline(Integer bOnline) {
		this.bOnline = bOnline;
	}
	public Integer getnCards() {
		return nCards;
	}
	public void setnCards(Integer nCards) {
		this.nCards = nCards;
	}
	public Integer getnState() {
		return nState;
	}
	public void setnState(Integer nState) {
		this.nState = nState;
	}
	public Integer getnSex() {
		return nSex;
	}
	public void setnSex(Integer nSex) {
		this.nSex = nSex;
	}


}
