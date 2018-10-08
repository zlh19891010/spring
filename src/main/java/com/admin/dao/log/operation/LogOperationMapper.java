package com.admin.dao.log.operation;

import java.util.List;

import com.admin.entity.log.operation.LogOperation;
import com.admin.param.ParamLogOperation;
import com.admin.param.YhxxParam;
import com.admin.view.ViewLogOperation;
import com.admin.view.ViewYhxx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zlh
 * @since 2017-12-05
 */
public interface LogOperationMapper extends BaseMapper<LogOperation> {

	
	List<ViewLogOperation> selectLogList(Page<ViewLogOperation> page, ParamLogOperation paramLog);
}