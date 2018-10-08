package com.admin.controller.yhgl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.common.CodeConstants;
import com.admin.entity.jsgl.TGlptJsglJsxx;
import com.admin.entity.yhgl.TGlptYhglYhxx;
import com.admin.param.YhxxParam;
import com.admin.service.jsgl.TGlptJsglJsxxService;
import com.admin.service.yhgl.TGlptYhglYhxxService;
import com.admin.view.ResultJson;
import com.admin.view.ViewYhxx;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author shaopei
 * @since 2017-03-01
 */
@RestController
public class TGlptYhglYhxxController extends BaseController {

	/**
	 * 用户信息service
	 */
	@Autowired
	private TGlptYhglYhxxService tGlptYhglYhxxService;

	/**
	 * 角色信息service
	 */
	@Autowired
	private TGlptJsglJsxxService tGlptJsglJsxxService;

	/**
	 * getYhxxPagination,(用户信息列表分页). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param yhxxParam 请求参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("getYhxxPagination")
	public Page<ViewYhxx> getYhxxPagination(@RequestBody YhxxParam yhxxParam) {
		return tGlptYhglYhxxService.selectYhxxList(yhxxParam, getUserModel());

	}

	/**
	 * getYhxxById,(根据id查询用户信息). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param id 请求参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("getYhxxbyId")
	public ResultJson getYhxxById(@RequestParam String id) {
		ResultJson resultJson = new ResultJson();
		resultJson.setObject(tGlptYhglYhxxService.selectById(id));
		return resultJson;
	}

	/**
	 * addYhxx,(增加用户信息). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param tGlptYhglYhxx 请求参数
	 * @return 解果
	 * @since JDK 1.7
	 */
	@RequestMapping("addYhxx")
	public ResultJson addYhxx(@RequestBody TGlptYhglYhxx tGlptYhglYhxx) {
		ResultJson resultJson = new ResultJson(false);
		TGlptYhglYhxx yhxx = tGlptYhglYhxxService.selectYhByLoginidForSuper(tGlptYhglYhxx.getLoginId());
		if (yhxx != null) {
			return new ResultJson(false, CodeConstants.ADD_JYCF);
		}
		boolean bool = tGlptYhglYhxxService.insertYhxx(tGlptYhglYhxx, getUserModel());
		resultJson.setResult(bool);
		return resultJson;
	}

	/**
	 * addYhxx,(修改用户信息). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param tGlptYhglYhxx 请求参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("modifyYhxx")
	public ResultJson updatYhxx(@RequestBody TGlptYhglYhxx tGlptYhglYhxx) {
		ResultJson resultJson = new ResultJson(false);
		boolean bool = tGlptYhglYhxxService.updateYhxx(tGlptYhglYhxx, getUserModel().getZh());
		resultJson.setResult(bool);
		return resultJson;
	}

	/**
	 * deleteYhxx,(删除用户信息). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param tGlptYhglYhxx 请求参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("removeYhxx")
	public ResultJson deleteYhxx(@RequestBody TGlptYhglYhxx tGlptYhglYhxx) {
		tGlptYhglYhxx.setOperaterCode(getUserModel().getZh());
		return tGlptYhglYhxxService.removeYhxx(tGlptYhglYhxx, getUserModel().getZh());
	}

	/**
	 * getJsxxAll,(获取所有角色信息). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月4日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月4日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param tGlptJsglJsxx 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@RequestMapping("getJsxxAll")
	public ResultJson getJsxxAll(@RequestBody TGlptJsglJsxx tGlptJsglJsxx) {
		ResultJson resultJson = new ResultJson();
		tGlptJsglJsxx.setId(getUserModel().getJsId());
		resultJson.setObject(tGlptJsglJsxxService.selectJsxx(tGlptJsglJsxx));
		return resultJson;
	}
}
