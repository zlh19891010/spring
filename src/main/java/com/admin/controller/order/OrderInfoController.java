package com.admin.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.param.ParamOrder;
import com.admin.service.order.OrderInfoService;
import com.admin.view.ViewOrder;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlh
 * @since 2017-12-14
 */
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController  extends BaseController{

	@Autowired
	private OrderInfoService orderInfoService;


	@RequestMapping("selectOrders")
	public Page<ViewOrder> selectOrders(@RequestBody ParamOrder paramOrder){

		return orderInfoService.selectOrders(paramOrder,getUserModel());
	}
}
