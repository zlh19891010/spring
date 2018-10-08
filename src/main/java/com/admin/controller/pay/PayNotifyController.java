package com.admin.controller.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.AnySDK.PayNotify;
import com.admin.common.BaseController;
import com.admin.common.CodeConstants;
import com.admin.service.order.OrderInfoService;
import com.admin.utils.CommonUtil;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;


/**
 * 文件名称： com.admin.controller.pay.PayNotifyController.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年12月14日</br>
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
@RequestMapping("pay")
public class PayNotifyController extends BaseController {


	@Autowired
	private OrderInfoService  orderInfoService;
	/**
	 *
	 *
	 * 方法描述: [支付通知.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月14日-下午7:04:09<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * String
	 *
	 */
	@Login(action = Action.Skip)
	@RequestMapping("Notify")
	public String Notify(){
		try{
			//验证IP是否是anySDK的IP通知
			/*if(!CommonUtil.checkIp(request)){
			return CodeConstants.UNKOWN;
		}*/
			String paramValues =	CommonUtil.getValues(request);
			PayNotify paynotify = new PayNotify();
			paynotify.setPrivateKey(CodeConstants.PRIVATE_KEY);
			System.out.println("paynotify.getSign(paramValues):"+paynotify.getSign(paramValues));
			if (paynotify.checkSign(paramValues, CommonUtil.originSign)){

				orderInfoService.addOrder(request);
				System.out.println("验证签名成功\n");
			} else {
				System.out.println("验证签名失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			return CodeConstants.UNKOWN;
		}
		return CodeConstants.OK;
	}


}
