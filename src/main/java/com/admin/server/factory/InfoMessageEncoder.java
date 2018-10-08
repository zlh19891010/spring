package com.admin.server.factory;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public class InfoMessageEncoder implements MessageEncoder<AbsMessage>{



	private static Logger logger = Logger.getLogger(InfoMessageEncoder.class);
	private Charset charset;

	public InfoMessageEncoder(Charset charset){
		this.charset = charset;
	}
	@Override
	public void encode(IoSession arg0, AbsMessage arg1, ProtocolEncoderOutput arg2) throws Exception {
		// TODO Auto-generated method stub

	}

}
