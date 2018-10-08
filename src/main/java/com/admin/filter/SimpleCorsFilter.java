package com.admin.filter;

/**
 * 文件名称： com.mxt.framework.demo.filter.SimpleCORSFilter.java</br>
 * 初始作者： weiming.chen</br>
 * 创建日期： 2017年2月9日</br>
 * 功能说明： 跨域过滤器 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (c) 2016-2017 .All rights reserved.<br/>
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: SimpleCorsFilter <br/>
 * Function: 跨域过滤器. <br/>
 * Date: 2017年2月9日 下午4:32:45 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
public class SimpleCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

}
