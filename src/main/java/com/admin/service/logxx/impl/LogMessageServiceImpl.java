/**
 * Project Name:vr-admin
 * File Name:LogMessageServiceImpl.java
 * Package Name:com.sd.vr.admin.service.admin.rzxx
 * Date:2017年3月15日下午1:39:00
 *
 */

package com.admin.service.logxx.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.admin.dao.TGlptXtglLogMapper;
import com.admin.entity.TGlptXtglLog;
import com.admin.param.ParamLogxx;
import com.admin.service.logxx.LogMessageService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * ClassName:LogMessageServiceImpl <br/>
 * Function: 分页查询日志信息. <br/>
 * Reason:	     查询日志. <br/>
 * Date:     2017年3月15日 下午1:39:00 <br/>
 * @author   shaopei
 * @version
 * @since    JDK 1.7
 * @see
 */
public class LogMessageServiceImpl extends ServiceImpl<TGlptXtglLogMapper, TGlptXtglLog> implements LogMessageService {

    /**
     * 日志mapper
     */
    @Autowired
    private TGlptXtglLogMapper tGlptXtglLogMapper;
    /**
     * selectByLoglist,(分页查询日志信息). <br/>
     * Author: shaopei <br/>
     * Create Date: 2017年3月15日 <br/>
     * =============================================================== <br/>
     * Modifier:shaopei<br/>
     * Modify Date: 2017年3月15日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     * @param param
     * @return
     * @since JDK 1.7
     */
    @Override
    public Page<TGlptXtglLog> selectByLoglist(ParamLogxx param) {

        logger.info("===开始查询事例===");
        Page<TGlptXtglLog> page = new Page<TGlptXtglLog>(param.getCurrent(), param.getSize());
        List<TGlptXtglLog> result =  tGlptXtglLogMapper.selectByLoglist(page, param);
        page.setRecords(result);
        logger.info("===完成查询事例===");
        return page;
    }

}

