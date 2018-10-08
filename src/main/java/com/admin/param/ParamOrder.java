package com.admin.param;

import java.io.Serializable;


/**
 * 文件名称： com.admin.param.ParamOrder.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年12月16日</br>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 *
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者        日期       修改内容<br/>
 *
 *
 * ================================================<br/>
 *  Copyright (c) 2010-2011 .All rights reserved.<br/>
 */
public class ParamOrder implements Serializable {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 1L;
	/**
	 * 游戏订单号
	 */
	private String privateData;
	/**
	 * 授权码
	 */
	private String sAuthCode;

	/**
	 * AnySDK 产生的订单号
	 */
	private String orderId;
	/**
	 * 支付时间  查询开始时间
	 */
	private String czkssj;
	/**
	 * 支付时间  查询结束时间
	 */
	private String czjssj;
	/**
	 * 角色ID
	 */
	private String gameUserId;

	/**
	 * 当前页
	 */
	private int current;
	/**
	 * 当前页显示条数
	 */
	private int size;

	public String getPrivateData() {

		return privateData;
	}

	public void setPrivateData(String privateData) {

		this.privateData = privateData;
	}






	public String getsAuthCode() {

		return sAuthCode;
	}


	public void setsAuthCode(String sAuthCode) {

		this.sAuthCode = sAuthCode;
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


	public String getGameUserId() {

		return gameUserId;
	}

	public void setGameUserId(String gameUserId) {

		this.gameUserId = gameUserId;
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


	public String getOrderId() {

		return orderId;
	}


	public void setOrderId(String orderId) {

		this.orderId = orderId;
	}



}
