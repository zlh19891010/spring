package com.admin.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.common.CodeConstants;
import com.admin.common.logger.MonitorLog;
import com.admin.common.logger.MonitorLogInfo;
import com.admin.param.LoginParam;
import com.admin.service.glyxx.TGlptGlyglGlyxxService;
import com.admin.view.ResultJson;
import com.admin.view.ViewUserModel;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;

/**
 * ClassName: LoginController <br/>
 * Function: 登录. <br/>
 * Date: 2017年2月24日 上午11:49:18 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
@RestController()
public class LoginController extends BaseController {

	/**
	 * 管理员管理服务
	 */
	@Autowired
	private TGlptGlyglGlyxxService glyxxService;

	/**
	 * 
	 * loginpost,(平台登录). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月15日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月15日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @param loginParam 登录参数
	 * @return ResultJson
	 * @since JDK 1.7
	 */
	@Login(action = Action.Skip)
	@RequestMapping("loginpost")
	public ResultJson loginpost(@RequestBody LoginParam loginParam) {
		ResultJson result = new ResultJson(false);

		Object obj = request.getSession().getAttribute(CodeConstants.USER_MOUDLE);
		if (obj != null) {
			result.setResult(true);
			result.setObject(obj);
			return result;
		}
		result = glyxxService.loginpost(loginParam);
		if (result.getObject() != null) {
			request.getSession().setAttribute(CodeConstants.USER_MOUDLE, result.getObject());
		}

		return result;

	}

	/**
	 * logout,(退出登录). <br/>
	 * Author: weiming.chen <br/>
	 * Create Date: 2017年2月24日 <br/>
	 * ===============================================================<br/>
	 * Modifier: weiming.chen <br/>
	 * Modify Date: 2017年2月24日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @return ResultJson
	 * @since JDK 1.7
	 */
	@Login(action = Action.Skip)
	@RequestMapping("logout")
	public ResultJson logout() {
		ResultJson result = new ResultJson(true);
		request.getSession().invalidate();
		return result;
	}

	/**
	 * 
	 * getUserInfo,(获取用户信息). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月8日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月8日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @return ResultJson
	 * @since JDK 1.7
	 */
	@RequestMapping("getUserInfo")
	public ResultJson getUserInfo() {

		ResultJson result = new ResultJson(true);
		result.setObject(getUserModel());
		return result;
	}

	/**
	 * 
	 * getJsglAllMenu,(角色管理-获取所有菜单&已分配的菜). <br/>
	 * Author: tangli <br/>
	 * Create Date: 2017年3月6日 <br/>
	 * ===============================================================<br/>
	 * Modifier: tangli <br/>
	 * Modify Date: 2017年3月6日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @param jsgs 角色归属
	 * @param jsbh 角色编号
	 * @return 结果
	 * @since JDK 1.7
	 */
	//    @Login(action = Action.Skip)
	//    @RequestMapping("/am/getJsglAllMenu")
	//    public ViewJsQx getJsglAllMenu(@RequestParam String jsgs, @RequestParam String jsid) {
	//
	//        return glyxxService.getJsglAllMenu(jsgs, jsid);
	//    }

	/**
	 * 
	 * getViewAllMenu,(获取菜单树形平级视图). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @param jsId 角色id
	 * @param jsgs 角色归属
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("getViewAllMenu")
	public String getViewAllMenu(@RequestParam String jsId, @RequestParam String jsgs) {
		if (StringUtils.isBlank(jsId)) {
			ViewUserModel model = (ViewUserModel) request.getSession().getAttribute(CodeConstants.USER_MOUDLE);
			jsId = model.getJsId();
		}
		return glyxxService.getViewAllMenu(jsId, jsgs);
	}

}
