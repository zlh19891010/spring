/**
 * Project Name:vr-admin-api
 * File Name:LogMessageService.java
 * Package Name:com.sd.vr.admin.api.service.admin.logxx
 * Date:2017年3月15日下午1:33:17
 *
 */

package com.admin.service.logxx;

import com.admin.entity.TGlptXtglLog;
import com.admin.param.ParamLogxx;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * ClassName:LogMessageService <br/>
 * Function: 日志查看. <br/>
 * Reason:	 ADD REASON. <br/>
 * Date:     2017年3月15日 下午1:33:17 <br/>
 * @author   shaopei
 * @version
 * @since    JDK 1.7
 * @see
 */
public interface LogMessageService {


    /**
     * selectByLoglist,(日志信息分页查询). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月15日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月15日 <br/>
     * Modify Description:  <br/>
     * ===============================================================<br/>
     * @param param 参数
     * @return 结果
     * @since JDK 1.7
     */
    Page<TGlptXtglLog> selectByLoglist(ParamLogxx param);
}

