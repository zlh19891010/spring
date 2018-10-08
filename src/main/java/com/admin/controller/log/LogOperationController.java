package com.admin.controller.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.param.ParamLogOperation;
import com.admin.service.log.operation.LogOperationService;
import com.admin.view.ViewLogOperation;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlh
 * @since 2017-12-05
 */
@RestController
@RequestMapping("/logOperation")
public class LogOperationController extends BaseController{

	@Autowired
	private LogOperationService logOperationService;

	/**
	 * getYhxxPagination,(日志信息列表分页). <br/>
	 * Author: zlh<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: zlh <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param yhxxParam 请求参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("getLogPagination")
	public Page<ViewLogOperation> getLogPagination(@RequestBody ParamLogOperation paramLog) {
		return logOperationService.selectLogList(paramLog, getUserModel());

	}




}
