package com.admin.service.glyxx.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.common.CodeConstants;
import com.admin.common.GlobalConstants;
import com.admin.common.logger.ModuleInfo;
import com.admin.common.logger.MonitorLog;
import com.admin.common.logger.MonitorLogInfo;
import com.admin.content.CommonConstants;
import com.admin.dao.account.QpaccountdbMapper;
import com.admin.dao.glygl.TGlptGlyglGlyxxMapper;
import com.admin.dao.jsgl.TGlptJsglJsxxMapper;
import com.admin.dao.order.OrderInfoMapper;
import com.admin.dao.xtgl.TGlptXtglMoudleMapper;
import com.admin.dataSource.DynamicDataSource;
import com.admin.entity.account.Qpaccountdb;
import com.admin.entity.cards.TGlptCards;
import com.admin.entity.glygl.TGlptGlyglGlyxx;
import com.admin.entity.jsgl.TGlptJsglJsxx;
import com.admin.entity.order.OrderInfo;
import com.admin.param.LoginParam;
import com.admin.param.ParamStatistics;
import com.admin.param.YhxxParam;
import com.admin.service.cards.TGlptCardsService;
import com.admin.service.glyxx.TGlptGlyglGlyxxService;
import com.admin.service.jsgl.TGlptJsglJsxxService;
import com.admin.utils.CommonUtil;
import com.admin.utils.Md5Utils;
import com.admin.view.ResultJson;
import com.admin.view.ViewAllMenu;
import com.admin.view.ViewStatistics;
import com.admin.view.ViewTGlptXtglMoudle;
import com.admin.view.ViewUserModel;
import com.admin.view.ViewYhxx;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 管理员信息 服务实现类
 * </p>
 *
 * @author LIUCHENG
 * @since 2017-03-01
 */
@Service
public class TGlptGlyglGlyxxServiceImpl extends ServiceImpl<TGlptGlyglGlyxxMapper, TGlptGlyglGlyxx> implements TGlptGlyglGlyxxService {

	/** 管理员信息Mapper */
	@Autowired
	private TGlptGlyglGlyxxMapper tGlptGlyglGlyxxMapper;

	/** 角色信息Mapper */
	@Autowired
	private TGlptJsglJsxxMapper tGlptJsglJsxxMapper;

	/** 模块菜单Mapper */
	@Autowired
	private TGlptXtglMoudleMapper tGlptXtglMoudleMapper;

	/**
	 * 发送点卡的Service
	 */
	@Autowired
	private TGlptCardsService tGlptCardsService;


	/**
	 * 角色信息服务
	 */
	@Autowired
	private TGlptJsglJsxxService tGlptJsglJsxxService;

	/**
	 * 订单mapper
	 */
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	/**
	 * 玩家mapper
	 */
	@Autowired
	private QpaccountdbMapper qpaccountdbMapper;

	/**
	 * 全部菜单Map
	 */
	Map<String, ViewTGlptXtglMoudle> mapAllMenu = new HashMap<String, ViewTGlptXtglMoudle>();

	/**
	 * 临时菜单Map
	 */
	Map<String, ViewTGlptXtglMoudle> tempMenu = new HashMap<String, ViewTGlptXtglMoudle>();

	/**
	 *
	 * getGlyxx,(获取管理员用户信息). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月8日 <br/>
	 * =============================================================== <br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月8日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param zh
	 * @param mm
	 * @return
	 * @since JDK 1.7
	 */
	@Override
	@MonitorLog(text = "获取管理员用户信息",containRequest=true, type = MonitorLogInfo.METHOD_TYPE_SERVICE,module=ModuleInfo.YHGL)
	public TGlptGlyglGlyxx getGlyxx(LoginParam loginParam) {
		logger.info(loginParam + "loginParam");
		TGlptGlyglGlyxx glyxx = null;
		if (!CommonConstants.parameterIsNull(loginParam.getUserName(), loginParam.getMm())) {
			String mm = Md5Utils.MD5(loginParam.getMm());
			glyxx = tGlptGlyglGlyxxMapper.getGlyxx(loginParam.getUserName(), mm);
		}

		return glyxx;
	}

	/**
	 *
	 * addGly,(增加管理员记录). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * =============================================================== <br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param tGlptGlyglGlyxx 参数信息
	 * @param zh 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@Override
	@MonitorLog(text = "新增用户service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	public boolean addGly(TGlptGlyglGlyxx tGlptGlyglGlyxx, String zh) {
		boolean flag = false;
		logger.info("===开始增加管理员===");
		tGlptGlyglGlyxx.setId(getKey());
		//账号取1+主键后五位
		tGlptGlyglGlyxx.setZh("1" + new StringBuffer(tGlptGlyglGlyxx.getId()).reverse().substring(0, Integer.valueOf("5")));
		//初始默认未启用
		tGlptGlyglGlyxx.setGlyZt(CodeConstants.QYZT_WQY);
		//密码加密
		tGlptGlyglGlyxx.setMm(Md5Utils.MD5(tGlptGlyglGlyxx.getMm()));
		tGlptGlyglGlyxx.setDelflag(CodeConstants.DELFLAG_A);
		tGlptGlyglGlyxx.setCreaterCode(zh);
		tGlptGlyglGlyxx.setCreateTime(new Date());
		tGlptGlyglGlyxx.setOperaterCode(zh);
		tGlptGlyglGlyxx.setOperateTime(new Date());
		flag = insert(tGlptGlyglGlyxx);
		logger.info("===完成增加管理员===");
		return flag;
	}

	/**
	 *
	 * modifyGly,(修改管理员记录). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * =============================================================== <br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param tGlptGlyglGlyxx 参数
	 * @param zh 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@Override
	public ResultJson modifyGly(TGlptGlyglGlyxx tGlptGlyglGlyxx, String zh) {
		ResultJson result = new ResultJson(false);

		logger.info("===开始修改管理员===");
		if (StringUtils.isBlank(tGlptGlyglGlyxx.getId())) {
			return result;
		}
		if (tGlptGlyglGlyxx.getMm() != null && !"".equals(tGlptGlyglGlyxx.getMm())) {
			//密码加密
			tGlptGlyglGlyxx.setMm(Md5Utils.MD5(tGlptGlyglGlyxx.getMm()));
		}
		tGlptGlyglGlyxx.setDelflag(CodeConstants.DELFLAG_U);
		tGlptGlyglGlyxx.setOperaterCode(zh);
		tGlptGlyglGlyxx.setOperateTime(new Date());
		result.setResult(updateById(tGlptGlyglGlyxx));
		logger.info("===完成修改管理员===");
		return result;
	}

	/**
	 *
	 * getGlyxxList,(管理员信息分业查询). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * =============================================================== <br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param yhxxParam 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@Override
	public Page<ViewYhxx> getGlyxxList(YhxxParam yhxxParam, ViewUserModel userModel) {
		logger.info("===开始管理员分页===");
		Page<ViewYhxx> page = new Page<ViewYhxx>(yhxxParam.getCurrent(), yhxxParam.getSize());
		//非内置超级管理员的管理员信息
		yhxxParam.setId(CodeConstants.CJGLY_BH);
		yhxxParam.setZh(userModel.getZh());
		if (!GlobalConstants.ADMIN_JS_ID.equals(userModel.getId())) {
			yhxxParam.setCreaterCode(userModel.getZh());
		}
		List<ViewYhxx> result = tGlptGlyglGlyxxMapper.selectGlyxxList(page, yhxxParam);
		page.setRecords(result);
		logger.info("===完成管理员分页===");
		return page;
	}

	/**
	 *
	 * findById,(根据主键查询管理员信息). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * =============================================================== <br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param id 主键
	 * @return 结果
	 * @since JDK 1.7
	 */
	@Override
	public TGlptGlyglGlyxx findById(String id) {
		logger.info("===开始查询事例===");
		TGlptGlyglGlyxx tGlptGlyglGlyxx = selectById(id);
		logger.info("===完成查询事例===");
		return tGlptGlyglGlyxx;
	}

	/**
	 *
	 * modifyDelflag,(管理员置D). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * =============================================================== <br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param id 主键
	 * @param zh 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	@Override
	public ResultJson modifyDelflag(String id, String zh) {
		ResultJson result = new ResultJson(false);
		TGlptGlyglGlyxx tGlptGlyglGlyxx = new TGlptGlyglGlyxx();
		tGlptGlyglGlyxx.setId(id);
		logger.info("===开始置D管理员===");
		if (StringUtils.isBlank(tGlptGlyglGlyxx.getId())) {
			return result;
		}
		tGlptGlyglGlyxx.setDelflag(CodeConstants.DELFLAG_D);
		tGlptGlyglGlyxx.setOperaterCode(zh);
		tGlptGlyglGlyxx.setOperateTime(new Date());
		result.setResult(updateById(tGlptGlyglGlyxx));
		logger.info("===完成置D管理员===");
		return result;
	}

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
	@Override
	@MonitorLog(text = "管理员登录", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	public ResultJson loginpost(LoginParam loginParam) {
		ResultJson result = new ResultJson(false);

		TGlptGlyglGlyxx glyxx = getGlyxx(loginParam);
		if (glyxx != null) {
			if (!glyxx.getGlyZt().equals(CodeConstants.GLYZT_YQY)) {
				result.setMessage("管理员未启用！");
				return result;
			}
			ViewUserModel userMoudle = new ViewUserModel();
			userMoudle.setZh(glyxx.getZh());
			userMoudle.setTx(glyxx.getYhtx());
			userMoudle.setLoginId(glyxx.getLoginId());
			userMoudle.setId(glyxx.getId());
			userMoudle.setCards(glyxx.getCards().toString());
			userMoudle.setsAuthCode(glyxx.getsAuthCode());

			TGlptJsglJsxx jsxx = new TGlptJsglJsxx();
			jsxx.setJsgs(CodeConstants.JSGS_GLPT);
			jsxx.setId(glyxx.getJsId());
			jsxx.setJszt(CodeConstants.QYZT_YQY);
			logger.info("效验角色是否可用 ");
			jsxx = tGlptJsglJsxxMapper.getJsxx(jsxx);
			logger.info("效验角色是否可用 " + jsxx);

			mapAllMenu = getAllMenuMap(CodeConstants.MOUDLE_XTDM_GLPT);
			//临时存储菜单节点 用于对比父节点是否存在
			tempMenu = new HashMap<String, ViewTGlptXtglMoudle>();
			//可用 查询菜单信息
			if (jsxx != null) {
				//显示菜单
				List<ViewTGlptXtglMoudle> childMoudleViewResult = new ArrayList<ViewTGlptXtglMoudle>();
				userMoudle.setJsId(jsxx.getId());
				//拥有权限的所有子节点菜单
				List<ViewTGlptXtglMoudle> childMoudleView = tGlptXtglMoudleMapper.findMoudleList(CodeConstants.JSGS_GLPT, glyxx.getJsId(), CodeConstants.MOUDLE_XTDM_GLPT, CodeConstants.STATUS_ZC);
				if (childMoudleView == null || childMoudleView.size() < 1) {
					result.setMessage("无角色权限！");
					return result;
				}
				//权限表只存子节点权限
				//通过子节点查询出所有父节点，把父节点放入显示菜单
				//子节点如果在已存在的父节点中  添加至父节点的节点支点上
				for (int i = 0; i < childMoudleView.size(); i++) {
					tempMenu.put(childMoudleView.get(i).getId(), childMoudleView.get(i));
					ViewTGlptXtglMoudle element = getParentMenu(childMoudleView.get(i), childMoudleViewResult);
					if (element != null) {
						childMoudleViewResult.add(element);
					}
				}
				//集合通过displayOrder顺序显示菜单
				Collections.sort(childMoudleViewResult);
				//菜单存入cookies
				userMoudle.setListMoudle(childMoudleViewResult);
				result.setResult(true);
				result.setObject(userMoudle);
			} else {
				result.setMessage("角色菜单不存在！");
			}

			return result;

		} else {
			result.setMessage("用户名或密码错误!");
			return result;
		}
	}

	/**
	 *
	 * getAllMenuMap,(获取所有菜单). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月4日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月4日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @return
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "获取所有菜单", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	public Map<String, ViewTGlptXtglMoudle> getAllMenuMap(String xtdm) {
		List<ViewTGlptXtglMoudle> listMoudle = tGlptXtglMoudleMapper.getMoudleList(xtdm, CodeConstants.STATUS_ZC);
		Map<String, ViewTGlptXtglMoudle> mapAllMenu = new HashMap<String, ViewTGlptXtglMoudle>();
		for (ViewTGlptXtglMoudle viewTGlptXtglMoudle : listMoudle) {
			mapAllMenu.put(viewTGlptXtglMoudle.getId(), viewTGlptXtglMoudle);
		}
		return mapAllMenu;
	}

	/**
	 *
	 * getParentMenu,(递归查询父节点). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月4日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月4日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param parentModule
	 * @return
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "递归查询父节点", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	public ViewTGlptXtglMoudle getParentMenu(ViewTGlptXtglMoudle parentModule, List<ViewTGlptXtglMoudle> childMoudleViewResult) {
		ViewTGlptXtglMoudle fujiedian = new ViewTGlptXtglMoudle();
		//拥有父菜单
		if (parentModule.getParentId() != null) {
			fujiedian = mapAllMenu.get(parentModule.getParentId().toString());
			if (tempMenu.put(fujiedian.getId(), fujiedian) != null) {
				//存在父节点  冲树形对象中找到节点 添加
				//                childMoudleView.remove(parentModule);
				addNode(parentModule, childMoudleViewResult);
				return null;
			}
			List<ViewTGlptXtglMoudle> viewTGlptXtglMoudle = new ArrayList<ViewTGlptXtglMoudle>();
			viewTGlptXtglMoudle.add(parentModule);
			fujiedian.setViewListMoudle(viewTGlptXtglMoudle);
			return getParentMenu(fujiedian, childMoudleViewResult);
		} else {
			return parentModule;
		}
	}

	/**
	 *
	 * add,(查找父节点,把子节点添加至父节点). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param parentModule
	 * @param AllMoudleView
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "查找父节点,把子节点添加至父节点", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	public void addNode(ViewTGlptXtglMoudle parentModule, List<ViewTGlptXtglMoudle> AllMoudleView) {
		ViewTGlptXtglMoudle fujiedian = mapAllMenu.get(parentModule.getParentId().toString());
		for (ViewTGlptXtglMoudle viewTGlptXtglMoudle : AllMoudleView) {
			if (viewTGlptXtglMoudle.getId().equals(fujiedian.getId())) {
				viewTGlptXtglMoudle.getViewListMoudle().add(parentModule);
			} else {
				if (viewTGlptXtglMoudle.getViewListMoudle() != null && viewTGlptXtglMoudle.getViewListMoudle().size() > 0) {
					addNode(parentModule, viewTGlptXtglMoudle.getViewListMoudle());
				}

			}
		}
	}

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
	@Override
	//@MonitorLog(text = "获取菜单树形平级视图", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	public String getViewAllMenu(String jsId, String jsgs) {
		mapAllMenu = getAllMenuMap(jsgs);
		List<ViewAllMenu> list = new ArrayList<ViewAllMenu>();
		//缓存菜单
		tempMenu = new HashMap<String, ViewTGlptXtglMoudle>();
		//可用 查询菜单信息
		List<ViewTGlptXtglMoudle> childMoudleView = tGlptXtglMoudleMapper.findMoudleList(jsgs, jsId, jsgs, CodeConstants.STATUS_ZC);
		for (ViewTGlptXtglMoudle viewTGlptXtglMoudle : childMoudleView) {
			String pid = viewTGlptXtglMoudle.getParentId() == null ? "" : viewTGlptXtglMoudle.getParentId();
			//把菜单添加至list集合
			list.add(new ViewAllMenu(viewTGlptXtglMoudle.getId(), pid, viewTGlptXtglMoudle.getModuleName(), viewTGlptXtglMoudle.getCzjb(), false));
			//把子节点添加至缓存菜单
			tempMenu.put(viewTGlptXtglMoudle.getId(), viewTGlptXtglMoudle);
			if (viewTGlptXtglMoudle.getModuleLevel() != 1) {
				//递归获取子节点的父节点下所有节点
				List<ViewAllMenu> simplelistMenu = getViewAllMenu(viewTGlptXtglMoudle, list);
				if (simplelistMenu != null && simplelistMenu.size() > 0) {
					list.addAll(simplelistMenu);
				}
			}

		}

		String jsonArr = JSONArray.toJSONString(list);
		return jsonArr;
	}

	/**
	 *
	 * getViewAllMenu,(递归添加节点). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param parentModule
	 * @param listViewAllMenu
	 * @return
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "递归添加节点", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	public List<ViewAllMenu> getViewAllMenu(ViewTGlptXtglMoudle parentModule, List<ViewAllMenu> listViewAllMenu) {
		ViewTGlptXtglMoudle fujiedian = new ViewTGlptXtglMoudle();
		//拥有父菜单
		if (parentModule.getParentId() != null) {
			fujiedian = mapAllMenu.get(parentModule.getParentId().toString());
			//检查父节点 是否存在，不存在
			if (tempMenu.put(fujiedian.getId(), fujiedian) == null) {
				String pid = fujiedian.getParentId() == null ? "" : fujiedian.getParentId();
				listViewAllMenu.add(new ViewAllMenu(fujiedian.getId(), pid, fujiedian.getModuleName(), fujiedian.getCzjb(), false));
			}
			return getViewAllMenu(fujiedian, listViewAllMenu);
		} else {
			return new ArrayList<ViewAllMenu>();
		}
	}

	/**
	 *
	 * getListByParentId,(通过父节点菜单集合). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月4日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月4日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param parentId
	 * @param mapAllMenu
	 * @return
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "通过父节点菜单集合", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	public List<ViewTGlptXtglMoudle> getListByParentId(String parentId, Map<String, ViewTGlptXtglMoudle> mapAllMenu) {
		List<ViewTGlptXtglMoudle> listMoudle = new ArrayList<ViewTGlptXtglMoudle>();
		for (ViewTGlptXtglMoudle moudle : mapAllMenu.values()) {
			if (moudle.getParentId() != null) {
				if (moudle.getParentId().equals(parentId)) {
					listMoudle.add(moudle);
				}
			}
		}
		return listMoudle;
	}

	/**
	 *
	 * getAllMenu,(通过父菜单获取所有菜单). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月3日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月3日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param parentModuleList
	 * @return
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "通过父菜单获取所有菜单", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	public List<ViewTGlptXtglMoudle> getAllMenu(Map<String, ViewTGlptXtglMoudle> mapall, String jsgs, String jsid, List<ViewTGlptXtglMoudle> parentModuleList) {
		//遍历所有父菜单
		for (ViewTGlptXtglMoudle parentModule : parentModuleList) {
			//查看父菜单下是否拥有子菜单
			List<ViewTGlptXtglMoudle> zcd1 = getListByParentId(parentModule.getId(), mapall);
			if (zcd1.size() > 0) {
				//拥有子菜单 给父菜单新增子菜单
				parentModule.setViewListMoudle(getAllMenu(mapall, jsgs, jsid, zcd1));
			}
		}
		return parentModuleList;
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
	//    @Override
	//    @MonitorLog(text = "角色管理-获取所有菜单&已分配的菜", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
	//    public ViewJsQx getJsglAllMenu(String jsgs, String jsid) {
	//        ViewJsQx result = new ViewJsQx();
	//        //获取所有一级父菜单
	//        List<ViewTGlptXtglMoudle> parentModuleList = tGlptXtglMoudleMapper.getParentModule(jsgs, CodeConstants.STATUS_ZC);
	//
	//        Map<String, ViewTGlptXtglMoudle> mapall = getAllMenuMap(jsgs);
	//        //角色id,已经存在，则从总菜单中剔除已有的权限，获取可分配权限
	//        if (StringUtils.isNotBlank(jsid)) {
	//            List<TGlptJsglJsyqxdygx> yfpqx = tGlptJsglJsxxService.getDygxListByParam(jsgs, jsid);
	//            for (TGlptJsglJsyqxdygx tGlptJsglJsyqxdygx : yfpqx) {
	//                mapall.remove(tGlptJsglJsyqxdygx.getQxCode());
	//            }
	//        }
	//        //获取可分配的菜单
	//        List<ViewTGlptXtglMoudle> kfpList = getAllMenu(mapall, jsgs, jsid, parentModuleList);
	//        result.setKfpList(kfpList);
	//
	//        //角色编号不为空，获取已分配角色
	//        if (StringUtils.isNotBlank(jsid)) {
	//            List<ViewTGlptXtglMoudle> childMoudleView = tGlptXtglMoudleMapper.findMoudleList(jsgs, jsid, jsgs, CodeConstants.STATUS_ZC);
	//            for (int i = 0; i < childMoudleView.size(); i++) {
	//                if (childMoudleView.get(i).getModuleLevel() != 1) {
	//                    ViewTGlptXtglMoudle element = getParentMenu(childMoudleView.get(i), childMoudleView);
	//                    childMoudleView.set(i, element);
	//                }
	//            }
	//            result.setYfpList(childMoudleView);
	//        }
	//
	//        return result;
	//    }

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
	@Override
	public TGlptGlyglGlyxx selectGlyxxByLoginId(String loginId) {

		return tGlptGlyglGlyxxMapper.selectGlyxxByLoginId(loginId);
	}

	/**
	 *
	 *
	 * 方法描述: [分配点卡.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月12日-下午7:09:30<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public boolean updateCards(String cards, String id,String yhmc,ViewUserModel user) {


		TGlptCards entity = new TGlptCards();
		entity.setCard(Long.valueOf(cards));
		entity.setCreaterCode(user.getId());
		entity.setCreateTime(new Date());
		entity.setStrAccounts(id);
		entity.setDelflag(CodeConstants.DELFLAG_A);
		entity.setId(getKey());
		entity.setStrNickName(yhmc);
		tGlptCardsService.insert(entity);

		TGlptGlyglGlyxx acc= selectById(id);
		if(acc!=null){
			cards=Long.toString(Long.valueOf(cards)+acc.getCards());
		}

		int res =tGlptGlyglGlyxxMapper.updateCards(cards, id);
		if(res>0){
			return true;
		}
		return false;
	}

	/**
	 *
	 *
	 * 方法描述: [授权]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月13日-下午9:56:48<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public boolean authCode(String id) {

		TGlptGlyglGlyxx glx =new TGlptGlyglGlyxx();
		glx.setId(id);
		glx.setsAuthCode(getAuthCode());
		int res=tGlptGlyglGlyxxMapper.updateById(glx);
		if(res>0){
			return true;
		}
		return false;
	}

	/**
	 *
	 *
	 * 方法描述: [生产数据库唯一授权码.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月13日-下午10:18:01<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * String
	 *
	 */
	private String  getAuthCode(){
		String authCode=	CommonUtil.getAuthCode2();
		Wrapper<TGlptGlyglGlyxx> wrapper = new EntityWrapper<TGlptGlyglGlyxx>();
		wrapper.where("s_auth_code= {0}", authCode);
		int count=tGlptGlyglGlyxxMapper.selectCount(wrapper);
		if(count>0){
			getAuthCode();
		}
		return authCode;
	}

	/**
	 *
	 *
	 * 方法描述: [查询统计代理商玩家的总数和充值的总金额数.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月19日-下午7:54:40<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public Page<ViewStatistics> selectStatistics(ParamStatistics param) {

		logger.info("===开始查询代理商玩家的总数和充值的总金额数分页===");
		Page<ViewStatistics> page = new Page<ViewStatistics>(param.getCurrent(), param.getSize());
		List<TGlptGlyglGlyxx> list = tGlptGlyglGlyxxMapper.selectZhList(page,param);

		List<ViewStatistics> viewList = new ArrayList<ViewStatistics>();
		for(TGlptGlyglGlyxx glyxx:list){
			if(StringUtils.isBlank(glyxx.getsAuthCode())){
				continue;
			}
			logger.info("开始切换游戏数据库。。。");
			DynamicDataSource.setDbType(CodeConstants.GAME_DATA_SOURCE);//设置后 就OK
			ViewStatistics statistics =new ViewStatistics();
			param.setsAuthCode(glyxx.getsAuthCode());
			List<Qpaccountdb> wjList=qpaccountdbMapper.selectAccountByCode(param);
			statistics.setName(glyxx.getYhmc());
			statistics.setpCount(String.valueOf(wjList==null?0:wjList.size()));
			logger.info("开始切换web数据库。。。");
			DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
			Float total =0f;
			for(Qpaccountdb db :wjList){
				param.setOpenid(db.getStrOpenID());
				List<OrderInfo> orderList=orderInfoMapper.selectAmount(param);
				for(OrderInfo order:orderList){
					total+=Float.valueOf(order.getAmount());
				}
			}
			statistics.setpAmount(total.toString());
			viewList.add(statistics);
		}
		page.setRecords(viewList);
		logger.info("===结束查询代理商玩家的总数和充值的总金额数分页===");
		return page;
	}
}
