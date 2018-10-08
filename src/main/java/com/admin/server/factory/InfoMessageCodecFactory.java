package com.admin.server.factory;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public  class InfoMessageCodecFactory extends DemuxingProtocolCodecFactory {

	private MessageDecoder decoder;
	private MessageEncoder<AbsMessage> encoder;
	public InfoMessageCodecFactory(MessageDecoder decoder, MessageEncoder<AbsMessage> encoder) {

		this.decoder = decoder;
		this.encoder = encoder;

		addMessageDecoder(this.decoder);
		addMessageEncoder(AbsMessage.class,encoder);
	}



}
