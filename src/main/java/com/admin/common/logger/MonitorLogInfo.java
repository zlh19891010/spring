package com.admin.common.logger;

import java.util.Date;

/**
 * ClassName:MonitorLogInfo <br/>
 * Function: 监控的日志信息. <br/>
 * Date: 2017年3月6日 下午1:31:09 <br/>
 * 
 * @author weiming.chen
 * @version
 * @since JDK 1.7
 * @see
 */
public class MonitorLogInfo extends LogInfo {

    /** controller层日志 */
    public static final String METHOD_TYPE_ACTION = "action";

    /** 服务层日志 */
    public static final String METHOD_TYPE_SERVICE = "service";

    /** dao层日志 */
    public static final String METHOD_TYPE_DAO = "dao";

    /** 其他日志 */
    public static final String METHOD_TYPE_OTHER = "other";

    /** 请求路径 */
    private String classpath;

    /** 方法名称 */
    private String methodName;

    /** 开始时间 */
    private Date startTime;

    /** 完成时间 */
    private Date endTime;

    /** 执行时间 */
    private Long executeTime;

    /** 状态 */
    private boolean status;

    /** 返回值 */
    private Object returnValue;

    /** 异常信息 */
    private Throwable throwable;

    /** 方法参数 */
    private Object[] methodParams;

    /** 请求参数 */
    private String requestParams;

    /** 方法类型 */
    private String methodType;
    
    /** 用户模型 */
    private Object userModel;

    public String getClasspath() {
        return this.classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getExecuteTime() {
        return this.executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public Object getReturnValue() {
        return this.returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public Object[] getMethodParams() {
        return this.methodParams;
    }

    public void setMethodParams(Object[] methodParams) {
        this.methodParams = methodParams;
    }

    public String getRequestParams() {
        return this.requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getMethodType() {
        return this.methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

	public Object getUserModel() {
		return userModel;
	}

	public void setUserModel(Object userModel) {
		this.userModel = userModel;
	}
    
    
}
