package com.admin.server;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

/**
 * 文件名称： com.admin.server.SessionMap.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年11月12日</br>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 *
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者        日期       修改内容<br/>
 *
 *
 * ================================================<br/>
 *  Copyright (c) 2010-2011 .All rights reserved.<br/>
 */
public class SessionMap {
	static Logger logger = Logger.getLogger(SessionMap.class);

	private static SessionMap sessionMap = null;

	private Map<String, IoSession>map = new HashMap<String, IoSession>();

	private Map<String,String> cache = new HashMap<String, String>();
	
	
	//构造私有化 单例
	private SessionMap(){}

	private Object result;





	public Object getResult() {

		return result;
	}


	/**
	 * @Description: 获取唯一实例
	 * @author whl
	 * @date 2014-9-29 下午1:29:33
	 */
	public static SessionMap newInstance(){
		logger.debug("SessionMap单例获取---");
		if(sessionMap == null){
			sessionMap = new SessionMap();
		}
		return sessionMap;
	}

	/**
	 * 保存用户信息
	 * @param key
	 * @param value
	 */
	public void addCache(String key, String value){
		logger.debug("保存用户信息到cache单例---key=" + key);
		cache.put(key, value);
	}
	
	public String getCache(String key){
		logger.debug("获取用户信息cache单例---key=" + key);
		return cache.get(key);
	}

	/**
	 * @Description: 保存session会话
	 * @author whl
	 * @date 2014-9-29 下午1:31:05
	 */
	public void addSession(String key, IoSession session){
		logger.debug("保存会话到SessionMap单例---key=" + key);
		map.put(key, session);
	}

	/**
	 * @Description: 根据key查找缓存的session
	 * @author whl
	 * @date 2014-9-29 下午1:31:55
	 */
	public IoSession getSession(String key){
		logger.debug("获取会话从SessionMap单例---key=" + key);
		return map.get(key);
	}

	/**
	 * @Description: 发送消息到客户端
	 * @author whl
	 * @date 2014-9-29 下午1:57:51
	 */
	public void sendMessage(String[] keys, Object message){
		for(String key : keys){
			IoSession session = getSession(key);

			logger.debug("反向发送消息到客户端Session---key=" + key + "----------消息=" + message);
			if(session == null){
				return;
			}
			session.write(message);

		}
	}

	/**
	 *
	 *
	 * 方法描述: [接收的消息.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月12日-下午3:57:49<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param result
	 * @return
	 * boolean
	 *
	 */
	public void receivedMessage(Object result){

		this.result=result;
	}

}
