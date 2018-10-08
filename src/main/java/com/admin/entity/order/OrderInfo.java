package com.admin.entity.order;

import com.admin.entity.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 *
 * </p>
 *
 * @author zlh
 * @since 2017-12-14
 */
@TableName("order_info")
public class OrderInfo extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单号，AnySDK 产生的订单号
	 */
	@TableField("order_id")
	private String orderId;
	@TableField("product_num")
	private String productNum;
	/**
	 * 要购买商品数量（暂不提供具体数量）
	 */
	@TableField("product_count")
	private String productCount;
	/**
	 * 支付金额，单位元 值根据不同渠道的要求可能为浮点类型
	 */
	private String amount;
	/**
	 * 支付状态，1 为成功，非1则为其他异常状态，游服请在成功的状态下发货
	 */
	@TableField("pay_status")
	private String payStatus;
	/**
	 * 支付时间，YYYY-mm-dd HH:ii:ss 格式
	 */
	@TableField("pay_time")
	private String payTime;
	/**
	 * 用户 ID，用户系统的用户 ID
	 */
	@TableField("user_id")
	private String userId;
	/**
	 * 支付方式 详见
	 */
	@TableField("order_type")
	private String orderType;
	/**
	 * 游戏内用户 ID，支付时传入的 Role_Id 参数
	 */
	@TableField("game_user_id")
	private String gameUserId;
	/**
	 * 服务器 ID，支付时传入的 Server_Id 参数
	 */
	@TableField("server_id")
	private String serverId;
	/**
	 * 商品名称，支付时传入的 Product_Name 参数
	 */
	@TableField("product_name")
	private String productName;
	/**
	 * 商品 ID，支付时传入的 Product_Id 参数
	 */
	@TableField("product_id")
	private String productId;
	/**
	 * product_id 字段的值对应的渠道商品 ID
	 */
	@TableField("channel_product_id")
	private String channelProductId;
	/**
	 * 自定义参数，调用客户端支付函数时传入的EXT参数，透传给游戏服务器
	 */
	@TableField("private_data")
	private String privateData;
	/**
	 * 渠道编号
	 */
	@TableField("channel_number")
	private String channelNumber;
	/**
	 * 通用签名串，通用验签参考签名算法
	 */
	private String sign;
	/**
	 * 渠道服务器通知 AnySDK 时请求的参数
	 */
	private String source;
	/**
	 * 增强签名串，验签参考签名算法（有增强密钥的游戏有效）
	 */
	@TableField("enhanced_sign")
	private String enhancedSign;
	/**
	 * 渠道订单号，如果渠道通知过来的参数没有渠道订单号则为空。
	 */
	@TableField("channel_order_id")
	private String channelOrderId;
	/**
	 * 游戏 ID，AnySDK 服务端为游戏分配的唯一标识
	 */
	@TableField("game_id")
	private String gameId;
	/**
	 * 插件 ID，AnySDK 插件数字唯一标识
	 */
	@TableField("plugin_id")
	private String pluginId;
	/**
	 * 货币类型，只有企业版才有这个字段，通用版不会有这个字段
	 */
	@TableField("currency_type")
	private String currencyType;




	public String getProductNum() {

		return productNum;
	}


	public void setProductNum(String productNum) {

		this.productNum = productNum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getGameUserId() {
		return gameUserId;
	}

	public void setGameUserId(String gameUserId) {
		this.gameUserId = gameUserId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEnhancedSign() {
		return enhancedSign;
	}

	public void setEnhancedSign(String enhancedSign) {
		this.enhancedSign = enhancedSign;
	}

	public String getChannelOrderId() {
		return channelOrderId;
	}

	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPluginId() {
		return pluginId;
	}

	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

}
