/**
 * Project Name:vr-admin-api
 * File Name:ViewYhxx.java
 * Package Name:com.sd.vr.admin.api.view
 * Date:2017年3月1日下午1:56:59
 *
 */

package com.admin.view;

import java.io.Serializable;

/**
 * ClassName:ViewYhxx <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年3月1日 下午1:56:59 <br/>
 * @author   shaopei
 * @version
 * @since    JDK 1.7
 * @see
 */
public class ViewYhxx implements Serializable {

	/**
	 * serialVersionUID:TODO(标识).
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;

	/**
	 * 登录名
	 */
	private String loginId;
	/**
	 * 账号
	 */
	private String zh;
	/**
	 * 姓名
	 */
	private String yhmc;
	/**
	 * 邮箱
	 */
	private String yx;
	/**
	 * 手机号
	 */
	private String sjh;
	/**
	 * 用户状态（01未启用，02已启用，03已停用）
	 */
	private String yhzt;

	/**
	 * 角色id
	 */
	private String jsid;
	/**
	 * 角色名称
	 */
	private String jsmc;

	/**
	 * 操作级别
	 */
	private String czjb;

	/**
	 * 上级管理员id
	 */
	private String sjglyid;

	/**
	 * 授权码
	 */
	private String sAuthCode;
	/**
	 * 点卡数量
	 */
	private String cards;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getYx() {
		return yx;
	}
	public void setYx(String yx) {
		this.yx = yx;
	}
	public String getSjh() {
		return sjh;
	}
	public void setSjh(String sjh) {
		this.sjh = sjh;
	}
	public String getYhzt() {
		return yhzt;
	}
	public void setYhzt(String yhzt) {
		this.yhzt = yhzt;
	}
	public String getJsid() {
		return jsid;
	}
	public void setJsid(String jsid) {
		this.jsid = jsid;
	}
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getCzjb() {
		return czjb;
	}
	public void setCzjb(String czjb) {
		this.czjb = czjb;
	}
	public String getSjglyid() {
		return sjglyid;
	}
	public void setSjglyid(String sjglyid) {
		this.sjglyid = sjglyid;
	}

	public String getsAuthCode() {

		return sAuthCode;
	}

	public void setsAuthCode(String sAuthCode) {

		this.sAuthCode = sAuthCode;
	}

	public String getCards() {

		return cards;
	}

	public void setCards(String cards) {

		this.cards = cards;
	}


}

