package com.admin.service.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.common.CodeConstants;
import com.admin.dao.order.OrderInfoMapper;
import com.admin.entity.order.OrderInfo;
import com.admin.param.ParamOrder;
import com.admin.server.SessionMap;
import com.admin.server2.message.PointCardMessage;
import com.admin.service.CommonService;
import com.admin.service.order.OrderInfoService;
import com.admin.utils.MessType;
import com.admin.view.ViewOrder;
import com.admin.view.ViewUserModel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlh
 * @since 2017-12-14
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {


	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	private CommonService commonService;

	static Map<String,Float[]> productMap =new HashMap<String,Float[]>();
	static {
		productMap.put("com.product.30",new Float[]{50f,11f});
		productMap.put("com.product.60",new Float[]{100f,24f});
		productMap.put("com.product.90",new Float[]{150f,38f});
		productMap.put("com.product.150",new Float[]{250f,70f});
		productMap.put("com.product.300",new Float[]{500f,150f});
	}

	/**
	 *
	 *
	 * 方法描述: [处理正常订单.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月14日-下午8:13:41<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public String addOrder(HttpServletRequest request) throws Exception{

		OrderInfo order =parseOrderParam(request);

		int count=orderInfoMapper.selectByOrder(order.getOrderId());
		if(count>0){
			return CodeConstants.OK;
		}

		Float[] array=productMap.get(order.getProductId());
		//验证价格
		if(array[0].floatValue()!=Float.valueOf(order.getAmount()).floatValue()){
			System.out.println("订单价格和配置价格不匹配：订单价："+order.getAmount()+"  配置价格："+productMap.get(order.getProductId()));
			return CodeConstants.OK;
		}
		order.setProductNum(String.valueOf(array[1]));
		//入库订单信息
		insert(order);
		//如果支付成功的要通知游戏服务器发点卡
		if(!CodeConstants.ORDER_SUCCESS.equals(order.getPayStatus())){
			return CodeConstants.OK;
		}
		String[] keys=new String[]{"carPark_id"};
		SessionMap sessionMap = SessionMap.newInstance();
		PointCardMessage mess =new PointCardMessage(MessType.PointCard.getValue(),order.getGameUserId(),Double.valueOf(array[1].floatValue()*CodeConstants.ORDER_RULE),CodeConstants.STYLE_RECHAGE);

		/*RechargeMessage mess = new RechargeMessage();
		mess.setAmount(productMap.get(order.getProductId())*CodeConstants.ORDER_RULE);
		mess.setChannelProductId(order.getChannelOrderId());
		mess.setGameUserId(order.getGameUserId());
		mess.setPrivateData(order.getPrivateData());
		mess.setProductId(order.getProductId());
		mess.setType(MessType.Recharge.getValue());
		mess.setUserId(order.getUserId());*/
		System.out.println("发送游戏服务器："+JSON.toJSON(mess).toString());
		sessionMap.sendMessage(keys,JSON.toJSON(mess).toString());
		//TODO是否要在订单表看游戏服务是否处理（数据库有字段还没处理）
		return CodeConstants.OK;
	}


	/**
	 *
	 *
	 * 方法描述: [获取订单的详情.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月14日-下午8:22:11<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param request
	 * @return
	 * OrderInfo
	 *
	 */
	private  OrderInfo parseOrderParam(HttpServletRequest request){

		OrderInfo order =new OrderInfo();
		order.setId(getKey());
		order.setOrderId(request.getParameter("order_id"));
		order.setProductCount(request.getParameter("product_count"));
		order.setAmount(request.getParameter("amount"));
		order.setPayStatus(request.getParameter("pay_status"));
		order.setPayTime(request.getParameter("pay_time"));
		order.setUserId(request.getParameter("user_id"));
		order.setOrderType(request.getParameter("order_type"));
		order.setGameUserId(request.getParameter("game_user_id"));
		order.setServerId(request.getParameter("server_id"));
		order.setProductName(request.getParameter("product_name"));
		order.setProductId(request.getParameter("product_id"));
		order.setChannelProductId(request.getParameter("channel_product_id"));
		order.setPrivateData(request.getParameter("private_data"));
		order.setChannelNumber(request.getParameter("channel_number"));
		order.setSign(request.getParameter("sign"));
		order.setSource(request.getParameter("source"));
		order.setEnhancedSign(request.getParameter("enhanced_sign"));
		order.setChannelOrderId(request.getParameter("channel_order_id"));
		order.setGameId(request.getParameter("game_id"));
		order.setPluginId(request.getParameter("plugin_id"));
		order.setCurrencyType(request.getParameter("currency_type"));
		order.setCreateTime(new Date());

		return order;
	}


	/**
	 *
	 *
	 * 方法描述: [查询订单列表.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月16日-下午2:47:16<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public Page<ViewOrder> selectOrders(ParamOrder paramOrder,ViewUserModel userModel) {

		logger.info("===开始发点卡记录分页===");
		Page<ViewOrder> page = new Page<ViewOrder>(paramOrder.getCurrent(), paramOrder.getSize());
		if(!CodeConstants.ADMIN.equals(userModel.getLoginId())){
			//如果没有授权码直接返回
			if(StringUtils.isBlank(userModel.getsAuthCode())){
				return page;
			}
			paramOrder.setsAuthCode(userModel.getsAuthCode());
		}
		List<ViewOrder> result = orderInfoMapper.selectOrders2(page,paramOrder);
		for(ViewOrder ord:result){
			ord.setGameUserId(commonService.getUid(ord.getGameUserId()));
		}

		page.setRecords(result);
		logger.info("===结束发点卡记录分页===");
		return page;
	}

}
