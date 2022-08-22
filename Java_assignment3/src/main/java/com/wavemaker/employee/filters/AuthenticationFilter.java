package com.wavemaker.employee.filters;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
    }
    @Override
    public void destroy(){
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        HttpSession session=httpServletRequest.getSession(false);
        if(session==null){
            httpServletResponse.getWriter().write("Login required");
            httpServletResponse.setStatus(401);
        }
        else{
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}
