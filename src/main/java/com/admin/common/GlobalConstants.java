package com.admin.common;

/**
 *
 * ClassName: GlobalConstants <br/>
 * Function: 公共常量. <br/>
 * Date: 2016年7月7日 下午5:06:49 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
public final class GlobalConstants {

    private GlobalConstants() {
    }

    /**
     * 加载配置文件列表
     */
    public static final String CFG_XML_FILE = "conf/config-list.xml";

    /** 文件下载相关配置 */
    public static final String CFG_DOWN_FILE = "conf/dl.properties";

    /** 应用相关配置 */
    public static final String CFG_APP_FILE = "conf/app.properties";

    /** 平台日志 01 */
    public static final String ADMIN_LOG = "01";

    /** 运营中心日志 */
    public static final String OPERATION_LOG = "02";

    /** 控制中心日志 03 */
    public static final String CONTROL_LOG = "03";

    /** 超级管理员角色ID */
    public static final String ADMIN_JS_ID = "1";
}
