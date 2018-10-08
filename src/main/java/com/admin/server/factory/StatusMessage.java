package com.admin.server.factory;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class StatusMessage extends AbsMessage {

	protected Logger logger = Logger.getLogger(this.getClass());

	public StatusMessage(byte[] startSymbol, byte[] messLen, byte[] messID, byte[] funcode, byte[] body,
			byte[] valCode, byte[] endSymbol) {
		super(startSymbol, messLen, messID, funcode, body, valCode, endSymbol);
	}

	private int status;

	@Override
	public void praseBody() {
		byte[] body = (byte[]) this.body;

		status = Integer.parseInt(CovertUntils.toHexString1(body), 16);
	}

	@Override
	public Object invoke(IoSession iosession, IoHandleInfo infoHandler) throws SQLException {

		String sql = "update tbl_product_line_info  l,(select n_product_line_id from tbl_device_info where n_id="+Long.valueOf(basics.getMessID())+") d set l.d_update =NOW(), l.n_status ="+status +"  where l.n_id=d.n_product_line_id";
		//JdbcUntils.updateStatus(sql);
		//JdbcUntils.close();

		return null;
	}

}
