package com.admin.dao.jsgl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.admin.entity.jsgl.TGlptJsglJsxx;
import com.admin.view.ViewTGlptJsglJsxx;
import com.admin.view.ViewXzjs;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ClassName: TGlptJsglJsxxMapper <br/>
 * Function: 角色信息 Mapper 接口. <br/>
 * Date: 2017年3月1日 上午10:14:40 <br/>
 *
 * @author tangli
 * @version 1.0
 * @since JDK 1.7
 */
public interface TGlptJsglJsxxMapper extends BaseMapper<TGlptJsglJsxx> {
    /**
     *
     * getJsxxList,(获取角色列表). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月3日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月3日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param page 请求参数
     * @param param 请求参数
     * @return 结果
     * @since JDK 1.7
     */
    List<TGlptJsglJsxx> getJsxxList(Page<ViewTGlptJsglJsxx> page, TGlptJsglJsxx param);

    /**
     *
     * getJsxx,(获取角色信息). <br/>
     * Author: 刘成 <br/>
     * Create Date: 2017年3月1日 <br/>
     * ===============================================================<br/>
     * Modifier: 刘成 <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param param 参数
     * @return 结果
     * @since JDK 1.7
     */
    TGlptJsglJsxx getJsxx(TGlptJsglJsxx param);

    /**
     * selectJsxx,(获取所有角色信息). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月6日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param tGlptJsglJsxx 参数
     * @return 结果
     * @since JDK 1.7
     */
    List<TGlptJsglJsxx> selectGlyJsxx(TGlptJsglJsxx tGlptJsglJsxx);

    /**
     * selectYyzxJsxx,(运营中心). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月10日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月10日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param tGlptJsglJsxx 参数
     * @return 结果
     * @since JDK 1.7
     */
    List<TGlptJsglJsxx> selectJsxx(TGlptJsglJsxx tGlptJsglJsxx);

    /**
     * selectYyzxJsxx,(运营中心). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月10日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月10日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param tGlptJsglJsxx 参数
     * @return 结果
     * @since JDK 1.7
     */
    List<TGlptJsglJsxx> selectYyzxJsxx(TGlptJsglJsxx tGlptJsglJsxx);

    /**
     * 
     * getJsxxByJsmc,(查询角色名称是否存在). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月9日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月9日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param tGlptJsglJsxx 角色归属&角色名称
     * @return 结果
     * @since JDK 1.7
     */
    TGlptJsglJsxx getJsxxByJsmc(TGlptJsglJsxx tGlptJsglJsxx);

    /**
     * 
     * getJsxxById,(根据id获取角色信息). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月9日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月9日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param id 角色id
     * @return 结果
     * @since JDK 1.7
     */
    ViewXzjs getJsxxById(@Param("id") String id);

    /**
     * 
     * getAllNextJsxx,(获取所有下级角色). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月10日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月10日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param tGlptJsglJsxx 角色id&角色归属
     * @return 结果
     * @since JDK 1.7
     */
    List<TGlptJsglJsxx> getAllNextJsxx(TGlptJsglJsxx tGlptJsglJsxx);

    /**
     * 
     * getKfpJsByJsgs,(根据角色归属查询最小的级别记录). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月10日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月10日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param jsgs 角色归属
     * @return 结果
     * @since JDK 1.7
     */
    TGlptJsglJsxx getKfpJsByJsgs(@Param("jsgs") String jsgs);

    /**
     * 
     * getAllxjjsList,(获取所有的下级角色). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年4月14日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年4月14日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param jsgs 角色归属
     * @param jsid 角色id
     * @return
     * @since JDK 1.7
     */
    List<TGlptJsglJsxx> getAllxjjsList(@Param("jsgs") String jsgs, @Param("jsid") String jsid);

}