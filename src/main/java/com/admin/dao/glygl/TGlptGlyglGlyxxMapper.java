package com.admin.dao.glygl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.admin.entity.glygl.TGlptGlyglGlyxx;
import com.admin.param.ParamStatistics;
import com.admin.param.YhxxParam;
import com.admin.view.ViewStatistics;
import com.admin.view.ViewYhxx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 管理员信息 Mapper 接口
 * </p>
 *
 * @author LIUCHENG
 * @since 2017-03-01
 */
public interface TGlptGlyglGlyxxMapper extends BaseMapper<TGlptGlyglGlyxx> {

	TGlptGlyglGlyxx getGlyxx(@Param("userName") String userName, @Param("mm") String mm);

	/**
	 *
	 * selectGlyxxList,(查询管理员信息列表). <br/>
	 * Author: wangzheng <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: wangzheng <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param page 请求参数
	 * @param yhxxParam 请求参数
	 * @return 结果
	 * @since JDK 1.7
	 */
	List<ViewYhxx> selectGlyxxList(Page<ViewYhxx> page, YhxxParam yhxxParam);

	/**
	 *
	 * getYhxxByJsid,(根据角色获取用户账号信息). <br/>
	 * Author: tangli <br/>
	 * Create Date: 2017年3月10日 <br/>
	 * ===============================================================<br/>
	 * Modifier: tangli <br/>
	 * Modify Date: 2017年3月10日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param jsId
	 * @return
	 * @since JDK 1.7
	 */
	List<String> getYhxxByJsid(@Param("jsId") String jsId);

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
	TGlptGlyglGlyxx selectGlyxxByLoginId(@Param("LoginId") String loginId);

	/**
	 *
	 *
	 * 方法描述: [分配点卡.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月12日-下午7:07:15<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param cards
	 * @param id
	 * @return
	 * int
	 *
	 */
	int updateCards(@Param("cards")String cards,@Param("id")String id);


	List<TGlptGlyglGlyxx> selectZhList(Page<ViewStatistics> page,ParamStatistics param);
}