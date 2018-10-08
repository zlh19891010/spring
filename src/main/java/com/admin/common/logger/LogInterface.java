/**
 * Project Name:admin
 * File Name:LogInterface.java
 * Package Name:com.sd.vr.admin.common.logger
 * Date:2017年3月6日下午1:30:22
 *
 */

package com.admin.common.logger;

/**
 * ClassName:LogInterface <br/>
 * Function: 日志记录接口. <br/>
 * Date: 2017年3月6日 下午1:30:22 <br/>
 * 
 * @author weiming.chen
 * @version
 * @since JDK 1.7
 * @see
 */
public interface LogInterface {

    /**
     * createLogInfo,(创建日志). <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年3月6日 <br/>
     * ===============================================================<br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param text 日志文本内容
     * @return 日志信息
     * @since JDK 1.7
     */
    LogInfo createLogInfo(String text);

    /**
     * formatLogInfo,(格式化日志). <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年3月6日 <br/>
     * ===============================================================<br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param logInfo 日志信息
     * @return 格式化后的日志信息
     * @since JDK 1.7
     */
    String formatLogInfo(LogInfo logInfo);

    /**
     * updateMonitorLogInfo,(更新日志). <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年3月6日 <br/>
     * ===============================================================<br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param monitorLogInfo 监控日志信息
     * @param logAnno 监控日志注解
     * @since JDK 1.7
     */
    void updateMonitorLogInfo(MonitorLogInfo monitorLogInfo, MonitorLog logAnno);

    /**
     * saveMonitorLogInfo,(保存监控日志信息). <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年3月6日 <br/>
     * ===============================================================<br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param monitorLogInfo 监控日志信息
     * @since JDK 1.7
     */
    void saveMonitorLogInfo(MonitorLogInfo monitorLogInfo);
}
