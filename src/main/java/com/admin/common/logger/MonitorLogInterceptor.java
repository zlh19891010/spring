package com.admin.common.logger;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.admin.common.CodeConstants;

/**
 * ClassName: MonitorLogInterceptor <br/>
 * Function: 监控日志拦截器. <br/>
 * Date: 2017年3月6日 下午2:21:01 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
public class MonitorLogInterceptor implements MethodInterceptor {

	/** 日志操作接口 **/
	private LogInterface logInterface;

	/**
	 * invoke,(触发日志调用). <br/>
	 * Author: weiming.chen <br/>
	 * Create Date: 2017年3月6日 <br/>
	 * =============================================================== <br/>
	 * Modifier: weiming.chen <br/>
	 * Modify Date: 2017年3月6日 <br/>
	 * Modify Description: <br/>
	 * =============================================================== <br/>
	 *
	 * @param invocation 方法
	 * @return 调用结果
	 * @throws Throwable 异常
	 * @since JDK 1.7
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		//获取用户信息
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Object userModel = request.getSession().getAttribute(CodeConstants.USER_MOUDLE);
		Method method = invocation.getMethod();
		Object type=request.getSession().getAttribute(CodeConstants.TYPE);

		MonitorLog logAnno = method.getAnnotation(MonitorLog.class);

		if (logAnno != null) {
			MonitorLogInfo monitorLogInfo = new MonitorLogInfo();

			Object returnObj = null;
			Long startTime = Long.valueOf(0L);
			Long endTime = Long.valueOf(0L);

			try {
				monitorLogInfo.setClasspath(invocation.getThis().getClass().getName());
				monitorLogInfo.setMethodName(method.getName());
				monitorLogInfo.setMethodParams(invocation.getArguments());
				//注解的文本
				monitorLogInfo.setText(type==null?logAnno.text():type.toString());
				//注解的类型
				monitorLogInfo.setMethodType(logAnno.type());
				monitorLogInfo.setUserModel(userModel);
				startTime = Long.valueOf(System.currentTimeMillis());
				returnObj = invocation.proceed();
				endTime = Long.valueOf(System.currentTimeMillis());

				//设置日志状态
				monitorLogInfo.setStatus(true);

				if ("void".equals(method.getReturnType().getName())) {
					monitorLogInfo.setReturnValue("void");
				} else {
					monitorLogInfo.setReturnValue(returnObj);
				}
			} catch (Exception e) {
				endTime = Long.valueOf(System.currentTimeMillis());
				monitorLogInfo.setThrowable(e);
				monitorLogInfo.setStatus(false);
				throw e;
			} finally {
				monitorLogInfo.setStartTime(new Date(startTime.longValue()));
				monitorLogInfo.setEndTime(new Date(endTime.longValue()));
				monitorLogInfo.setExecuteTime(Long.valueOf(endTime.longValue() - startTime.longValue()));

				//更新日志信息
				logInterface.updateMonitorLogInfo(monitorLogInfo, logAnno);
			}

			return returnObj;
		}

		return invocation.proceed();
	}

	/**
	 * setLogInterface,(设置日志操作类). <br/>
	 * Author: weiming.chen <br/>
	 * Create Date: 2017年3月6日 <br/>
	 * ===============================================================<br/>
	 * Modifier: weiming.chen <br/>
	 * Modify Date: 2017年3月6日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param logInterface 日志操作类
	 * @since JDK 1.7
	 */
	public void setLogInterface(LogInterface logInterface) {
		this.logInterface = logInterface;
	}
}
