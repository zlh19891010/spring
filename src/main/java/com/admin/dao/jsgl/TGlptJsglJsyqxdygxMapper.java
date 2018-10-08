package com.admin.dao.jsgl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.admin.entity.TGlptJsglJsyqxdygx;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * ClassName: TGlptJsglJsyqxdygxMapper <br/>
 * Function: 角色与权限对应关系 Mapper. <br/>
 * Date: 2017年3月1日 下午6:35:00 <br/>
 *
 * @author tangli
 * @version 1.0
 * @since JDK 1.7
 */
public interface TGlptJsglJsyqxdygxMapper extends BaseMapper<TGlptJsglJsyqxdygx> {
    /**
     * 
     * insertPl,(批量插入). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月2日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月2日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param dygList 对应关系list
     * @return 结果
     * @since JDK 1.7
     */
    Integer insertPl(@Param("dygxList") List<TGlptJsglJsyqxdygx> dygList);

    /**
     * 
     * getDygxListByParam,(根据角色id,和角色归属查询结果). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月6日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param dygx 请求参数
     * @return 结果
     * @since JDK 1.7
     */
    List<TGlptJsglJsyqxdygx> getDygxListByParam(TGlptJsglJsyqxdygx dygx);

    /**
     * selectYhCzjb,((查询用户操作级别). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月10日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月10日 <br/>
     * Modify Description:  <br/>
     * ===============================================================<br/>
     * @param param 参数
     * @return 结果
     * @since JDK 1.7
     */
    TGlptJsglJsyqxdygx selectYhCzjb(TGlptJsglJsyqxdygx param);

}