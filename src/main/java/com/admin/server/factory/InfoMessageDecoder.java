package com.admin.server.factory;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

public class InfoMessageDecoder implements MessageDecoder{




	private static Logger logger =Logger.getLogger(InfoMessageDecoder.class);
	private Charset charset;

	public InfoMessageDecoder(Charset charset) {
		this.charset = charset;
	}


	@Override
	public MessageDecoderResult decodable(IoSession ioSession, IoBuffer ioBuffer) {
		if (ioBuffer.remaining() < 3) {
			return MessageDecoderResult.NEED_DATA;
		}
		return MessageDecoderResult.OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

		byte[] temp = new byte[ioBuffer.limit()];
		ioBuffer.get(temp);

		IoBuffer buf = IoBuffer.allocate(ioBuffer.remaining()).setAutoExpand(true);
		buf.put(temp);
		buf.flip();
		logger.info("开始解析消息");
		List<byte[]> list =CovertUntils.praseIoBuffer(buf);
		logger.info("消息已经解析到了list集合，接下来循环"+list.size());
		for(byte[] array:list){
			AbsMessage mess = null;
			byte[] startSymbol = new byte[2];
			startSymbol[0]=array[0];
			startSymbol[1]=array[1];
			byte[] messLen = new byte[1];
			messLen[0]=array[2];
			byte[] messID = new byte[1];
			messID[0]=array[3];
			byte[] funcode = new byte[1];
			funcode[0]=array[4];
			byte[] body = new byte[array.length-8];
			for (int j = 0; j < body.length; j++) {
				body[j]=array[5+j];
			}
			byte[] valCode = new byte[1];
			valCode[0]=array[array.length-3];
			byte[] endSymbol = new byte[2];
			endSymbol[0]=array[array.length-2];
			endSymbol[1]=array[array.length-1];
			//二维码上传的
			ioSession.setAttribute("type", CovertUntils.toHexString1(funcode).toUpperCase());
			if(MessageType.QrCode.equals(CovertUntils.toHexString1(funcode).toUpperCase())){
				logger.info("进入了二维码处理消息");
				mess= new QrCodeMessage(startSymbol, messLen, messID, funcode, body,valCode,endSymbol);
			}
			//光电上传的
			else if(MessageType.Infrared.equals(CovertUntils.toHexString1(funcode).toUpperCase())){
				logger.info("进入了光电处理消息");
				mess= new InfraredMessage(startSymbol, messLen, messID, funcode, body,valCode,endSymbol);
			}
			//状态灯
			else if(MessageType.Status.equals(CovertUntils.toHexString1(funcode).toUpperCase())){
				logger.info("进入了状态处理消息");
				ioSession.setAttribute("lightFlag", Integer.parseInt(CovertUntils.toHexString1(body), 16));
				mess= new StatusMessage(startSymbol, messLen, messID, funcode, body,valCode,endSymbol);
			}
			protocolDecoderOutput.write(mess);
		}


		return MessageDecoderResult.OK;
	}







	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1) throws Exception {
	}

}
