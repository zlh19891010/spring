package com.admin.server.factory;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.admin.server.SessionMap;
import com.admin.server.exception.TblDeviceInfoNotFoundException;
import com.admin.server.exception.impl.DataValidationException;

public class IoHandleInfo extends IoHandlerAdapter {



	Logger logger = Logger.getLogger(IoHandleInfo.class);

	public IoHandleInfo() {
	}


	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		logger.info("客户端和服务器创建会话连接。。。。");

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("客户端和服务器session打开。。。。");
		//保存客户端的会话session
		SessionMap sessionMap = SessionMap.newInstance();
		sessionMap.addSession("youxi", session);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		try{
			Object lightFlag =session.getAttribute("lightFlag");
			Object type =session.getAttribute("type");


			if(MessageType.Infrared.equals(type)){
				if(lightFlag!=null&&MessageType.RedLight==(Integer)lightFlag){
					return;
				}
			}
			logger.info("服务器收到的message:"+message);
			((AbsMessage) message).praseBody();
			((AbsMessage) message).preInvoke();
			((AbsMessage) message).invoke(session,this);


			/*if(MessageType.Status.equals(type)){
				logger.info("服务器收到的message:"+message);
			    ((AbsMessage) message).praseBody();
				((AbsMessage) message).preInvoke();
				((AbsMessage) message).invoke(session,this);
			}
			else if(MessageType.Infrared.equals(type)&&(lightFlag!=null||!"".equals(lightFlag))&&MessageType.RedLight!=(Integer)lightFlag){
				logger.info("服务器收到的message:"+message);
			    ((AbsMessage) message).praseBody();
				((AbsMessage) message).preInvoke();
				((AbsMessage) message).invoke(session,this);
			}
			else if(MessageType.RedLight!=(Integer)lightFlag){
				logger.info("服务器收到的message:"+message);
			    ((AbsMessage) message).praseBody();
				((AbsMessage) message).preInvoke();
				((AbsMessage) message).invoke(session,this);
			}*/
		}catch(DataValidationException e){
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (TblDeviceInfoNotFoundException e) {
			e.printStackTrace();
		}
		finally{

		}

	}

	@Override
	public void messageSent(IoSession ioSession, Object arg1) throws Exception {
		logger.info("服务端发送信息成功..."+arg1);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.info("服务端进入空闲状态...");

	}

	@Override
	public void exceptionCaught(IoSession ioSession, Throwable cause) throws Exception {
		logger.error("服务端发送异常...", cause);

	}
}
