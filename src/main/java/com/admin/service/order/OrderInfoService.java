package com.admin.service.order;

import javax.servlet.http.HttpServletRequest;

import com.admin.entity.order.OrderInfo;
import com.admin.param.ParamOrder;
import com.admin.view.ViewOrder;
import com.admin.view.ViewUserModel;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlh
 * @since 2017-12-14
 */
public interface OrderInfoService extends IService<OrderInfo> {


	String addOrder(HttpServletRequest request)throws Exception;


	Page<ViewOrder> selectOrders(ParamOrder paramOrder,ViewUserModel userModel);
}
