package com.admin.controller.account;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.common.CodeConstants;
import com.admin.dataSource.DynamicDataSource;
import com.admin.entity.glygl.TGlptGlyglGlyxx;
import com.admin.param.ParamAccount;
import com.admin.param.ParamMessage;
import com.admin.service.account.QpaccountdbService;
import com.admin.service.glyxx.TGlptGlyglGlyxxService;
import com.admin.utils.MessType;
import com.admin.view.ResultJson;
import com.admin.view.ViewAccount;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlh
 * @since 2017-11-11
 */
@RestController
@RequestMapping("/qpaccountdb")
public class QpaccountdbController extends BaseController{

	@Autowired
	private QpaccountdbService qpaccountdbService;

	/** 管理员信息管理service */
	@Autowired
	private TGlptGlyglGlyxxService glyxxService;

	/**
	 *
	 * getGlyxxList,(管理员信息分页). <br/>
	 * Author: zlh <br/>
	 * Create Date: 2017年11月11日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param yhxxParam 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("getAccountList")
	Page<ViewAccount> getAccountList(@RequestBody ParamAccount paramAccount) {

		DynamicDataSource.setDbType(CodeConstants.GAME_DATA_SOURCE);//设置后 就OK
		return qpaccountdbService.getAccountList(paramAccount, getUserModel());
	}
	/**
	 *
	 *
	 * 方法描述: [踢人操作.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月12日-下午3:02:23<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param id
	 * void
	 *
	 */
	@RequestMapping("tiren")
	public ResultJson tiren(@RequestParam String id){

		ResultJson resultJson = new ResultJson();
		resultJson.setObject(qpaccountdbService.tiren(id));
		resultJson.setResult(true);
		return resultJson;
	}

	/**
	 *
	 *
	 * 方法描述: [发送消息操作.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月12日-下午3:02:23<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param id
	 * void
	 *
	 */
	@RequestMapping("message")
	public ResultJson message(@RequestBody ParamMessage message){


		if(MessType.Message.toString().equals(message.getType())){
			request.getSession().setAttribute(CodeConstants.TYPE, CodeConstants.Message+ " 操作的账号："+message.getAccount());
		}
		else if(MessType.PointCard.toString().equals(message.getType())){
			request.getSession().setAttribute(CodeConstants.TYPE, CodeConstants.PointCard+" 操作的账号："+message.getAccount());
		}
		else if(MessType.Notice.toString().equals(message.getType())){
			request.getSession().setAttribute(CodeConstants.TYPE, CodeConstants.Notice);
		}
		else if(MessType.Lock.toString().equals(message.getType())){
			request.getSession().setAttribute(CodeConstants.TYPE,CodeConstants.Lock+" 操作的账号："+message.getAccount());
		}
		else if(MessType.UnLock.toString().equals(message.getType())){
			request.getSession().setAttribute(CodeConstants.TYPE,CodeConstants.UnLock+" 操作的账号："+message.getAccount());
		}
		else if(MessType.Kicking.toString().equals(message.getType())){
			request.getSession().setAttribute(CodeConstants.TYPE,CodeConstants.Kicking+" 操作的账号："+message.getAccount());
		}
		else if(MessType.Unbink.toString().equals(message.getType())){
			request.getSession().setAttribute(CodeConstants.TYPE,CodeConstants.Unbink+" 操作的账号："+message.getAccount());
		}

		ResultJson resultJson = new ResultJson();
		TGlptGlyglGlyxx acc= glyxxService.selectById(getUserModel().getId());
		//服务器端验证点卡余额是否大于发送的点数
		if(acc!=null&&!CodeConstants.ADMIN.equals(acc.getLoginId())&&Long.valueOf(message.getMessage())>acc.getCards()){
			resultJson.setResult(false);
			resultJson.setCode(CodeConstants.NOT_ENOUGH);
			return resultJson;
		}
		resultJson.setObject(qpaccountdbService.message(message,getUserModel()));
		resultJson.setResult(true);
		return resultJson;
	}

	/**
	 *
	 *
	 * 方法描述: [获取getAccessToken.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年11月15日-下午10:17:11<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param code
	 * @return
	 * ResultJson
	 *
	 */
	/*@Login(action = Action.Skip)
	@RequestMapping("getAccessToken")
	public ResultJson getAccessToken(@RequestParam String code){

		ResultJson resultJson = new ResultJson();
		resultJson.setObject(qpaccountdbService.getAccessToken(code));
		resultJson.setResult(true);
		return resultJson;
	}*/


	/**
	 *
	 *
	 * 方法描述: [查询在线人数.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2018年1月3日-下午8:56:59<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * ResultJson
	 *
	 */
	@RequestMapping("selectCount")
	public ResultJson selectCount(){
		DynamicDataSource.setDbType(CodeConstants.GAME_DATA_SOURCE);//设置后 就OK
		ResultJson resultJson = new ResultJson();
		resultJson.setObject(qpaccountdbService.selectCount(getUserModel()));
		resultJson.setResult(true);
		return resultJson;
	}

}
