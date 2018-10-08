package com.admin.server2.message;

/**
 * 文件名称： com.admin.server2.message.UnBinkAccount.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年12月23日</br>
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
public class UnBinkAccountMessage extends BaseMessage {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 1L;

	private String roleId;

	public UnBinkAccountMessage(Integer type,String roleId) {
		this.type=type;
		this.roleId = roleId;
	}

	public String getRoleId() {

		return roleId;
	}


	public void setRoleId(String roleId) {

		this.roleId = roleId;
	}


}
