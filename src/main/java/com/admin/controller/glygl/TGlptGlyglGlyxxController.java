package com.admin.controller.glygl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.common.CodeConstants;
import com.admin.entity.glygl.TGlptGlyglGlyxx;
import com.admin.param.ParamStatistics;
import com.admin.param.YhxxParam;
import com.admin.service.glyxx.TGlptGlyglGlyxxService;
import com.admin.utils.DateUtil;
import com.admin.view.ResultJson;
import com.admin.view.ViewStatistics;
import com.admin.view.ViewUserModel;
import com.admin.view.ViewYhxx;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ClassName: TGlptGlyglGlyxxController <br/>
 * Function: 管理员管理控制器<br/>
 * Date: 2017年3月6日 上午10:54:16 <br/>
 *
 * @author 刘成
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("tGlptGlyglGlyxx")
public class TGlptGlyglGlyxxController extends BaseController {

	/** 管理员信息管理service */
	@Autowired
	private TGlptGlyglGlyxxService glyxxService;

	/**
	 *
	 * getGlyxxList,(管理员信息分页). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
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
	@RequestMapping("getGlyxxList")
	Page<ViewYhxx> getGlyxxList(@RequestBody YhxxParam yhxxParam) {
		return glyxxService.getGlyxxList(yhxxParam, getUserModel());

	}

	/**
	 *
	 * addGlyxx,(新增管理员信息). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param tGlptGlyglGlyxx 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("addGlyxx")
	public ResultJson addGlyxx(@RequestBody TGlptGlyglGlyxx tGlptGlyglGlyxx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("login_id", tGlptGlyglGlyxx.getLoginId());
		if (glyxxService.selectGlyxxByLoginId(tGlptGlyglGlyxx.getLoginId()) != null) {
			return new ResultJson(false, CodeConstants.ADD_JYCF);
		}
		ViewUserModel userModel = getUserModel();
		ResultJson result = new ResultJson(glyxxService.addGly(tGlptGlyglGlyxx, userModel.getZh()));
		return result;
	}

	/**
	 *
	 * modifyGly,(修改管理员信息). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param tGlptGlyglGlyxx 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("modifyGly")
	public ResultJson modifyGly(@RequestBody TGlptGlyglGlyxx tGlptGlyglGlyxx) {
		ViewUserModel userModel = getUserModel();
		if(StringUtils.isBlank(tGlptGlyglGlyxx.getId())){
			tGlptGlyglGlyxx.setId(userModel.getId());
		}
		return glyxxService.modifyGly(tGlptGlyglGlyxx, userModel.getZh());
	}

	/**
	 *
	 * findById,(根据主键查询管理员信息). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param id 主键
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("findById")
	public ResultJson findById(@RequestParam String id) {
		ResultJson resultJson = new ResultJson();
		resultJson.setObject(glyxxService.selectById(id));
		return resultJson;
	}

	/**
	 *
	 * modifyDelflag,(管理员置D). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param id 主键
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("modifyDelflag")
	public ResultJson modifyDelflag(@RequestParam String id) {
		ViewUserModel userModel = getUserModel();
		return glyxxService.modifyDelflag(id, userModel.getZh());
	}

	/**
	 *
	 *
	 * 方法描述: [分配点卡.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月12日-下午7:11:51<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param id
	 * @param cards
	 * @return
	 * ResultJson
	 *
	 */
	@RequestMapping("updateCards")
	public ResultJson updateCards(@RequestParam("id") String id,@RequestParam("cards") String cards,@RequestParam("yhmc")String yhmc){
		ResultJson resultJson = new ResultJson();
		resultJson.setResult(glyxxService.updateCards(cards, id,yhmc,getUserModel()));
		return resultJson;
	}

	/**
	 *
	 *
	 * 方法描述: [获取用户的点卡总数量.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月12日-下午11:12:04<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * ResultJson
	 *
	 */
	@RequestMapping("selectCardByUserId")
	ResultJson selectCardByUserId(){
		ResultJson json =new ResultJson();
		TGlptGlyglGlyxx acc= glyxxService.selectById(getUserModel().getId());
		if(acc!=null){
			json.setResult(true);
			if(CodeConstants.ADMIN.equals(getUserModel().getLoginId())){
				json.setObject(CodeConstants.ADMIN);
			}else{
				json.setObject(acc.getCards());
			}
			return json;
		}
		json.setResult(false);
		return json;
	}

	/**
	 *
	 *
	 * 方法描述: [授权.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月13日-下午9:55:43<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param id
	 * @return
	 * ResultJson
	 *
	 */
	@RequestMapping("authCode")
	public ResultJson authCode(@RequestParam String id){
		ResultJson json =new ResultJson();
		json.setResult(glyxxService.authCode(id));
		return json;
	}


	/**
	 *
	 *
	 * 方法描述: [查询统计代理商玩家的总数和充值的总金额数.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月19日-下午7:27:09<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * Page<ViewStatistics>
	 *
	 */
	@RequestMapping("selectStatistics")
	public Page<ViewStatistics> selectStatistics(@RequestBody ParamStatistics param){

		if(!StringUtils.isBlank(param.getFlag())){
			switch (param.getFlag()) {
				case CodeConstants.FLAG_D:
					param.setSdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 00:00:00");
					param.setEdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 23:59:59");
					break;
				case CodeConstants.FLAG_M:
					param.setSmouth(DateUtil.getMouthFirst());
					param.setEmouth(DateUtil.getMouthLast());
					break;
				case CodeConstants.FLAG_Y:
					param.setSyear(DateUtil.getCurrYearFirst());
					param.setEyear(DateUtil.getCurrYearLast());
					break;
			}
		}
		return glyxxService.selectStatistics(param);
	}
}
