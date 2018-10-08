package com.admin.common.logger;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.admin.common.GlobalConstants;
import com.admin.content.CommonConstants;
import com.admin.content.SpringContext;
import com.admin.entity.TGlptXtglLog;
import com.admin.service.log.TGlptXtglLogService;
import com.admin.utils.DateUtil;
import com.admin.utils.JsonUtil;
import com.admin.view.ViewUserModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * ClassName: LogInterfaceImp <br/>
 * Function: 日志拦截实现类. <br/>
 * Date: 2017年3月6日 下午2:09:21 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
public class LogInterfaceImpl implements LogInterface {

    /** 日志 **/
    private Logger logger = LoggerFactory.getLogger(getClass());

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
    public LogInfo createLogInfo(String text) {
        LogInfo logInfo = new LogInfo(text);
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            logInfo.setClientIp(request.getRemoteAddr());
            logInfo.setServerIp(request.getLocalAddr());
            logInfo.setServerPort(Integer.valueOf(request.getLocalPort()));
            logInfo.setUserAgent(request.getHeader("User-Agent"));
            logInfo.setMethod(request.getMethod());
        } catch (Exception localException) {
        }
        return logInfo;
    }

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
    public String formatLogInfo(LogInfo logInfo) {
        return JSON.toJSONString(logInfo);
    }

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
    public void saveMonitorLogInfo(MonitorLogInfo monitorLogInfo) {
    }

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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void updateMonitorLogInfo(MonitorLogInfo monitorLogInfo, MonitorLog logAnno) {
        LogModel lm = new LogModel();
        lm.setClassPath(monitorLogInfo.getClasspath());
        lm.setMethodName(monitorLogInfo.getMethodName());
        lm.setMethodText(monitorLogInfo.getText());
        lm.setMethodType(monitorLogInfo.getMethodType());
        lm.setStartTime(DateUtil.dateToString(monitorLogInfo.getStartTime(), "yyyy-MM-dd HH:mm:ss SSS"));
        lm.setEndTime(DateUtil.dateToString(monitorLogInfo.getEndTime(), "yyyy-MM-dd HH:mm:ss SSS"));
        lm.setExecuteTime(String.valueOf(monitorLogInfo.getExecuteTime()));
        lm.setStatus(String.valueOf(monitorLogInfo.isStatus()));
        //方法参数以Map的方式展示
        if (monitorLogInfo.getMethodParams() != null) {
            Map inputParams = new HashMap();
            for (int i = 0; i < monitorLogInfo.getMethodParams().length; i++) {
                Object obj = monitorLogInfo.getMethodParams()[i];

                if ((!(obj instanceof BeanPropertyBindingResult)) && (!(obj instanceof HttpServletRequest)) && (!(obj instanceof HttpServletResponse))) {
                    inputParams.put("param" + (i + 1), obj);
                }
            }
            lm.setInputParams(inputParams);
        }

        //方法返回结果不作日志输出
        //lm.setResultParams(monitorLogInfo.getReturnValue());

        //controller的方法
        if ((logAnno.containRequest()) && (MonitorLogInfo.METHOD_TYPE_ACTION.equals(lm.getMethodType()))) {
            logger.info("记录Controller日志");
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            Map requestInfo = new HashMap();
            requestInfo.put("url", request.getRequestURI() + (request.getQueryString() == null ? "" : new StringBuilder("?").append(request.getQueryString()).toString()));
            requestInfo.put("clientIp", request.getRemoteAddr());
            requestInfo.put("serverIp", request.getLocalAddr());
            requestInfo.put("serverPort", Integer.valueOf(request.getLocalPort()));
            requestInfo.put("userAgent", request.getHeader("User-Agent"));
            lm.setRequestInfo(requestInfo);
        }

        /** controller操作操作日志记录，并保存数据库 */
        if ((logAnno.containRequest())) {
            //用户名
            String zh = "";
            if (monitorLogInfo.getUserModel() != null) {
                ViewUserModel userModel = (ViewUserModel) monitorLogInfo.getUserModel();
                zh = userModel.getZh();
                String ywbh = GlobalConstants.ADMIN_LOG;
                //业务名称
                String ywmc = logAnno.module();
                //操作类型
                String ywb = CommonConstants.getCzlx(lm.getMethodName());
                //日志描述
                String rzms = lm.getMethodText();
                //操作人名称
                String czrmc = zh;

                logger.info("开始记录管理平台日志数据到数据库");

                TGlptXtglLogService tGlptXtglLogService = SpringContext.getBean(TGlptXtglLogService.class);

                tGlptXtglLogService.addLog(new TGlptXtglLog(zh, ywmc, ywbh, ywb, rzms, czrmc));

                logger.info("记录管理平台日志数据到数据库完成");
            }
        }

        lm.setErrorinfo(String.valueOf(monitorLogInfo.getThrowable()));

        this.logger.debug("Methods to monitor the log：" + JsonUtil.formatJson(JSON.toJSONString(lm, new SerializerFeature[] { SerializerFeature.WriteMapNullValue })));
    }
}