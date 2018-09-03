package com.ffcs.orderdinner.utils;

import com.ffcs.orderdinner.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class LoginFilter implements Filter {


    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        if (uri.endsWith("login.jsp") || uri.endsWith("login") || uri.endsWith("register.jsp") || uri.endsWith("register")) {
            chain.doFilter(request, response);
            return;
        }

        User login = (User) request.getSession().getAttribute("login");
        if (null == login) {
            response.sendRedirect("/orderdinner/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}