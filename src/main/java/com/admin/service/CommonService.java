/**
 * Project Name:vr-admin-api
 * File Name:CommonService.java
 * Package Name:com.sd.vr.admin.api.service
 * Date:2017年3月4日上午11:26:34
 *
 */

package com.admin.service;

import java.util.List;
import java.util.Map;

import com.admin.entity.TGlptCode;

/**
 * ClassName:CommonService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月4日 上午11:26:34 <br/>
 *
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
public interface CommonService {

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
	List<TGlptCode> getCodeListByCodeclass(String codeclass);

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
	TGlptCode getCodeByCodeclassAndCode(String codeclass, String code);

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
	Map<String, TGlptCode> getCodeMapByCodeclass(String codeclass);

	String getUid(String roleID);
}
