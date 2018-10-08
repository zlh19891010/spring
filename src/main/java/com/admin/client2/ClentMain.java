package com.admin.client2;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.admin.server2.HCoderFactory;

/**
 * 文件名称： com.admin.client2.ClentMain.java</br>
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
public class ClentMain extends Thread{

	static Logger log = Logger.getLogger(ClentMain.class);
	@Override
	public void run() {

		//ip
		String host = "server.natappfree.cc";
		//String host = "127.0.0.1";
		//端口
		int port = 43288;
		//消息
		final String carPark_id ="-1";


		// 创建客户端连接器.
		final NioSocketConnector connector = new NioSocketConnector();
		//设置连接超时
		connector.setConnectTimeoutMillis(30000);
		// 设置默认访问地址
		connector.setDefaultRemoteAddress(new InetSocketAddress(host, port));
		//将IoSession的主键属性注入线程映射表MDC中
		//connector.getFilterChain().addLast("mdc", new MdcInjectionFilter());
		//日志过滤器
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		// 设置编码过滤器
		connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new HCoderFactory()));
		//添加处理器
		connector.setHandler(new ClintHandler());
		// 设置接收缓冲区的大小
		connector.getSessionConfig().setReceiveBufferSize(10240);
		// 设置输出缓冲区的大小
		connector.getSessionConfig().setSendBufferSize(10240);

		/**
		 * 空闲重连的机制，根据需要选择相应的配置
		 */
		// 读写都空闲时间:30秒
		//connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
		// 读(接收通道)空闲时间:40秒
		//connector.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 40);
		// 写(发送通道)空闲时间:50秒
		//connector.getSessionConfig().setIdleTime(IdleStatus.WRITER_IDLE, 50);

		//断线重连回调拦截器
		connector.getFilterChain().addFirst("reconnection", new IoFilterAdapter() {
			@Override
			public void sessionClosed(NextFilter nextFilter, IoSession ioSession) throws Exception {
				for(;;){
					try{
						Thread.sleep(3000);
						ConnectFuture future = connector.connect();
						future.awaitUninterruptibly();// 等待连接创建成功
						IoSession session = future.getSession();// 获取会话
						session.write(carPark_id);

						if(session.isConnected()){
							log.info("断线重连["+ connector.getDefaultRemoteAddress().getHostName() +":"+ connector.getDefaultRemoteAddress().getPort()+"]成功");
							//System.out.println("断线重连["+ connector.getDefaultRemoteAddress().getHostName() +":"+ connector.getDefaultRemoteAddress().getPort()+"]成功");
							break;
						}
					}catch(Exception ex){
						log.info("重连服务器登录失败,3秒再连接一次:" + ex.getMessage());
						//System.out.println("重连服务器登录失败,3秒再连接一次:" + ex.getMessage());
					}
				}
			}
		});

		//开始连接
		for (;;) {
			try {
				ConnectFuture future = connector.connect();
				// 等待连接创建成功
				future.awaitUninterruptibly();
				// 获取会话
				IoSession session = future.getSession();
				//发送消息
				session.write("{'roleid':1}"+(byte)0xff);
				log.error("连接服务端" + host + ":" + port + "[成功]" + ",,时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				break;
			} catch (Exception e) {
				//System.out.println("连接服务端" + host + ":" + port + "失败" + ",,时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ", 连接MSG异常,请检查MSG端口、IP是否正确,MSG服务是否启动,异常内容:" + e.getMessage());
				log.error("连接服务端" + host + ":" + port + "失败" + ",,时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ", 连接MSG异常,请检查MSG端口、IP是否正确,MSG服务是否启动,异常内容:" + e.getMessage(), e);
				// 连接失败后,重连10次,间隔30s
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					log.error("连接服务端失败后，睡眠5秒发生异常！");
				}
			}
		}



		// cf.getSession().write("quit");//发送消息
		//cf.getSession().close();
		//cf.getSession().getCloseFuture().awaitUninterruptibly();// 等待连接断开
		//connector.dispose();

	}

	public static void main(String[] args) {

		System.out.println((byte)0xff);
	}
}
