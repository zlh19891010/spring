package com.admin.service.room;

import java.util.List;
import java.util.Map;

import com.admin.entity.room.Qptabledb;
import com.admin.param.ParamRoom;
import com.admin.view.ViewPerson;
import com.admin.view.ViewRoom;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlh
 * @since 2017-12-15
 */
public interface QptabledbService extends IService<Qptabledb> {
	
	
	Page<ViewRoom> selectRooms(ParamRoom paramRoom);
	
	List<ViewRoom> getPersons(String id);
	
	Map<String,List<ViewPerson>>  getRounds(String id);
}
