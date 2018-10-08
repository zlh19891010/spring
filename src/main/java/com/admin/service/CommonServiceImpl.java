/**
 * Project Name:vr-admin
 * File Name:CommonServiceImpl.java
 * Package Name:com.sd.vr.admin.service
 * Date:2017年3月4日上午11:28:03
 *
 */

package com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.admin.common.CodeConstants;
import com.admin.dao.TGlptCodeMapper;
import com.admin.dataSource.DynamicDataSource;
import com.admin.entity.TGlptCode;
import com.admin.service.account.QpaccountdbService;
import com.admin.utils.CommonUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * ClassName:CommonServiceImpl <br/>
 * Function: 码表获取. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月4日 上午11:28:03 <br/>
 *
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
public class CommonServiceImpl extends ServiceImpl<TGlptCodeMapper, TGlptCode> implements CommonService {

	@Autowired
	private QpaccountdbService qpaccountdbService;

	/** 码表Mapper */
	@Autowired
	private TGlptCodeMapper tGlptCodeMapper;


	/**
	 *
	 *
	 * 方法描述: [根据roleID获取UID.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2018年1月3日-下午10:48:19<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者        日期          修改内容<br/>
	 * ================================================<br/>
	 *
	 */
	@Override
	public String getUid(String roleID) {
		DynamicDataSource.setDbType(CodeConstants.GAME_DATA_SOURCE);//设置后 就OK
		String uid=qpaccountdbService.getUid(roleID);
		DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
		if(StringUtils.isBlank(uid)){
			return "";
		}
		return CommonUtil.frontCompWithZore(Integer.valueOf(uid),6);
	}

	/**
	 *
	 * getCodeListByCodeclass,(根据codeclass获取TYyxtCode列表). <br/>
	 * Author: nifang <br/>
	 * Create Date: 2017年2月21日 <br/>
	 * ===============================================================<br/>
	 * Modifier: nifang <br/>
	 * Modify Date: 2017年2月21日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param codeclass code类别
	 * @return TGlptCode列表
	 * @since JDK 1.7
	 */
	@Override
	public List<TGlptCode> getCodeListByCodeclass(String codeclass) {

		logger.info("根据codeclass获取TYyxtCode列表");
		return tGlptCodeMapper.getCodeListByCodeclass(codeclass);
	}

	/**
	 *
	 * getCodeByCodeclassAndCode,(根据codeclass和code获取TYyxtCode). <br/>
	 * Author: nifang <br/>
	 * Create Date: 2017年2月21日 <br/>
	 * ===============================================================<br/>
	 * Modifier: nifang <br/>
	 * Modify Date: 2017年2月21日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param codeclass code类别
	 * @param code code值
	 * @return TGlptCode
	 * @since JDK 1.7
	 */
	@Override
	public TGlptCode getCodeByCodeclassAndCode(String codeclass, String code) {

		logger.info("根据codeclass和code获取TYyxtCode");
		return tGlptCodeMapper.getCodeByCodeclassAndCode(codeclass, code);
	}

	/**
	 *
	 * getCodeMapByCodeclass,(根据codeclass获取码表map). <br/>
	 * Author: nifang <br/>
	 * Create Date: 2017年2月23日 <br/>
	 * ===============================================================<br/>
	 * Modifier: nifang <br/>
	 * Modify Date: 2017年2月23日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param codeclass code类别
	 * @return 码表map
	 * @since JDK 1.7
	 */
	@Override
	public Map<String, TGlptCode> getCodeMapByCodeclass(String codeclass) {

		List<TGlptCode> codeList = tGlptCodeMapper.getCodeListByCodeclass(codeclass);
		Map<String, TGlptCode> codeMap = new HashMap<String, TGlptCode>();
		for (TGlptCode code : codeList) {
			String key = codeclass + code.getCode();
			codeMap.put(key, code);
		}
		return codeMap;
	}



}
