package com.admin.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.admin.entity.order.OrderInfo;
import com.admin.param.ParamOrder;
import com.admin.param.ParamStatistics;
import com.admin.view.ViewOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlh
 * @since 2017-12-14
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {


	List<ViewOrder> selectOrders(Page<ViewOrder> page,ParamOrder paramOrder);

	List<ViewOrder> selectOrders2(Page<ViewOrder> page,ParamOrder paramOrder);

	List<OrderInfo> selectAmount(ParamStatistics param);

	int selectByOrder(@Param("orderId")String orderId);
}