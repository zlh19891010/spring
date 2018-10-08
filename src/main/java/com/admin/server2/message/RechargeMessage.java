package com.admin.server2.message;

/**
 * 文件名称： com.admin.server2.message.RechargeMessage.java</br>
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
public class RechargeMessage extends BaseMessage {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 充值点数
	 */
	private  Float amount;
	/**
	 * 用户 ID，用户系统的用户 ID
	 */
	private String userId;
	/**
	 * 游戏内用户 ID，支付时传入的 Role_Id 参数
	 */
	private String gameUserId;
	/**
	 * 商品 ID，支付时传入的 Product_Id 参数
	 */
	private String productId;
	/**
	 * product_id 字段的值对应的渠道商品 ID
	 */
	private String channelProductId;
	/**
	 * 自定义参数，调用客户端支付函数时传入的EXT参数，透传给游戏服务器
	 */
	private String privateData;






	public Float getAmount() {

		return amount;
	}



	public void setAmount(Float amount) {

		this.amount = amount;
	}


	public String getUserId() {

		return userId;
	}


	public void setUserId(String userId) {

		this.userId = userId;
	}


	public String getGameUserId() {

		return gameUserId;
	}


	public void setGameUserId(String gameUserId) {

		this.gameUserId = gameUserId;
	}


	public String getProductId() {

		return productId;
	}


	public void setProductId(String productId) {

		this.productId = productId;
	}


	public String getChannelProductId() {

		return channelProductId;
	}


	public void setChannelProductId(String channelProductId) {

		this.channelProductId = channelProductId;
	}


	public String getPrivateData() {

		return privateData;
	}


	public void setPrivateData(String privateData) {

		this.privateData = privateData;
	}


}
