package com.ctn.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hp on 2015/4/27.
 */
public class MobileSecurityFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if ("/mobile/me.html".equals(req.getServletPath()) && session.getAttribute("username") != null && session.getAttribute("barry") != null){
            chain.doFilter(request, response);
        }else if (session.getAttribute("username") != null) {//登录后才能访问
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("../login/login.html");
        }
    }

    @Override
    public void destroy() {

    }
}
