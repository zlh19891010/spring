package com.admin.server.factory;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class InfraredMessage extends AbsMessage {
	protected Logger logger = Logger.getLogger(this.getClass());

	public InfraredMessage(byte[] startSymbol, byte[] messLen, byte[] messID, byte[] funcode, byte[] body,
			byte[] valCode, byte[] endSymbol) {
		super(startSymbol, messLen, messID, funcode, body, valCode, endSymbol);
	}

	private int isQualified;

	@Override
	public void praseBody() {
		byte[] body = (byte[]) this.body;

		isQualified = Integer.parseInt(CovertUntils.toHexString1(body), 16);

	}

	@Override
	public Object invoke(IoSession iosession, IoHandleInfo infoHandler) throws SQLException {/*
		Production product = new Production();
		ProductionDetail prodetail =null;
		product.setNisQualified(String.valueOf(isQualified));
		// String clientIP = iosession.getAttribute("clientIP").toString();
		String sql = "select n_order_id,d.n_id as d_nid  from tbl_product_line_info  l,(select n_id,n_product_line_id from tbl_device_info where n_id="+Long.valueOf(basics.getMessID())+") d  where l.n_id=d.n_product_line_id";
		ResultSet rs = JdbcUntils.getResult(sql);

		int count = 0;
		while (rs.next()) {
			count++;
			product.setNorderId(Long.valueOf(rs.getString("n_order_id")));
			product.setNdeviceId(Long.valueOf(rs.getString("d_nid")));
		}
		if (count == 0) {
			throw new TblDeviceInfoNotFoundException("the  device is not found,  id is :"
					+ Long.valueOf(basics.getMessID()));
		}
		//		当接收到的 是1 ：合格 的情况
		if (MessageType.Qualified == isQualified) {
			prodetail =new ProductionDetail();
			JdbcUntils.insert(product);
			sql = "select n_id from tbl_product_info where n_is_qualified ='1' and n_order_id="+product.getNorderId()+" and n_device_id="+product.getNdeviceId()+" order by d_create desc";
			ResultSet proRes = JdbcUntils.getResult(sql);
			Long proId = 0l;
			count=0;
			if(proRes.next()) {
				count++;
				proId = proRes.getLong("n_id");
			}
			prodetail.setNproductId(proId);
			prodetail.setSstatus(product.getNisQualified());
			JdbcUntils.insertDetail(prodetail);
		}
		//		当接收到的是2：不合格的情况
		else {
			prodetail =new ProductionDetail();
			sql = "select n_id from tbl_product_detail_info where s_status ='1' and n_product_id in ( select n_id from tbl_product_info where n_order_id="+product.getNorderId()+" order by d_create desc) order by  d_create desc ";
			ResultSet	rss = JdbcUntils.getResult(sql);
			if (rss.next()) {
				prodetail.setNid(rss.getLong("n_id"));
				prodetail.setSstatus(product.getNisQualified());
				JdbcUntils.updateDetail(prodetail);
				//				product.setNid(rss.getLong("n_id"));
				//				JdbcUntils.update(product);
			} else {
				JdbcUntils.insert(product);
				sql = "select n_id from tbl_product_info where n_is_qualified ='1' and n_order_id="+product.getNorderId()+" and n_device_id="+product.getNdeviceId()+" order by d_create desc";
				ResultSet proRes = JdbcUntils.getResult(sql);
				Long proId = 0l;
				count=0;
				if(proRes.next()) {
					count++;
					proId = proRes.getLong("n_id");
				}
				prodetail.setNproductId(proId);
				prodetail.setSstatus(product.getNisQualified());
				JdbcUntils.insertDetail(prodetail);
				//				JdbcUntils.insert(product);
			}
		}
		JdbcUntils.close();
	 */
		return null;
	}

}
