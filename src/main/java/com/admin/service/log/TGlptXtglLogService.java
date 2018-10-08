package com.admin.service.log;

import com.admin.entity.TGlptXtglLog;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * ClassName: TGlptXtglLogService <br/> 
 * Function: 日志信息 服务类. <br/>
 * Date: 2017年3月9日 下午5:20:45 <br/>
 *
 * @author 刘成
 * @version 1.0
 * @since JDK 1.7
 */
public interface TGlptXtglLogService extends IService<TGlptXtglLog> {
	
    /**
     * 
     * addLog,(添加日志). <br/>
     * Author: 刘成 <br/>
     * Create Date: 2017年3月9日 <br/>
     * ===============================================================<br/>
     * Modifier: 刘成 <br/>
     * Modify Date: 2017年3月9日 <br/>
     * Modify Description:  <br/>
     * ===============================================================<br/>
     * @param tGlptXtglLog
     * @return
     * @since JDK 1.7
     */
    Boolean addLog(TGlptXtglLog tGlptXtglLog);
}
