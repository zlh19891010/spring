/**
 * Project Name:vr-admin-api
 * File Name:UserModel.java
 * Package Name:com.sd.vr.admin.api.model
 * Date:2017年3月2日下午1:59:27
 *
 */

package com.admin.view;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName:UserModel <br/>
 * Function: 用户信息视图. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年3月2日 下午1:59:27 <br/>
 * @author   刘成
 * @version
 * @since    JDK 1.7
 * @see
 */
@SuppressWarnings("serial")
public class ViewUserModel implements Serializable {



	/**
	 * 用户id
	 */
	private String id;

	/**
	 * 账户
	 */
	private String zh;

	/**
	 * 登录名
	 */
	private String loginId;

	/**
	 * 机构ID
	 */
	private String jgId;

	/**
	 * 菜单
	 */
	private List<ViewTGlptXtglMoudle> listMoudleView;

	/**
	 * 头像
	 */
	private String tx;

	/**
	 * 角色id
	 */
	private String jsId;

	/**
	 * 用户类型
	 */
	private String yhlx;

	/**
	 * 授权码
	 */
	private String sAuthCode;
	/**
	 * 点卡数量
	 */
	private String cards;




	public String getYhlx() {
		return yhlx;
	}


	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}


	public String getZh() {
		return zh;
	}


	public void setZh(String zh) {
		this.zh = zh;
	}


	public List<ViewTGlptXtglMoudle> getListMoudle() {
		return listMoudleView;
	}


	public void setListMoudle(List<ViewTGlptXtglMoudle> listMoudleView) {
		this.listMoudleView = listMoudleView;
	}


	public String getTx() {
		return tx;
	}


	public void setTx(String tx) {
		this.tx = tx;
	}


	public String getJsId() {
		return jsId;
	}


	public void setJsId(String jsId) {
		this.jsId = jsId;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getJgId() {
		return jgId;
	}


	public void setJgId(String jgId) {
		this.jgId = jgId;
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

