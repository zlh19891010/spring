package com.admin.dao.xtgl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.admin.entity.TGlptXtglMoudle;
import com.admin.view.ViewTGlptXtglMoudle;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * ClassName: TGlptXtglMoudleMapper <br/>
 * Function: 模块菜单 Mapper 接口. <br/>
 * Date: 2017年3月1日 下午4:25:31 <br/>
 *
 * @author tangli
 * @version 1.0
 * @since JDK 1.7
 */
public interface TGlptXtglMoudleMapper extends BaseMapper<TGlptXtglMoudle> {

    /**
     * 
     * findMoudleList,(通过角色ID查询菜单信息). <br/>
     * Author: 刘成 <br/>
     * Create Date: 2017年3月1日 <br/>
     * ===============================================================<br/>
     * Modifier: 刘成 <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param jsgs
     * @param js_id
     * @param system_code
     * @param status
     * @return
     * @since JDK 1.7
     */
    List<ViewTGlptXtglMoudle> findMoudleList(@Param("jsgs") String jsgs, @Param("jsId") String jsId, @Param("systemCode") String systemCode, @Param("status") String status);

    /**
     * 
     * getMoudleList,(获取所有菜单). <br/>
     * Author: 刘成 <br/>
     * Create Date: 2017年3月4日 <br/>
     * ===============================================================<br/>
     * Modifier: 刘成 <br/>
     * Modify Date: 2017年3月4日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param systemCode
     * @param status
     * @return
     * @since JDK 1.7
     */
    List<ViewTGlptXtglMoudle> getMoudleList(@Param("systemCode") String systemCode, @Param("status") String status);

    /**
     * 
     * getParentModule,(获取所有一级菜单). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月4日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月4日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param status 请求参数
     * @param systemCode 请求参数
     * @return 结果
     * @since JDK 1.7
     */
    List<ViewTGlptXtglMoudle> getParentModule(@Param("systemCode") String systemCode, @Param("status") String status);
}
