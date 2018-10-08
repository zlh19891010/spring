package com.admin.service.account.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.AnySDK.Login;
import com.admin.common.CodeConstants;
import com.admin.common.logger.ModuleInfo;
import com.admin.common.logger.MonitorLog;
import com.admin.common.logger.MonitorLogInfo;
import com.admin.dao.account.QpaccountdbMapper;
import com.admin.dao.glygl.TGlptGlyglGlyxxMapper;
import com.admin.dataSource.DynamicDataSource;
import com.admin.entity.account.Qpaccountdb;
import com.admin.entity.cards.TGlptCards;
import com.admin.entity.glygl.TGlptGlyglGlyxx;
import com.admin.entity.log.operation.LogOperation;
import com.admin.param.ParamAccount;
import com.admin.param.ParamMessage;
import com.admin.server.SessionMap;
import com.admin.server2.ServerHandler;
import com.admin.server2.message.BaseMessage;
import com.admin.server2.message.KickingMessage;
import com.admin.server2.message.LockMessage;
import com.admin.server2.message.NoticeMessage;
import com.admin.server2.message.PersonalMessage;
import com.admin.server2.message.PointCardMessage;
import com.admin.server2.message.UnBinkAccountMessage;
import com.admin.server2.message.UnLockMessage;
import com.admin.server2.model.MessageObj;
import com.admin.service.account.QpaccountdbService;
import com.admin.service.cards.TGlptCardsService;
import com.admin.service.glyxx.TGlptGlyglGlyxxService;
import com.admin.service.log.operation.LogOperationService;
import com.admin.utils.CommonUtil;
import com.admin.utils.MessType;
import com.admin.view.ViewAccount;
import com.admin.view.ViewUserModel;
import com.admin.wechat.WeChatValidate;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlh
 * @since 2017-11-11
 */
@Service
public class QpaccountdbServiceImpl extends ServiceImpl<QpaccountdbMapper, Qpaccountdb> implements QpaccountdbService {

	@Autowired
	private QpaccountdbMapper qpaccountdbMapper;

	@Autowired
	private ServerHandler serviceHandler;

	@Autowired
	private LogOperationService logOperationService;

	/** 管理员信息管理service */
	@Autowired
	private TGlptGlyglGlyxxService glyxxService;

	/** 管理员信息Mapper */
	@Autowired
	private TGlptGlyglGlyxxMapper tGlptGlyglGlyxxMapper;

	/**
	 * 发送点卡的Service
	 */
	@Autowired
	private TGlptCardsService tGlptCardsService;
	/**
	 *
	 *
	 * 方法描述: [获取玩家列表.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月12日-下午2:53:35<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public Page<ViewAccount> getAccountList(ParamAccount paramAccount,
			ViewUserModel userModel) {
		logger.info("===开始游戏帐号分页===");
		Page<ViewAccount> page = new Page<ViewAccount>(paramAccount.getCurrent(), paramAccount.getSize());
		if(!CodeConstants.ADMIN.equals(userModel.getLoginId())){
			//如果没有授权码直接返回
			if(StringUtils.isBlank(userModel.getsAuthCode())){
				DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
				return page;
			}
			paramAccount.setsAuthCode(userModel.getsAuthCode());
		}
		List<ViewAccount> result = qpaccountdbMapper.selectAccountList(page, paramAccount);
		for(ViewAccount acc:result){
			if(acc.getUid()<100000){
				acc.setSuid(CommonUtil.frontCompWithZore(acc.getUid(),6));
			}else{
				acc.setSuid(acc.getUid()==null?"":acc.getUid().toString());
			}

		}
		page.setRecords(result);
		logger.info("===结束游戏帐号分页===");
		DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
		//DynamicDataSource.clearContext();
		return page;
	}

	/**
	 *
	 *
	 * 方法描述: [踢人操作.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月12日-下午2:53:51<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public Object tiren(String id) {
		String[] keys=new String[]{"carPark_id"};
		try {
			SessionMap sessionMap = SessionMap.newInstance();
			MessageObj message = new MessageObj(MessType.Kicking.toString(),id,"踢出此帐号");
			sessionMap.sendMessage(keys,JSON.toJSON(message).toString());
			return sessionMap.getResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Object();
	}

	/**
	 *
	 *
	 * 方法描述: [发送消息操作.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月12日-下午2:53:51<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	@MonitorLog(text = "协议service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.XYGL)
	public Object message(ParamMessage message,ViewUserModel usermodel) {
		String[] keys=new String[]{"carPark_id"};
		try {
			SessionMap sessionMap = SessionMap.newInstance();
			BaseMessage mess = null;
			switch (Integer.valueOf(message.getType())) {
				case CodeConstants.Message:
					mess = new PersonalMessage(MessType.Message.getValue(),message.getAccount(),message.getMessage());
					break;
				case CodeConstants.PointCard:
					mess = new PointCardMessage(MessType.PointCard.getValue(),message.getAccount(),Double.valueOf(message.getMessage()),CodeConstants.STYLE_PROX);
					//获取当前登录人的总点卡余额
					TGlptGlyglGlyxx acc= glyxxService.selectById(usermodel.getId());
					if(acc!=null){
						if(!CodeConstants.ADMIN.equals(usermodel.getLoginId())){
							Long count=acc.getCards()-Long.valueOf(message.getMessage());
							tGlptGlyglGlyxxMapper.updateCards(count.toString(),usermodel.getId());
						}
						TGlptCards entity = new TGlptCards();
						entity.setCard(Long.valueOf(message.getMessage()));
						entity.setCreaterCode(usermodel.getId());
						entity.setCreateTime(new Date());
						entity.setStrAccounts(message.getAccount());
						entity.setDelflag(CodeConstants.DELFLAG_A);
						entity.setId(getKey());
						entity.setStrNickName(message.getStrnickname());
						tGlptCardsService.insert(entity);
					}
					break;
				case CodeConstants.Notice:
					message.setAccount(null);
					mess = new NoticeMessage(MessType.Notice.getValue(),message.getMessage());
					break;
				case CodeConstants.Lock:
					mess = new LockMessage(MessType.Lock.getValue(),message.getAccount(),Long.valueOf(message.getMessage()));
					break;
				case CodeConstants.UnLock:
					mess = new UnLockMessage(MessType.UnLock.getValue(),message.getAccount());
					break;
				case CodeConstants.Kicking:
					mess = new KickingMessage(MessType.Kicking.getValue(),message.getAccount());
					break;
				case CodeConstants.Unbink:
					mess = new UnBinkAccountMessage(MessType.Unbink.getValue(),message.getAccount());
					break;
			}
			sessionMap.sendMessage(keys,JSON.toJSON(mess).toString());
			//插入操作记录
			LogOperation logOperation =new LogOperation();

			logOperation.setId(mess.getId().toString());
			logOperation.setAccount(message.getAccount());
			logOperation.setType(message.getType());
			logOperation.setStatus(CodeConstants.STATUS_ING);
			logOperation.setOperaterCode(usermodel.getZh());
			logOperation.setSendContent(JSON.toJSON(mess).toString());
			logOperation.setCreateTime(new Date());

			logOperationService.insert(logOperation);
			return sessionMap.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object();
	}

	/**
	 *
	 *
	 * 方法描述: [getAccessToken.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月15日-下午10:18:40<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public Object getAccessToken(String code) {


		return WeChatValidate.getAccessToken(code);
	}

	/**
	 *
	 *
	 * 方法描述: [getAccessToken.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月15日-下午10:18:40<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public void getAccessToken(HttpServletRequest request, HttpServletResponse response) {

		Login login =new Login();
		login.check(request, response);
	}

	/**
	 *
	 *
	 * 方法描述: [查询在线人数.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2018年1月3日-下午8:55:56<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public Integer selectCount(ViewUserModel userModel) {
		String sAuthCode="";
		if(!CodeConstants.ADMIN.equals(userModel.getLoginId())){
			//如果没有授权码直接返回
			if(StringUtils.isBlank(userModel.getsAuthCode())){
				DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
				return 0;
			}
			sAuthCode=userModel.getsAuthCode();
		}
		Integer count=qpaccountdbMapper.selectOnCount(sAuthCode);
		DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
		return count;
	}

	@Override
	public String getUid(String roleID) {

		return qpaccountdbMapper.getUid(roleID);
	}

}
