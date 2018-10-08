package com.admin.server.factory;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.admin.server.exception.impl.DataValidationException;

public abstract class AbsMessage {

	protected Logger logger = Logger.getLogger(this.getClass());

	protected Basics basics;

	protected Object body;

	public void preInvoke(){
		String dataCode = basics.getStartSymbol()+basics.getMessLen()+basics.getMessID()+basics.getFuncode()+CovertUntils.toHexString1((byte[])body).toUpperCase();
		String valStr =CovertUntils.makeChecksum(dataCode);
		if(!basics.getValCode().equals(valStr)){
			throw new DataValidationException("valCode is not right  => pass valCode:"+valStr+"         this.basics.getValCode(): "+basics.getValCode());
		}
	}

	public abstract Object invoke (IoSession iosession,IoHandleInfo infoHandler)throws SQLException;



	public abstract void praseBody();


	public AbsMessage(byte[] startSymbol,byte[] messLen,byte[] messID,byte[] funcode,byte[] body,byte[] valCode,byte[] endSymbol) {
		basics = new Basics(CovertUntils.toHexString1(startSymbol).toUpperCase(),
				CovertUntils.toHexString1(messLen).toUpperCase(),
				CovertUntils.toHexString1(messID).toUpperCase(),
				CovertUntils.toHexString1(funcode).toUpperCase(),
				CovertUntils.toHexString1(valCode).toUpperCase(),
				CovertUntils.toHexString1(endSymbol).toUpperCase());
		this.body = body;

	}


	public class Basics{

		private String startSymbol;//起始符
		private String messLen;       //消息总长度
		private String messID;     //消息ID
		private String funcode;    //功能码

		private String valCode;    //校验码
		private String endSymbol;  //结束符



		public Basics(String startSymbol, String messLen, String messID, String funcode, String valCode, String endSymbol) {
			this.startSymbol = startSymbol;
			this.messLen = messLen;
			this.messID = messID;
			this.funcode = funcode;
			this.valCode = valCode;
			this.endSymbol = endSymbol;
		}
		public String getStartSymbol() {
			return startSymbol;
		}
		public void setStartSymbol(String startSymbol) {
			this.startSymbol = startSymbol;
		}
		public String getMessLen() {
			return messLen;
		}
		public void setMessLen(String messLen) {
			this.messLen = messLen;
		}
		public String getMessID() {
			return messID;
		}
		public void setMessID(String messID) {
			this.messID = messID;
		}
		public String getFuncode() {
			return funcode;
		}
		public void setFuncode(String funcode) {
			this.funcode = funcode;
		}
		public String getValCode() {
			return valCode;
		}
		public void setValCode(String valCode) {
			this.valCode = valCode;
		}
		public String getEndSymbol() {
			return endSymbol;
		}
		public void setEndSymbol(String endSymbol) {
			this.endSymbol = endSymbol;
		}



	}

}
