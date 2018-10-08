package com.admin.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.admin.content.SpringContext;


/**
 * 文件名称： com.admin.server.ServerStartListener.java</br>
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
public class ServerStartListener implements ServletContextListener {

	/**
	 * mina服务
	 */
	private Server server;


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		server = (Server) SpringContext.getBean("server");
		new Thread(new Runnable() {
			@Override
			public void run() {
				server.start();
			}
		}).start();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		server.shutdown();

	}

}
