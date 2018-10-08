package com.admin.dao.yhgl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.admin.entity.yhgl.TGlptYhglYhxx;
import com.admin.param.YhxxParam;
import com.admin.view.ViewYhxx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author weiming.chen
 * @since 2017-03-01
 */
public interface TGlptYhglYhxxMapper extends BaseMapper<TGlptYhglYhxx> {

	/**
	 *
	 * selectYhxxList,(分页查询结果). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param page 请求参数
	 * @param yhxxParam 请求参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	List<ViewYhxx> selectYhxxList(Page<ViewYhxx> page, YhxxParam yhxxParam);

	/**
	 *
	 * selectYhxxList,(查询结果). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param yhxxParam 请求参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	List<TGlptYhglYhxx> selectXjyhList(YhxxParam yhxxParam);

	/**
	 *
	 * getYhxx,(通过用户，密码查询用户信息). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月4日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月4日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param userName 参数
	 * @param mm 密码
	 * @return 结果
	 * @since JDK 1.7
	 */
	TGlptYhglYhxx getYhxx(@Param("userName") String userName, @Param("mm") String mm);

	/**
	 * selectYhxxById,(根据id查询). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月14日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月14日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param id 参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	List<ViewYhxx> selectYhxxById(String id);

	/**
	 * selectYhByLoginid,(). <br/>
	 * Author: shaopei<br/>
	 * Create Date: 2017年3月21日 <br/>
	 * ===============================================================<br/>
	 * Modifier: shaopei <br/>
	 * Modify Date: 2017年3月21日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 * 
	 * @param loginid 登录id
	 * @param jgId 机构id
	 * @return 结果
	 * @since JDK 1.7
	 */
	TGlptYhglYhxx selectYhByLoginid(@Param("loginid") String loginid, @Param("jgId") String jgId);

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
	 * @param loginid 登录Id
	 * @return 用户信息
	 * @since JDK 1.7
	 */
	TGlptYhglYhxx selectYhByLoginidForSuper(@Param("loginid") String loginid);

	/**
	 * selectYhxxBySjglyId,(根据上级管理编号获取用户信息). <br/>
	 * Author: yaojie <br/>
	 * Create Date: 2017年5月13日 <br/>
	 * ===============================================================<br/>
	 * Modifier: yaojie <br/>
	 * Modify Date: 2017年5月13日 <br/>
	 * Modify Description:  <br/>
	 * ===============================================================<br/>
	 * @param sjglyid 上级管理员编号
	 * @return 用户信息列表
	 * @since JDK 1.7
	 */
	List<TGlptYhglYhxx> selectYhxxBySjglyId(@Param("sjglyid") String sjglyid);
}