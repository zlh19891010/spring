package com.admin.service.log.operation;

import com.admin.entity.log.operation.LogOperation;
import com.admin.param.ParamLogOperation;
import com.admin.view.ViewLogOperation;
import com.admin.view.ViewUserModel;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlh
 * @since 2017-12-05
 */
public interface LogOperationService extends IService<LogOperation> {

	/**
	 *
	 * @param account
	 * @param userModel
	 * @return
	 */
	Page<ViewLogOperation> selectLogList(ParamLogOperation paramLog,ViewUserModel userModel);

}
