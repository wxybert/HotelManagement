package com.tellh.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CharSetFilter implements Filter {
    private FilterConfig conf;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String charSet = conf.getInitParameter("CharSet");
        request.setCharacterEncoding(charSet);
        response.setCharacterEncoding(charSet);
        response.setContentType("text/html;charset=" + charSet);

        String access = conf.getInitParameter("access");
        response.addHeader("Access-Control-Allow-Origin", access);
        response.addHeader("Access-Control-Allow-Methods", "POST,GET");
        response.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, Content-Language, Cache-Control, X-E4M-With");
        chain.doFilter(new MyHttpServletRequest(request), response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.conf = filterConfig;
    }

    @Override
    public void destroy() {
    }

    //包装器
    private class MyHttpServletRequest extends HttpServletRequestWrapper {
        private HttpServletRequest requst;

        public MyHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.requst = request;
        }

        @Override
        public String getParameter(String name) {
            String value = this.requst.getParameter(name);
            if (value == null) {
                return null;
            }
            //如果不是get请求
            if (!this.requst.getMethod().equalsIgnoreCase("get")) {
                return value;
            }
            try {
                value = new String(value.getBytes("ISO8859-1"), this.requst.getCharacterEncoding());
                return value;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}