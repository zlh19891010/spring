package com.admin.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.service.account.QpaccountdbService;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;


/**
 * 文件名称： com.admin.controller.account.LoginController.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年12月3日</br>
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
@RestController
@RequestMapping("login")
public class GLoginController extends BaseController {

	@Autowired
	private QpaccountdbService qpaccountdbService;
	/**
	 *
	 *
	 * 方法描述: [另一种获取getAccessToken.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月15日-下午10:17:11<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param code
	 * @return
	 * ResultJson
	 *
	 */
	@Login(action = Action.Skip)
	@RequestMapping("getAccessToken")
	public void gameLoginAuth(){
		qpaccountdbService.getAccessToken(request,response);
	}
}
