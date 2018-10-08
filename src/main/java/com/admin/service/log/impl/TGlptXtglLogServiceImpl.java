package com.admin.service.log.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.admin.common.CodeConstants;
import com.admin.dao.TGlptXtglLogMapper;
import com.admin.entity.TGlptXtglLog;
import com.admin.service.log.TGlptXtglLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * 
 * ClassName: TGlptXtglLogServiceImpl <br/> 
 * Function: 日志信息 服务实现类. <br/>
 * Date: 2017年3月9日 下午5:22:45 <br/>
 *
 * @author zlh
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class TGlptXtglLogServiceImpl extends ServiceImpl<TGlptXtglLogMapper, TGlptXtglLog> implements TGlptXtglLogService {

    /**
     * 
     * addLog,(添加日志). <br/>
     * Author: zlh <br/>
     * Create Date: 2017年3月9日 <br/>
     * =============================================================== <br/>
     * Modifier: zlh <br/>
     * Modify Date: 2017年3月9日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     * @param tGlptXtglLog
     * @return
     * @since JDK 1.7
     */
    @Override
    public Boolean addLog(TGlptXtglLog tGlptXtglLog) {
        tGlptXtglLog.setId(getKey());
        tGlptXtglLog.setYwmc(tGlptXtglLog.getYwmc());
        tGlptXtglLog.setYwbh(tGlptXtglLog.getYwbh());
        tGlptXtglLog.setYwb(tGlptXtglLog.getYwb());
        tGlptXtglLog.setRzms(tGlptXtglLog.getRzms());
        tGlptXtglLog.setCzrmc(tGlptXtglLog.getCzrmc());
        tGlptXtglLog.setDelflag(CodeConstants.DELFLAG_A);
        tGlptXtglLog.setCreaterCode(tGlptXtglLog.getCreaterCode());
        tGlptXtglLog.setCreateTime(new Date());
        tGlptXtglLog.setOperaterCode(tGlptXtglLog.getOperaterCode());
        tGlptXtglLog.setOperateTime(new Date());
       
        return  insert(tGlptXtglLog);
    }
	
}
