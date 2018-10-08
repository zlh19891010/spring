package com.admin.content;

/**
 * ClassName: AppConfig <br/>
 * Function: 读取配置文件. <br/>
 * Date: 2017年2月21日 下午4:43:49 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
public class AppConfig {

    /** 分布式文件系统主机地址 */
    private String dfsHost;

    /** 分布式文件系统文件上传地址 */
    private String dfsUploadUrl;

    /** 获取分布式系统主机地址 @return dfs主机地址 */
    public String getDfsHost() {
        return dfsHost;
    }

    /** 设置分布式系统主机地址 @param dfsHost dfs主机地址 */
    public void setDfsHost(String dfsHost) {
        this.dfsHost = dfsHost;
    }

    /** 获取分布式系统上传地址 @return dfs上传地址 */
    public String getDfsUploadUrl() {
        return dfsUploadUrl;
    }

    /** 获取分布式系统上传地址 @param dfsUploadUrl dfs上传地址 */
    public void setDfsUploadUrl(String dfsUploadUrl) {
        this.dfsUploadUrl = dfsUploadUrl;
    }
}
