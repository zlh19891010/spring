package com.admin.entity.account;

import java.util.Date;

import com.admin.entity.BaseModel;

/**
 * <p>
 *
 * </p>
 *
 * @author zlh
 * @since 2017-11-11
 */
public class Qpaccountdb extends BaseModel {

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
	 * 性别 1男 2女
	 */
	private Integer nSex;
	/**
	 * 积分
	 */
	private Integer nScore;
	/**
	 * 授权码
	 */
	private String sAuthCode;
	/**
	 * UID
	 */
	private Integer uid;





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

	public Integer getBOnline() {
		return bOnline;
	}

	public void setBOnline(Integer bOnline) {
		this.bOnline = bOnline;
	}

	public Integer getNCards() {
		return nCards;
	}

	public void setNCards(Integer nCards) {
		this.nCards = nCards;
	}

	public Integer getNState() {
		return nState;
	}

	public void setNState(Integer nState) {
		this.nState = nState;
	}

	public Integer getNSex() {
		return nSex;
	}

	public void setNSex(Integer nSex) {
		this.nSex = nSex;
	}

	public Integer getnScore() {
		return nScore;
	}

	public void setnScore(Integer nScore) {
		this.nScore = nScore;
	}

	public String getsAuthCode() {
		return sAuthCode;
	}

	public void setsAuthCode(String sAuthCode) {
		this.sAuthCode = sAuthCode;
	}


}
