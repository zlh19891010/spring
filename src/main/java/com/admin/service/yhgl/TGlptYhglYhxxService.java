/**
 * Project Name:vr-admin
 * File Name:TGlptYhglYhxxService.java
 * Package Name:com.sd.vr.admin.service.admin
 * Date:2017年3月1日上午9:50:26
 *
 */

package com.admin.service.yhgl;

import com.admin.entity.yhgl.TGlptYhglYhxx;
import com.admin.param.YhxxParam;
import com.admin.view.ResultJson;
import com.admin.view.ViewUserModel;
import com.admin.view.ViewYhxx;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * ClassName:TGlptYhglYhxxService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月1日 上午9:50:26 <br/>
 * 
 * @author shaopei
 * @version
 * @since JDK 1.7
 * @see
 */
public interface TGlptYhglYhxxService extends IService<TGlptYhglYhxx> {

	/**
	 * selectYhxxList,(分页查询用户信息). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @param yhxxParam 参数
	 * @param userModel 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	Page<ViewYhxx> selectYhxxList(YhxxParam yhxxParam, ViewUserModel userModel);

	/**
	 * selectById,(根据id查询用户信息). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @param id 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	TGlptYhglYhxx findById(String id);

	/**
	 * insertYhxx,(新增用户). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @param tGlptYhglYhxx 参数
	 * @param userModel 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	boolean insertYhxx(TGlptYhglYhxx tGlptYhglYhxx, ViewUserModel userModel);

	/**
	 * updateYhxx,(修改用户). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月1日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月1日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @param tGlptYhglYhxx 参数
	 * @param user 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	boolean updateYhxx(TGlptYhglYhxx tGlptYhglYhxx, String user);

	/**
	 * removeYhxx,(删除用户). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月2日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月2日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @param tGlptYhglYhxx 参数
	 * @param user 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	ResultJson removeYhxx(TGlptYhglYhxx tGlptYhglYhxx, String user);

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
	 * @param loginid 参数
	 * @param jgId 机构Id
	 * @return 结果
	 * @since JDK 1.7
	 */
	TGlptYhglYhxx selectYhByLoginid(String loginid, String jgId);

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
	TGlptYhglYhxx selectYhByLoginidForSuper(String loginid);
}
