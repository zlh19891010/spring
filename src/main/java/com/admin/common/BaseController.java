package com.admin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerMapping;

import com.admin.view.ViewUserModel;

/**
 * ClassName: BaseController <br/>
 * Function: Controller基类. <br/>
 * Date: 2017年2月15日 下午1:25:21 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
public class BaseController {

    /** 声明日志，所以子类都可以使用 */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * http请求
     */
    @Autowired
    protected HttpServletRequest request;

    /**
     * http响应
     */
    @Autowired
    protected HttpServletResponse response;

    /**
     * redirectTo,(跳转到某个URL). <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年2月15日 <br/>
     * ===============================================================<br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年2月15日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param url 路径
     * @return 字符串
     * @since JDK 1.7
     */
    protected String redirectTo(String url) {
        StringBuffer rto = new StringBuffer("redirect:");
        rto.append(url);
        return rto.toString();
    }

    /**
     *
     * getUserModel,(获取用户信息). <br/>
     * Author: 刘成 <br/>
     * Create Date: 2017年3月2日 <br/>
     * ===============================================================<br/>
     * Modifier: 刘成 <br/>
     * Modify Date: 2017年3月2日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @return ViewUserModel
     * @since JDK 1.7
     */
    protected ViewUserModel getUserModel() {
        //先判断用户是否登录，再决定是否取用户信息
        Object obj = request.getSession().getAttribute(CodeConstants.USER_MOUDLE);
        if (obj != null) {
            ViewUserModel userModel = (ViewUserModel) request.getSession().getAttribute(CodeConstants.USER_MOUDLE);
            return userModel;
        } else {
            //清除session
            request.getSession().invalidate();
            return new ViewUserModel();
        }

    }

    /**
     *
     * extractPathFromPattern,(把指定URL后的字符串全部截断当成参数,这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题). <br/>
     * Author: ZhouLanHui <br/>
     * Create Date: 2017年3月20日 <br/>
     * ===============================================================<br/>
     * Modifier: ZhouLanHui <br/>
     * Modify Date: 2017年3月20日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param request
     * @return
     * @since JDK 1.7
     */
    protected String extractPathFromPattern(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }
}
