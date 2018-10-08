package com.admin.common.logger;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: LogInfo <br/>
 * Function: 日志信息. <br/>
 * Date: 2017年3月6日 下午1:46:02 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
public class LogInfo {

    /** 用户id */
    private String loginid;

    /** 用户浏览器 */
    private String userAgent;

    /** 客户端IP */
    private String clientIp;

    /** 请求方法 */
    private String method;

    /** 服务端IP */
    private String serverIp;

    /** 服务端端口 */
    private Integer serverPort;

    /** 请求内容 */
    private String text;

    /** 预留扩展信息 */
    private Map<String, Object> ext;

    public LogInfo() {
    }

    public LogInfo(String text) {
        this.text = text;
    }

    /**
     * setExt,(设置扩展信息). <br/>
     * 
     * @param propertyName 属性名称
     * @param value 属性值
     * @since JDK 1.7
     */
    public void setExtAttr(String propertyName, Object value) {
        if (this.ext == null) {
            this.ext = new HashMap<String, Object>();
        }
        this.ext.put(propertyName, value);
    }

    /**
     * getExt,(获取扩展信息). <br/>
     * 
     * @param propertyName 属性名称
     * @return 属性值
     * @since JDK 1.7
     */
    public Object getExtAttr(String propertyName) {
        if (this.ext != null) {
            return this.ext.get(propertyName);
        }
        return null;
    }

    public String getLoginid() {
        return this.loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public Integer getServerPort() {
        return this.serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getExt() {
        return this.ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }
}
