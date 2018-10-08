package com.admin.client2;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;


/**
 * 文件名称： com.admin.client2.ClintHandler.java</br>
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
public class ClintHandler extends IoHandlerAdapter {

	static Logger log = Logger.getLogger(ClintHandler.class);
	/**
	 * 写处理服务端推送的信息的逻辑
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {





		System.out.println("-----服务顿返回的json数据----");
		String s = message.toString();
		System.out.println("message :" + s.indexOf("0xff"));
		System.out.println("message :" + s.toString());
		System.out.println("message length:" + s.length());




	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		log.info("-客户端与服务端连接[空闲] - " + status.toString());
		System.out.println("-客户端与服务端连接[空闲] - " + status.toString());

		if(session != null){
			session.close(true);
		}
	}
}
