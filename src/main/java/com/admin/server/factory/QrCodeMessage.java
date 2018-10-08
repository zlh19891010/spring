package com.admin.server.factory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class QrCodeMessage extends AbsMessage {

	protected Logger logger = Logger.getLogger(this.getClass());
	Map<String,String> dicMap =new HashMap<String,String>();
	//private Production product;

	public QrCodeMessage(byte[] startSymbol, byte[] messLen, byte[] messID, byte[] funcode, byte[] body,
			byte[] valCode, byte[] endSymbol) {
		super(startSymbol, messLen, messID, funcode, body, valCode, endSymbol);
	}

	@Override
	public void praseBody() {
		//product = new Production();
		byte[] body = (byte[]) this.body;
		/*String sproductNumber = CovertUntils.decode(CovertUntils.toHexString1(body[1]))
				+ CovertUntils.decode(CovertUntils.toHexString1(body[2]))
				+ CovertUntils.decode(CovertUntils.toHexString1(body[3]))
				+ CovertUntils.decode(CovertUntils.toHexString1(body[4]))
				+ CovertUntils.decode(CovertUntils.toHexString1(body[5]));
		String sname = CovertUntils.decode(CovertUntils.toHexString1(body[6]))
				+ CovertUntils.decode(CovertUntils.toHexString1(body[7]))
				+ CovertUntils.decode(CovertUntils.toHexString1(body[8]))
				+ CovertUntils.decode(CovertUntils.toHexString1(body[9]))
				+ CovertUntils.decode(CovertUntils.toHexString1(body[10]));

		product.setNisQualified(CovertUntils.decode(CovertUntils.toHexString1(body[0])).toUpperCase());
		product.setSname(sname);
		 */
		String proNum= "";
		for (byte element : body) {
			proNum+=CovertUntils.decode(CovertUntils.toHexString1(element).toUpperCase());
		}
		//product.setNisQualified(proNum);

	}

	@Override
	public Object invoke(IoSession iosession, IoHandleInfo infoHandler) throws SQLException {/*


		String type = "";
		String regex ="";
		// String clientIP = iosession.getAttribute("clientIP").toString();
		String sql = "select n_order_id,d.n_id as d_nid,d.n_type as d_type  from tbl_product_line_info  l,(select n_id,n_product_line_id,n_type from tbl_device_info where n_id="+Long.valueOf(basics.getMessID())+") d  where l.n_id=d.n_product_line_id";
		//ResultSet rs = JdbcUntils.getResult(sql);
		int count = 0;
		while (rs.next()) {
			count++;
			product.setNorderId(Long.valueOf(rs.getString("n_order_id")));
			product.setNdeviceId(Long.valueOf(rs.getString("d_nid")));
			type = rs.getString("d_type");
		}
		if (count == 0) {
			throw new TblDeviceInfoNotFoundException("the  device is not found,  id is :"
					+ Long.valueOf(basics.getMessID()));
		}
		//		如果收到的是第三种类型的设备处理
		if(MessageType.DeviceType_3.equals(type)){
			saveInfomation();
			return null;
		}

		//根据订单ID获取订单的正则匹配
		sql = "select s_product_name from tbl_order_info where n_id ="+product.getNorderId();
		ResultSet orderRes = JdbcUntils.getResult(sql);

		while (orderRes.next()) {
			regex = orderRes.getString("s_product_name");
		}
		//		 正则匹配
		if(product.getNisQualified().matches(regex)){
			iosession.setAttribute("productNum", product.getNisQualified());
			iosession.setAttribute("isInsert",false);
			return null;
		}
		//		正则匹配失败
		String productNum =(String)iosession.getAttribute("productNum");
		if(productNum==null||"".equals(productNum)){
			throw new DataValidationException("the  data  is invalid  Don't  match regex..please check client data...OK?");
		}
		Boolean isInsert = (Boolean)iosession.getAttribute("isInsert");
		//		如果为false 就代表上一条匹配正确为入库，此时就可入库
		if(!isInsert.booleanValue()){
			product.setSproductNumber(productNum);
			//			 入库产品表
			sql = "select n_id from tbl_product_info where n_order_id="+product.getNorderId()+" and s_product_number='"+productNum+"' and n_device_id="+product.getNdeviceId();
			ResultSet proRes = JdbcUntils.getResult(sql);
			Long proId = 0l;
			count=0;
			if (proRes.next()) {
				count++;
				proId = proRes.getLong("n_id");
				product.setNid(proId);
				JdbcUntils.update(product);
			}
			if(count==0){
				JdbcUntils.insert(product);
				ResultSet	 proRes_ = JdbcUntils.getResult(sql);
				if (proRes_.next()) {
					proId = proRes_.getLong("n_id");
				}
			}
			if(dicMap.isEmpty()){
				//				 查询字典表所有的合格与不合格的状态以及下标
				sql ="select  s_value ,s_position from tbl_dic_info   where  n_parent_id in ( select n_id from tbl_dic_info where s_key='qualified' or s_key='unqualified') ";
				ResultSet dicRes = JdbcUntils.getResult(sql);
				while(dicRes.next()){
					dicMap.put(dicRes.getString("s_value"), dicRes.getString("s_position"));
				}
			}

			//			用接到的下标和产品ID去查询详情表是否有存在
			sql ="select n_id from tbl_product_detail_info where n_product_id="+proId+" and s_position='"+dicMap.get(product.getNisQualified())+"' ";
			ResultSet proDetalRes = JdbcUntils.getResult(sql);
			ProductionDetail proDetail =null;
			count=0;
			if (proDetalRes.next()) {
				count++;
				proDetail = new ProductionDetail();
				proDetail.setNid(proDetalRes.getLong("n_id"));
				proDetail.setSstatus(product.getNisQualified());
				JdbcUntils.updateDetail(proDetail);
			}
			//			 如果查询为空，代表没有该状态的产品，直接入库
			if(count==0){
				proDetail = new ProductionDetail();
				proDetail.setNproductId(proId);
				proDetail.setSstatus(product.getNisQualified());
				proDetail.setPosition(dicMap.get(product.getNisQualified()));
				JdbcUntils.insertDetail(proDetail);
			}
			iosession.setAttribute("isInsert", true);
		}

		JdbcUntils.close();
	 */
		return null;
	}


	private void saveInfomation() throws SQLException{
		/*Long proId= JdbcUntils.insert(product);

		if(dicMap.isEmpty()){
			//			 查询字典表所有的合格与不合格的状态以及下标
			String sql ="select  s_value ,s_position from tbl_dic_info   where  n_parent_id in ( select n_id from tbl_dic_info where s_key='qualified' or s_key='unqualified') ";
			ResultSet dicRes = JdbcUntils.getResult(sql);
			while(dicRes.next()){
				dicMap.put(dicRes.getString("s_value"), dicRes.getString("s_position"));
			}
		}
		//		 入库详情表
		ProductionDetail proDetail = new ProductionDetail();

		proDetail.setNproductId(proId);
		proDetail.setSstatus(product.getNisQualified());
		proDetail.setPosition(dicMap.get(product.getNisQualified()));
		JdbcUntils.insertDetail(proDetail);*/

	}

}
