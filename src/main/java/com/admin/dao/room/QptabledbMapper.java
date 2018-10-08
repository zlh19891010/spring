package com.admin.dao.room;

import java.util.List;

import com.admin.entity.room.Qptabledb;
import com.admin.param.ParamRoom;
import com.admin.view.ViewRoom;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zlh
 * @since 2017-12-15
 */
public interface QptabledbMapper extends BaseMapper<Qptabledb> {

	
	List<ViewRoom> selectRooms(Page<ViewRoom> page,ParamRoom paramRoom);
}