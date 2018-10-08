package com.admin.service.log.operation.impl;

import java.util.List;

import com.admin.entity.log.operation.LogOperation;
import com.admin.common.GlobalConstants;
import com.admin.dao.log.operation.LogOperationMapper;
import com.admin.param.ParamLogOperation;
import com.admin.service.log.operation.LogOperationService;
import com.admin.view.ViewLogOperation;
import com.admin.view.ViewUserModel;
import com.admin.view.ViewYhxx;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlh
 * @since 2017-12-05
 */
@Service
public class LogOperationServiceImpl extends ServiceImpl<LogOperationMapper, LogOperation> implements LogOperationService {

	@Autowired
	private LogOperationMapper logOperationMapper;
	
	/**
	 * 查看日志列表
	 */
	@Override
	public Page<ViewLogOperation> selectLogList(ParamLogOperation paramLog,
			ViewUserModel userModel) {
		logger.info("===开始查询事例===");
		Page<ViewLogOperation> page = new Page<ViewLogOperation>(paramLog.getCurrent(), paramLog.getSize());
		List<ViewLogOperation> result = logOperationMapper.selectLogList(page, paramLog);
		page.setRecords(result);
		logger.info("===完成查询事例===");
		return page;
	}
	
}
