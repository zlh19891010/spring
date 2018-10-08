package com.admin.service.glyxx;

import com.admin.entity.glygl.TGlptGlyglGlyxx;
import com.admin.param.LoginParam;
import com.admin.param.ParamStatistics;
import com.admin.param.YhxxParam;
import com.admin.view.ResultJson;
import com.admin.view.ViewStatistics;
import com.admin.view.ViewUserModel;
import com.admin.view.ViewYhxx;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 *
 * ClassName: TGlptGlyglGlyxxService <br/>
 * Function: 管理员信息服务. <br/>
 * Date: 2017年3月6日 上午10:37:28 <br/>
 *
 * @author 刘成
 * @version 1.0
 * @since JDK 1.7
 */
public interface TGlptGlyglGlyxxService extends IService<TGlptGlyglGlyxx> {

	/**
	 *
	 * getGlyxx,(获取管理员信息.) <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月6日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月6日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param loginParam 条件
	 * @return 结果
	 * @since JDK 1.7
	 */
	TGlptGlyglGlyxx getGlyxx(LoginParam loginParam);

	/**
	 *
	 * addGly,(增加管理员记录). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param tGlptGlyglGlyxx 参数
	 * @param zh 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	boolean addGly(TGlptGlyglGlyxx tGlptGlyglGlyxx, String zh);

	/**
	 *
	 * modifyGly,(修改管理员记录). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param tGlptGlyglGlyxx 参数
	 * @param zh 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	ResultJson modifyGly(TGlptGlyglGlyxx tGlptGlyglGlyxx, String zh);

	/**
	 *
	 * getGlyxxList,(管理员信息分业查询). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param yhxxParam 参数
	 * @param zh 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	Page<ViewYhxx> getGlyxxList(YhxxParam yhxxParam, ViewUserModel userModel);

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
	TGlptGlyglGlyxx findById(String id);

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
	 * @param zh 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	ResultJson modifyDelflag(String id, String zh);

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
	String getViewAllMenu(String jsId, String jsgs);

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
	//    ViewJsQx getJsglAllMenu(String jsgs, String jsid);

	/**
	 *
	 * loginpost,(登录). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月8日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月8日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param loginParam
	 * @return
	 * @since JDK 1.7
	 */

	ResultJson loginpost(LoginParam loginParam);

	/**
	 * selectGlyxxByLoginId,(这里用一句话描述这个方法的作用). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月21日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月21日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param loginId 用户名
	 * @return 实体
	 * @since JDK 1.7
	 */
	TGlptGlyglGlyxx selectGlyxxByLoginId(String loginId);

	/**
	 *
	 *
	 * 方法描述: [分配点卡.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月12日-下午7:08:15<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param cards
	 * @param id
	 * @return
	 * boolean
	 *
	 */
	boolean updateCards(String cards,String id,String yhmc,ViewUserModel user);

	/**
	 *
	 *
	 * 方法描述: [授权.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月13日-下午9:56:26<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param id
	 * @return
	 * boolean
	 *
	 */
	boolean authCode(String id);

	/**
	 *
	 *
	 * 方法描述: [查询统计代理商玩家的总数和充值的总金额数.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月19日-下午7:54:14<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param param
	 * @return
	 * Page<ViewStatistics>
	 *
	 */
	Page<ViewStatistics> selectStatistics(ParamStatistics param);
}
