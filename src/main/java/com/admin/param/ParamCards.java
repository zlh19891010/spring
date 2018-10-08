package com.admin.param;

import java.io.Serializable;


/**
 * 文件名称： com.admin.param.ParamCards.java</br>
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
public class ParamCards implements Serializable {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 1L;


	private String userId;

	private String strAccounts;

	private String czkssj;

	private String czjssj;




	public String getStrAccounts() {

		return strAccounts;
	}



	public void setStrAccounts(String strAccounts) {

		this.strAccounts = strAccounts;
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

	/**
	 * 当前页
	 */
	private int current;
	/**
	 * 当前页显示条数
	 */
	private int size;




	public String getUserId() {

		return userId;
	}


	public void setUserId(String userId) {

		this.userId = userId;
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
