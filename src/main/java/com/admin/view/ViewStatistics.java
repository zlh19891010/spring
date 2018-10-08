package com.admin.view;

import java.io.Serializable;


/**
 * 文件名称： com.admin.view.ViewStatistics.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年12月19日</br>
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
public class ViewStatistics implements Serializable {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 玩家总数
	 */
	private String pCount;
	/**
	 * 玩家充值总额
	 */
	private String pAmount;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getpCount() {

		return pCount;
	}

	public void setpCount(String pCount) {

		this.pCount = pCount;
	}

	public String getpAmount() {

		return pAmount;
	}

	public void setpAmount(String pAmount) {

		this.pAmount = pAmount;
	}



}
