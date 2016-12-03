package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.filter.charater.CharacterRequest;

public class CharacterFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        CharacterRequest wrapRequest = new CharacterRequest(request,
                request.getParameterMap());
        chain.doFilter(wrapRequest, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}