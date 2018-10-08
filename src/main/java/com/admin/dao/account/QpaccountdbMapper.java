package com.admin.dao.account;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.admin.entity.account.Qpaccountdb;
import com.admin.param.ParamAccount;
import com.admin.param.ParamStatistics;
import com.admin.view.ViewAccount;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlh
 * @since 2017-11-11
 */
public interface QpaccountdbMapper extends BaseMapper<Qpaccountdb> {


	/**
	 *
	 * selectGlyxxList,(查询游戏帐号信息列表). <br/>
	 * Author: zlh <br/>
	 * Create Date: 2017年11月11日 <br/>
	 * ===============================================================<br/>
	 * Modifier: zlh <br/>
	 * Modify Date: 2017年11月11日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param page 请求参数
	 * @param paramAccount 请求参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	List<ViewAccount> selectAccountList(Page<ViewAccount> page, ParamAccount paramAccount);

	List<Qpaccountdb> selectAccountByCode(ParamStatistics param);

	Integer selectOnCount(@Param("sAuthCode")String sAuthCode);

	String getUid(@Param("roleID")String roleID);
}