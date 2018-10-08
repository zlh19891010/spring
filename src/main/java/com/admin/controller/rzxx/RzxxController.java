/**
 * Project Name:vr-ctrl
 * File Name:RzxxController.java
 * Package Name:com.sd.vr.ctrl.controller.admin.rzxx
 * Date:2017年3月15日下午1:46:06
 *
 */

package com.admin.controller.rzxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.common.logger.MonitorLog;
import com.admin.common.logger.MonitorLogInfo;
import com.admin.entity.TGlptXtglLog;
import com.admin.param.ParamLogxx;
import com.admin.service.logxx.LogMessageService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * ClassName:RzxxController <br/>
 * Function: 分页查询. <br/>
 * Reason: ADD REASON. <br/>
 * Date: 2017年3月15日 下午1:46:06 <br/>
 *
 * @author shaopei
 * @version
 * @since JDK 1.7
 * @see
 */
@RestController
public class RzxxController extends BaseController {

    /**
     * 日志信息service
     */
    @Autowired
    private LogMessageService logMessageService;

    /**
     * getYhxxPagination,(日志信息列表分页). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月15日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月15日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param param 参数
     * @return 结果
     * @since JDK 1.7
     */
    @RequestMapping("getRzxxlist")
    public Page<TGlptXtglLog> getRzxxPagination(@RequestBody ParamLogxx param) {
        return logMessageService.selectByLoglist(param);
    }
}
