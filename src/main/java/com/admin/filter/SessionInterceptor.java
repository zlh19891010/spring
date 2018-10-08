package com.admin.filter;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.admin.common.CodeConstants;
import com.admin.view.ViewUserModel;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.common.util.HttpUtil;
import com.baomidou.kisso.web.handler.KissoDefaultHandler;
import com.baomidou.kisso.web.handler.SSOHandlerInterceptor;

/**
 * ClassName:SessionInterceptor <br/>
 * Function: 登录校验. <br/>
 * Date: 2017年3月8日 上午9:01:42 <br/>
 * 
 * @author weiming.chen
 * @version
 * @since JDK 1.7
 * @see
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger("SSOInterceptor");
    private SSOHandlerInterceptor handlerInterceptor;

    /**
     * preHandle,登录权限验证. <br/>
     * Author: weiming.chen <br/>
     * Create Date: 2017年3月8日 <br/>
     * =============================================================== <br/>
     * Modifier: weiming.chen <br/>
     * Modify Date: 2017年3月8日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     * 
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 处理 Controller 方法
         * <p>
         * 判断 handler 是否为 HandlerMethod 实例
         * </p>
         */
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Login login = method.getAnnotation(Login.class);
            if (login != null) {
                if (login.action() == Action.Skip) {
                    /**
                     * 忽略拦截
                     */
                    return true;
                }
            }

            logger.info("判断是否登录，从session中获取");
            Object obj = request.getSession().getAttribute(CodeConstants.USER_MOUDLE);
            if (obj == null) {
                logger.info("用户未登录，返回401");
                if (HttpUtil.isAjax(request)) {
                    /*
                     * Handler 处理 AJAX 请求
                     */
                    this.getHandlerInterceptor().preTokenIsNullAjax(request, response);
                    return false;
                } else {
                    /*
                     * token 为空，调用 Handler 处理
                     * 返回 true 继续执行，清理登录状态并重定向至登录界面
                     */
                    if (this.getHandlerInterceptor().preTokenIsNull(request, response)) {
                        logger.fine("logout. request url:" + request.getRequestURL());
                        SSOHelper.clearRedirectLogin(request, response);
                    }
                    return false;
                }
            }
            logger.info("用户已登录，登录用户账号为：" + ((ViewUserModel) obj).getZh());
        }
        return true;
    }

    public SSOHandlerInterceptor getHandlerInterceptor() {
        if (handlerInterceptor == null) {
            return KissoDefaultHandler.getInstance();
        }
        return handlerInterceptor;
    }

    public void setHandlerInterceptor(SSOHandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }
}
