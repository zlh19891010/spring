package com.admin.server2;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.admin.common.CodeConstants;
import com.admin.dao.glygl.TGlptGlyglGlyxxMapper;
import com.admin.entity.glygl.TGlptGlyglGlyxx;
import com.admin.entity.log.operation.LogOperation;
import com.admin.server.SessionMap;
import com.admin.server2.message.AuthCodeMessage;
import com.admin.server2.message.BaseMessage;
import com.admin.service.log.operation.LogOperationService;
import com.admin.utils.MessType;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * 文件名称： com.admin.server2.ServerHandler.java</br>
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
public class ServerHandler extends IoHandlerAdapter{

	@Autowired
	private LogOperationService logOperationService;

	/** 管理员信息Mapper */
	@Autowired
	private TGlptGlyglGlyxxMapper tGlptGlyglGlyxxMapper;

	static Logger log = Logger.getLogger(ServerHandler.class);
	public ServerHandler() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
	}


	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		log.debug("服务端收到信息-------------");
		//获取客户端发过来的key
		JSONObject json=(JSONObject)JSON.parse(message.toString());
		SessionMap sessionMap=  SessionMap.newInstance();
		//当协议是获取用户信息的时候  协议编号是9
		if(CodeConstants.User_Info==Integer.valueOf(json.getString("type"))){
			log.debug("sessionID:"+json.getString("sessionID"));
			log.debug("sessionMap.getCache:"+sessionMap.getCache(json.getString("sessionID")));
			String str=sessionMap.getCache(json.getString("sessionID"));
			JSONObject obj = (JSONObject) JSON.parse(str);
			obj.put("type",CodeConstants.User_Info);
			obj.put("socketId",Integer.valueOf(json.getString("socketId")));
			sessionMap.sendMessage(new String[]{"carPark_id"},JSON.json(obj));

		}
		//验证授权码是否有效的协议
		else if(CodeConstants.AUTHCODE==Integer.valueOf(json.getString("type"))){
			log.debug("socketId:"+json.getString("socketId")+"   sAuthCode:"+json.getString("sAuthCode"));
			Wrapper<TGlptGlyglGlyxx> wrapper = new EntityWrapper<TGlptGlyglGlyxx>();
			wrapper.where("s_auth_code= {0}", json.getString("sAuthCode"));
			int count=tGlptGlyglGlyxxMapper.selectCount(wrapper);
			BaseMessage mess =null;
			if(count>0){
				mess =new AuthCodeMessage(MessType.AuthCode.getValue(),CodeConstants.AUTH_CODE_EFFECTIVE,json.getString("sAuthCode"),Integer.valueOf(json.getString("socketId")));
			}else{
				mess =new AuthCodeMessage(MessType.AuthCode.getValue(),CodeConstants.AUTH_CODE_INVALID,json.getString("sAuthCode"),Integer.valueOf(json.getString("socketId")));
			}
			sessionMap.sendMessage(new String[]{"carPark_id"},com.alibaba.fastjson.JSON.toJSON(mess).toString());
			
		}else{
			LogOperation logOperation =new LogOperation();
			logOperation.setId(json.getString("id"));
			if("0".equals(json.getString("code"))){
				logOperation.setStatus(CodeConstants.STATUS_SUCCESS);
			}else{
				logOperation.setStatus(CodeConstants.STATUS_FAILED);
			}
			logOperation.setReturnContent(message.toString());
			logOperation.setOperateTime(new Date());
			logOperationService.updateById(logOperation);
		}

	}
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		log.debug("------------服务端发消息到客户端---");
	}
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		log.debug("远程session关闭了一个..." + session.getRemoteAddress().toString());
	}
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.debug(session.getRemoteAddress().toString() +"----------------------create");
	}
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		log.debug(session.getServiceAddress() +"IDS");
	}
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.debug("连接打开："+session.getLocalAddress());
		SessionMap sessionMap = SessionMap.newInstance();
		sessionMap.addSession("carPark_id", session);
	}
	
	
}
