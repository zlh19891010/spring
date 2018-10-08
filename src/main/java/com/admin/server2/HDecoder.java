package com.admin.server2;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.admin.utils.CoverUtil;


/**
 * 文件名称： com.admin.server2.HDecoder.java</br>
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
public class HDecoder extends CumulativeProtocolDecoder {

	private final Charset charset;

	private boolean readRear = false;

	private boolean readHead = false;
	private Logger logger = Logger.getLogger(HDecoder.class);

	public HDecoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {

		//System.out.println("-------doDecode----------");

		//CharsetDecoder cd = charset.newDecoder();
		byte[] temp = new byte[in.limit()];
		in.get(temp);

		IoBuffer buf = IoBuffer.allocate(in.remaining()).setAutoExpand(true);
		buf.put(temp);
		buf.flip();
		logger.info("开始解析消息");
		List<byte[]> list =CoverUtil.praseIoBuffer(buf);
		logger.info("消息已经解析到了list集合，接下来循环"+list.size());
		for(byte[] array:list){
			String mes="";
			for(byte b :array){
				mes+=CoverUtil.decode(CoverUtil.toHexString1(b).toUpperCase());
			}
			out.write(mes);
		}
		return true;

		/*
		if (in.remaining() > 4) {// 有数据时，读取字节判断消息长度
			in.mark();// 标记当前位置，以便reset
			int size = in.getInt();

			// 如果消息内容不够，则重置，相当于不读取size
			if (size > in.remaining()) {
				in.reset();
				return false;// 接收新数据，以拼凑成完整数据
			} else if (size != 0 && (size - 4 >= 0)) {
				byte[] bytes = new byte[size - 4];
				//int protocol = in.getInt();
				// 拿到客户端发过来的数据组装成基础包写出去
				in.get(bytes, 0, size - 4);
				//in.get(bytes, size - 4, size);

				PackageBeanFactory beanFactory = (PackageBeanFactory) session
						.getAttribute(ServerHandler.BEAN_FACTORY);
				//out.write(beanFactory.getPackage(protocol, size, bytes));

				String mes = in.getString(cd);

				out.write(mes);
				// 如果读取内容后还粘了包，就让父类再给读取进行下次解析
				if (in.remaining() > 0) {
					return true;
				}
			}
		}

		return false;// 处理成功，让父类进行接收下个包
		 */


	}

}
