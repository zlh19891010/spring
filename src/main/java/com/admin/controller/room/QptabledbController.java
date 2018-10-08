package com.admin.controller.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.CodeConstants;
import com.admin.dataSource.DynamicDataSource;
import com.admin.param.ParamRoom;
import com.admin.service.room.QptabledbService;
import com.admin.view.ResultJson;
import com.admin.view.ViewRoom;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlh
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/qptabledb")
public class QptabledbController {
	
	@Autowired
	private QptabledbService qptabledbService;
	
	/**
	 * 获取房间列表
	 * @param paramRoom
	 * @return
	 */
	@RequestMapping("rooms")
	public Page<ViewRoom> selectRooms(@RequestBody ParamRoom paramRoom){
		
		DynamicDataSource.setDbType(CodeConstants.GAME_DATA_SOURCE);//设置后 就OK
		return qptabledbService.selectRooms(paramRoom);
	}
	
	/**
	 * 获取玩家的结果
	 * @return
	 */
	@RequestMapping("getPersons")
	public ResultJson getPersons(@RequestParam("id")String id){
		DynamicDataSource.setDbType(CodeConstants.GAME_DATA_SOURCE);//设置后 就OK
		ResultJson json =new ResultJson();
		json.setData(qptabledbService.getPersons(id));
		json.setResult(true);
		return json;
	}
	
	
	/**
	 * 获取玩家的结果
	 * @return
	 */
	@RequestMapping("getRounds")
	public ResultJson getRounds(@RequestParam("id")String id){
		DynamicDataSource.setDbType(CodeConstants.GAME_DATA_SOURCE);//设置后 就OK
		ResultJson json =new ResultJson();
		json.setObject(qptabledbService.getRounds(id));
		json.setResult(true);
		return json;
	}
	
}
