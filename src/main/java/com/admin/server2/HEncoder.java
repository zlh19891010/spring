package com.admin.server2;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;


/**
 * 文件名称： com.admin.server2.HEncoder.java</br>
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
public class HEncoder implements ProtocolEncoder {
	static Logger log = Logger.getLogger(HEncoder.class);
	private final Charset charset;

	public HEncoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {

		CharsetEncoder ce = charset.newEncoder();
		String mes = (String) message;
		IoBuffer buffer = IoBuffer.allocate(mes.getBytes().length+1).setAutoExpand(true);
		buffer.putString(mes,ce);
		buffer.put((byte)0xff);
		buffer.flip();
		out.write(buffer);
		
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		log.info("Dispose called,session is " + session);
	}

}
