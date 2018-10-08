package com.admin.dao;

import java.util.List;

import com.admin.entity.TGlptXtglLog;
import com.admin.param.ParamLogxx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ClassName: TGlptXtglLogMapper <br/>
 * Function: 日志信息 Mapper 接口. <br/>
 * Date: 2017年3月9日 下午5:21:27 <br/>
 *
 * @author 刘成
 * @version 1.0
 * @since JDK 1.7
 */
public interface TGlptXtglLogMapper extends BaseMapper<TGlptXtglLog> {


    /**
     * selectByLoglist,(分页查询所有日志集合). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月15日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月15日 <br/>
     * Modify Description:  <br/>
     * ===============================================================<br/>
     * @param page 分页参数
     * @param param 查询条件
     * @return 结果
     * @since JDK 1.7
     */
    List<TGlptXtglLog> selectByLoglist(Page<TGlptXtglLog> page, ParamLogxx param);

}