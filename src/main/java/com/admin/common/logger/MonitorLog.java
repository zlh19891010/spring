package com.admin.common.logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: MonitorLog <br/>
 * Function: 日志记录. <br/>
 * Date: 2017年3月6日 下午1:39:31 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface MonitorLog {

    /** 日志内容 */
    String text();

    /** 日志类型 */
    String type() default "action";

    /** 日志模块 */
    String module();
    
    /** 是否是controller层 */
    boolean containRequest() default false;

    /** 请求参数 */
    String[] requestParams() default {};

    /** 服务层Bean */
    String serviceBean() default "";
    
    
}
