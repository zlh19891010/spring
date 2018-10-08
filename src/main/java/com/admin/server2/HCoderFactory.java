package com.admin.server2;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 文件名称： com.admin.server2.HCoderFactory.java</br>
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
public class HCoderFactory implements ProtocolCodecFactory{
	private final HEncoder encoder;
	private final HDecoder decoder;

	final static char endchar = 0x0d;
	public HCoderFactory() {
		//this(Charset.defaultCharset());
		this(Charset.forName("UTF-8"));

	}

	public HCoderFactory(Charset charSet) {
		encoder = new HEncoder(charSet);
		decoder = new HDecoder(charSet);
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return encoder;
	}
}
