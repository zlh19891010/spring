package com.admin.content;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * ClassName: SpringContext <br/>
 * Function: Spring上下文. <br/>
 * Date: 2017年2月9日 下午4:21:55 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
public class SpringContext implements ServletContextListener {

    /** 日志记录 */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** springContext **/
    private static WebApplicationContext springContext;

    /**
     * contextDestroyed,(Spring容器销毁). <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年2月9日 <br/>
     * ===============================================================<br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年2月9日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param event
     * @since JDK 1.7
     */
    public void contextDestroyed(ServletContextEvent event) {

    }

    /**
     * contextInitialized,(spring容器初始化). <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年2月9日 <br/>
     * ===============================================================<br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年2月9日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param event
     * @since JDK 1.7
     */
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("aaa");
        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        logger.info("=================================");
        logger.info("系统[{}]启动完成!!!", event.getServletContext().getServletContextName());
        logger.info("=================================");
    }

    /** 获取springContext @return WebApplicationContext **/
    public static WebApplicationContext getSpringContext() {
        return springContext;
    }

    /**
     * getBean,(根据名称获取实体). <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年2月9日 <br/>
     * ===============================================================<br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年2月9日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param name 实体名称
     * @return 实体对象
     * @since JDK 1.7
     */
    public static Object getBean(String name) {
        return springContext.getBean(name);
    }

    /**
     * getBean,(根据类型获取实体). <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年2月9日 <br/>
     * ===============================================================<br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年2月9日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param <T> 传入的类型
     * @param clazz 类型
     * @return 实体
     * @since JDK 1.7
     */
    public static <T> T getBean(Class<T> clazz) {
        return springContext.getBean(clazz);
    }

}
