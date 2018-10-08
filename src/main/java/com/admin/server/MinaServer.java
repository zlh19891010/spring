package com.admin.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.admin.server.factory.InfoMessageCodecFactory;
import com.admin.server.factory.InfoMessageDecoder;
import com.admin.server.factory.InfoMessageEncoder;
import com.admin.server.factory.IoHandleInfo;

/**
 * 文件名称： com.admin.server.MinaServer.java</br>
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
public class MinaServer implements Server {

	static Logger logger = Logger.getLogger(MinaServer.class);


	public static Integer listenerPort=9090;

	public MinaServer() {
		logger.info("MinaServer.server()");
	}

	@Override
	public void start() {

		try {

			//			CovertUntils.validate(args);
			logger.info("MinaServer.start()...启动成功！........");
			final IoAcceptor ioAcceptor =new NioSocketAcceptor();
			InfoMessageCodecFactory imcf = new InfoMessageCodecFactory(new InfoMessageDecoder(Charset.forName("utf-8")),new InfoMessageEncoder(Charset.forName("utf-8")));
			ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(imcf));
			ioAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
			ioAcceptor.getFilterChain().addLast("executor", new org.apache.mina.filter.executor.ExecutorFilter());
			ioAcceptor.setHandler(new IoHandleInfo());
			ioAcceptor.getSessionConfig().setReadBufferSize( 2048 );
			ioAcceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
			ioAcceptor.bind( new InetSocketAddress(listenerPort) );
		} catch (IOException e) {
			logger.info("Server exception..........."+e.getMessage());
			e.printStackTrace();
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void restart() {

		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {

		// TODO Auto-generated method stub

	}

}
