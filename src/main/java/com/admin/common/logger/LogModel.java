package com.admin.common.logger;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * ClassName: LogModel <br/>
 * Function: 日志实体. <br/>
 * Date: 2017年3月6日 下午2:17:04 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
@JSONType(orders = { "classPath", "methodName", "methodText", "methodType", "startTime", "endTime", "executeTime", "status", "inputParams", "resultParams", "requestInfo", "errorinfo" })
public class LogModel implements Serializable {

    /** 序列化id **/
    private static final long serialVersionUID = -4669806296244149334L;
    /** 路径 **/
    private String classPath;
    /** 方法名 **/
    private String methodName;
    /** 方法内容 **/
    private String methodText;
    /** 方法类型 **/
    private String methodType;
    /** 开始时间 **/
    private String startTime;
    /** 结束时间 **/
    private String endTime;
    /** 完成时间 **/
    private String executeTime;
    /** 状态 **/
    private String status;
    /** 输入参数 **/
    private Object inputParams;
    /** 输出结果 **/
    private Object resultParams;
    /** 输出信息 **/
    private Object requestInfo;
    /** 错误信息 **/
    private Object errorinfo;

    public String getClassPath() {
        return this.classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodText() {
        return this.methodText;
    }

    public void setMethodText(String methodText) {
        this.methodText = methodText;
    }

    public String getMethodType() {
        return this.methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExecuteTime() {
        return this.executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getInputParams() {
        return this.inputParams;
    }

    public void setInputParams(Object inputParams) {
        this.inputParams = inputParams;
    }

    public Object getResultParams() {
        return this.resultParams;
    }

    public void setResultParams(Object resultParams) {
        this.resultParams = resultParams;
    }

    public Object getRequestInfo() {
        return this.requestInfo;
    }

    public void setRequestInfo(Object requestInfo) {
        this.requestInfo = requestInfo;
    }

    public Object getErrorinfo() {
        return this.errorinfo;
    }

    public void setErrorinfo(Object errorinfo) {
        this.errorinfo = errorinfo;
    }
}
