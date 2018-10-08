package com.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.admin.entity.TGlptCode;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * ClassName: TGlptCodeMapper <br/>
 * Function: 码表Mapper. <br/>
 * Date: 2017年3月4日 上午11:23:24 <br/>
 *
 * @author tangli
 * @version 1.0
 * @since JDK 1.7
 */
public interface TGlptCodeMapper extends BaseMapper<TGlptCode> {

    /**
     * 
     * getCodeListByCodeclass,(根据codeclass获取TYyxtCode列表). <br/>
     * Author: nifang <br/>
     * Create Date: 2017年2月20日 <br/>
     * ===============================================================<br/>
     * Modifier: nifang <br/>
     * Modify Date: 2017年2月20日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param codeclass code类别
     * @return TGlptCode列表
     * @since JDK 1.7
     */
    List<TGlptCode> getCodeListByCodeclass(@Param("codeclass") String codeclass);

    /**
     * 
     * getCodeByCodeclassAndCode,(根据codeclass和code获取TYyxtCode). <br/>
     * Author: nifang <br/>
     * Create Date: 2017年2月20日 <br/>
     * ===============================================================<br/>
     * Modifier: nifang <br/>
     * Modify Date: 2017年2月20日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param codeclass code类别
     * @param code code值
     * @return TGlptCode
     * @since JDK 1.7
     */
    TGlptCode getCodeByCodeclassAndCode(@Param("codeclass") String codeclass, @Param("code") String code);
}