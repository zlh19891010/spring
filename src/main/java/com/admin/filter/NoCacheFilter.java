package com.admin.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: NoCacheFilter <br/>
 * Function: 禁止浏览器缓存所有动态页面. <br/>
 * Date: 2017年2月9日 下午4:34:07 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
public class NoCacheFilter implements Filter {

    /** 开始标识 */
    private Boolean startFlag = true;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String startFlag = filterConfig.getInitParameter("startFlag");
        this.startFlag = Boolean.parseBoolean(startFlag);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        // 把ServletRequest强转成HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) req;
        // 把ServletResponse强转成HttpServletResponse
        HttpServletResponse response = (HttpServletResponse) resp;
        if (startFlag) {
            // 禁止浏览器缓存所有动态页面
            response.setDateHeader("Expires", -1);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

}
