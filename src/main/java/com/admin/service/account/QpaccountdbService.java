package com.admin.service.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.common.logger.ModuleInfo;
import com.admin.common.logger.MonitorLog;
import com.admin.common.logger.MonitorLogInfo;
import com.admin.entity.account.Qpaccountdb;
import com.admin.param.ParamAccount;
import com.admin.param.ParamMessage;
import com.admin.view.ViewAccount;
import com.admin.view.ViewUserModel;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlh
 * @since 2017-11-11
 */
public interface QpaccountdbService extends IService<Qpaccountdb> {

	/**
	 *
	 *
	 * 方法描述: [获取玩家列表.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月12日-下午2:53:15<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param paramAccount
	 * @param userModel
	 * @return
	 * Page<ViewAccount>
	 *
	 */
	Page<ViewAccount> getAccountList(ParamAccount paramAccount,ViewUserModel userModel);

	/**
	 *
	 *
	 * 方法描述: [踢人操作.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月12日-下午2:53:01<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * void
	 *
	 */
	Object tiren(String id);


	/**
	 *
	 *
	 * 方法描述: [发送消息操作.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月12日-下午2:53:01<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * void
	 *
	 */
	@MonitorLog(text = "协议service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.XYGL)
	Object message(ParamMessage message,ViewUserModel usermodel);



	/**
	 *
	 *
	 * 方法描述: [getAccessToken.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月15日-下午10:18:19<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param id
	 * @return
	 * Object
	 *
	 */
	Object getAccessToken(String id);
	/**
	 *
	 *
	 * 方法描述: [getAccessToken.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月15日-下午10:18:19<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param id
	 * @return
	 * Object
	 *
	 */
	void getAccessToken(HttpServletRequest request,HttpServletResponse response);

	/**
	 *
	 *
	 * 方法描述: [查询在线人数.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2018年1月3日-下午8:55:23<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * Integer
	 *
	 */
	Integer selectCount(ViewUserModel userModel);

	/**
	 *
	 *
	 * 方法描述: [根据roleid获取uid.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2018年1月3日-下午10:50:18<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param roleID
	 * @return
	 * String
	 *
	 */
	String getUid(String roleID);
}
