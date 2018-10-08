package com.admin.view;

import java.io.Serializable;


/**
 * 文件名称： com.admin.view.ViewCards.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年12月12日</br>
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
public class ViewCards implements Serializable {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 1L;

	private String yhmc;

	private String card;

	private String strAccounts;

	private String strNickName;

	private String createTime;




	public String getYhmc() {

		return yhmc;
	}



	public void setYhmc(String yhmc) {

		this.yhmc = yhmc;
	}


	public String getCard() {

		return card;
	}


	public void setCard(String card) {

		this.card = card;
	}


	public String getStrAccounts() {

		return strAccounts;
	}


	public void setStrAccounts(String strAccounts) {

		this.strAccounts = strAccounts;
	}




	public String getStrNickName() {

		return strNickName;
	}



	public void setStrNickName(String strNickName) {

		this.strNickName = strNickName;
	}



	public String getCreateTime() {

		return createTime;
	}



	public void setCreateTime(String createTime) {

		this.createTime = createTime;
	}







}
