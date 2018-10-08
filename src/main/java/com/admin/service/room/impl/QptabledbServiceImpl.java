package com.admin.service.room.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.entity.room.Qptabledb;
import com.admin.common.CodeConstants;
import com.admin.dao.room.QptabledbMapper;
import com.admin.dataSource.DynamicDataSource;
import com.admin.param.ParamRoom;
import com.admin.service.room.QptabledbService;
import com.admin.view.ViewCards;
import com.admin.view.ViewPerson;
import com.admin.view.ViewRoom;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlh
 * @since 2017-12-15
 */
@Service
public class QptabledbServiceImpl extends ServiceImpl<QptabledbMapper, Qptabledb> implements QptabledbService {

	
	@Autowired
	private QptabledbMapper qptabledbMapper;
	/**
	 * 查询房间列表
	 */
	@Override
	public Page<ViewRoom> selectRooms(ParamRoom paramRoom) {
		logger.info("===开始房间分页===");
		Page<ViewRoom> page = new Page<ViewRoom>(paramRoom.getCurrent(), paramRoom.getSize());
		List<ViewRoom> result = qptabledbMapper.selectRooms(page,paramRoom);
		//解析data的json数据
		for(ViewRoom room :result){
			try {
				String str=new String(room.getData(),"UTF-8");
				System.out.println("str:"+str);
				JSONObject json = JSONObject.parseObject(str);
				room.setRoomId(json.getString("roomid"));
				room.setOwner(json.getString("owner"));
				room.setdCount(json.getString("rounds"));
				JSONArray array = json.getJSONArray("players");
				room.setpCount(array.isEmpty()?"0":String.valueOf(array.size()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		page.setRecords(result);
		logger.info("===结束房间分页===");
		
		DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
		return page;
	}
	
	
	/**
	 * 获取玩家
	 */
	@Override
	public List<ViewRoom> getPersons(String id) {
		Qptabledb room= selectById(id);
		try {
			String str = new String(room.getData(),"UTF-8");
			System.out.println("str:"+str);
			JSONObject json = JSONObject.parseObject(str);
			JSONArray array = json.getJSONArray("players");
			String js=JSONObject.toJSONString(array, SerializerFeature.WriteClassName);//将array数组转换成字符串
			List<ViewRoom>  collection = JSONObject.parseArray(js, ViewRoom.class);//把字符串转换成集合  
			
			return collection;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
		}
		return null;
	}


	/**
	 * 获取对局详情
	 */
	@Override
	public Map<String,List<ViewPerson>>  getRounds(String id) {
		Qptabledb room= selectById(id);
		Map<String,List<ViewPerson>> map = new HashMap<String,List<ViewPerson>>();
		try {
			String str = new String(room.getData(),"UTF-8");
			System.out.println("str:"+str);
			JSONObject json = JSONObject.parseObject(str);
			JSONArray array = json.getJSONArray("details");
            for (Object obj:array) {
            	JSONArray players =((JSONObject)obj).getJSONArray("players");
            	String js=JSONObject.toJSONString(players, SerializerFeature.WriteClassName);//将array数组转换成字符串
            	List<ViewPerson> list = JSONObject.parseArray(js, ViewPerson.class);//把字符串转换成集合 
            	map.put(((JSONObject)obj).getString("banker"), list);
			}
			return map;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
		}
		return null;
	}
	
}
