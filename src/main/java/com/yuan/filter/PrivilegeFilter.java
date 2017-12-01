package com.yuan.filter;

import com.yuan.entity.UserInfo;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class PrivilegeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setContentType("textml;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed", "1");
        System.out.println("过滤器:" + request.getRequestURI());
        if (privilegeControl(request, response)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            System.out.println("过滤器权限不足");
        }
    }

    @Override
    public void destroy() {

    }


    private boolean privilegeControl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInfo loginUser = (UserInfo) request.getSession().getAttribute("user");
        String requestUrl = request.getRequestURI();
        if (requestUrl.indexOf(".")>0 || requestUrl.equals("/"))
            return true;
        if (loginUser == null && !requestUrl.equals("/user/login") && !requestUrl.equals("/user/exit") && !requestUrl.equals("/user/getVerifiCode") && !requestUrl.equals("/user/loginAndroid")) {
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            return false;
        }
        return true;
    }
}
