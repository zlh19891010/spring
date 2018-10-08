package com.admin.service.yhgl.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.common.CodeConstants;
import com.admin.common.GlobalConstants;
import com.admin.common.logger.ModuleInfo;
import com.admin.common.logger.MonitorLog;
import com.admin.common.logger.MonitorLogInfo;
import com.admin.dao.yhgl.TGlptYhglYhxxMapper;
import com.admin.entity.yhgl.TGlptYhglYhxx;
import com.admin.param.YhxxParam;
import com.admin.service.yhgl.TGlptYhglYhxxService;
import com.admin.utils.Md5Utils;
import com.admin.view.ResultJson;
import com.admin.view.ViewUserModel;
import com.admin.view.ViewYhxx;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author shaopei
 * @since 2017-03-01
 */
@Service
public class TGlptYhglYhxxServiceImpl extends ServiceImpl<TGlptYhglYhxxMapper, TGlptYhglYhxx> implements TGlptYhglYhxxService {

	/**
	 * 用户信息Mapper
	 */
	@Autowired
	private TGlptYhglYhxxMapper tGlptYhglYhxxMapper;

	/**
	 * selectYhxxList,(分页查询用户信息). <br/>
	 * Author: shaopei <br/>
	 * Create Date: 2017年3月1日 <br/>
	 * =============================================================== <br/>
	 * Modifier:shaopei<br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param yhxxParam
	 * @param listMoudleView
	 * @return
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "获取用户信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	@Override
	public Page<ViewYhxx> selectYhxxList(YhxxParam yhxxParam, ViewUserModel userModel) {
		logger.info("===开始查询事例===");
		Page<ViewYhxx> page = new Page<ViewYhxx>(yhxxParam.getCurrent(), yhxxParam.getSize());
		if (!GlobalConstants.ADMIN_JS_ID.equals(userModel.getId())) {
			yhxxParam.setCreaterCode(userModel.getZh());
		}
		List<ViewYhxx> result = tGlptYhglYhxxMapper.selectYhxxList(page, yhxxParam);
		page.setRecords(result);
		logger.info("===完成查询事例===");
		return page;
	}

	/**
	 * selectById,(根据id查询用户信息). <br/>
	 * Author: shaopei <br/>
	 * Create Date: 2017年3月1日 <br/>
	 * =============================================================== <br/>
	 * Modifier:shaopei<br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param id
	 * @return
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "根据id查询用户信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	@Override
	public TGlptYhglYhxx findById(String id) {
		logger.info("===开始查询事例===");
		TGlptYhglYhxx tGlptYhglYhxx = selectById(id);
		logger.info("===完成查询事例===");
		return tGlptYhglYhxx;
	}

	/**
	 * insertYhxx,(新增用户). <br/>
	 * Author: shaopei <br/>
	 * Create Date: 2017年3月1日 <br/>
	 * =============================================================== <br/>
	 * Modifier:shaopei<br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param tGlptYhglYhxx 条件
	 * @param userModel 条件
	 * @return 结果
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "新增用户信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	@Override
	public boolean insertYhxx(TGlptYhglYhxx tGlptYhglYhxx, ViewUserModel userModel) {
		logger.info("===开始新增事例===");
		tGlptYhglYhxx.setId(getKey());
		String zh = getKey().substring(getKey().length() - Integer.valueOf("5"), getKey().length());
		tGlptYhglYhxx.setZh("2" + zh);
		tGlptYhglYhxx.setMm(Md5Utils.MD5(tGlptYhglYhxx.getMm()));
		tGlptYhglYhxx.setDelflag(CodeConstants.DELFLAG_A);
		tGlptYhglYhxx.setYhzt(CodeConstants.QYZT_WQY);
		if (tGlptYhglYhxx.getJgid() == null || "".equals(tGlptYhglYhxx.getJgid())) {
			tGlptYhglYhxx.setJgid(tGlptYhglYhxx.getId());
			tGlptYhglYhxx.setSsjgmc(tGlptYhglYhxx.getYhmc());
		}
		//管理员级别
		tGlptYhglYhxx.setGlyjb(CodeConstants.GLY_JB);
		tGlptYhglYhxx.setSjglyid(tGlptYhglYhxx.getId());
		tGlptYhglYhxx.setOperateTime(new Date());
		tGlptYhglYhxx.setOperaterCode(userModel.getZh());
		tGlptYhglYhxx.setCreaterCode(userModel.getZh());
		tGlptYhglYhxx.setCreateTime(new Date());
		boolean flag = insert(tGlptYhglYhxx);
		logger.info("===完成增加事例===");
		return flag;
	}

	/**
	 * updateYhxx,(更新用户用信息). <br/>
	 * Author: shaopei <br/>
	 * Create Date: 2017年3月1日 <br/>
	 * =============================================================== <br/>
	 * Modifier:shaopei<br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param tGlptYhglYhxx
	 * @param user
	 * @return
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "修改用户信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	@Override
	public boolean updateYhxx(TGlptYhglYhxx tGlptYhglYhxx, String user) {

		logger.info("===开始修改事例===");
		if (tGlptYhglYhxx.getMm() != null || !"".equals(tGlptYhglYhxx.getMm())) {
			tGlptYhglYhxx.setMm(Md5Utils.MD5(tGlptYhglYhxx.getMm()));
		}
		tGlptYhglYhxx.setOperaterCode(user);
		tGlptYhglYhxx.setDelflag(CodeConstants.DELFLAG_U);
		tGlptYhglYhxx.setOperateTime(new Date());
		boolean flag = updateById(tGlptYhglYhxx);
		logger.info("===完成修改事例===");
		return flag;
	}

	/**
	 * updateYhxx,(删除用户用信息). <br/>
	 * Author: shaopei <br/>
	 * Create Date: 2017年3月1日 <br/>
	 * =============================================================== <br/>
	 * Modifier:shaopei<br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param tGlptYhglYhxx
	 * @param user
	 * @return
	 * @since JDK 1.7
	 */
	@MonitorLog(text = "删除用户信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.YHGL)
	@Override
	public ResultJson removeYhxx(TGlptYhglYhxx tGlptYhglYhxx, String user) {
		ResultJson resultJson = new ResultJson(false);

		List<TGlptYhglYhxx> yhxxList = tGlptYhglYhxxMapper.selectYhxxBySjglyId(tGlptYhglYhxx.getId());
		if (CollectionUtils.isNotEmpty(yhxxList) && yhxxList.size() > 1) {
			resultJson.setMessage("该账号下存在正在使用的子账号，不能删除！ ");
			return resultJson;
		}

		logger.info("===开始删除事例===");
		tGlptYhglYhxx.setOperaterCode(user);
		tGlptYhglYhxx.setDelflag(CodeConstants.DELFLAG_D);
		tGlptYhglYhxx.setOperateTime(new Date());
		boolean flag = updateById(tGlptYhglYhxx);
		if (flag) {
			resultJson.setResult(true);
		}
		logger.info("===完成删除事例===");
		return resultJson;
	}

	/**
	 * selectYhByLoginid,(根据loginid查询校验重复). <br/>
	 * Author: shaopei <br/>
	 * Create Date: 2017年3月21日 <br/>
	 * =============================================================== <br/>
	 * Modifier:shaopei<br/>
	 * Modify Date: 2017年3月21日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 * 
	 * @param loginid 登录Id
	 * @param jgId 机构Id
	 * @return
	 * @since JDK 1.7
	 */
	@Override
	public TGlptYhglYhxx selectYhByLoginid(String loginid, String jgId) {

		return tGlptYhglYhxxMapper.selectYhByLoginid(loginid, jgId);
	}

	/**
	 * selectYhByLoginidForSuper,(根据loginid查询校验重复, 运营中心超级管理员重复校验). <br/>
	 * Author: weiming.chen <br/>
	 * Create Date: 2017年4月17日 <br/>
	 * =============================================================== <br/>
	 * Modifier: weiming.chen <br/>
	 * Modify Date: 2017年4月17日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 * 
	 * @param loginid
	 * @return
	 * @since JDK 1.7
	 */
	@Override
	public TGlptYhglYhxx selectYhByLoginidForSuper(String loginid) {
		return tGlptYhglYhxxMapper.selectYhByLoginidForSuper(loginid);
	}
}
