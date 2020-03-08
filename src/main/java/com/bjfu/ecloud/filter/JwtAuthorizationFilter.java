package com.bjfu.ecloud.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjfu.ecloud.util.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 权限验证
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private String username;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));

//        RequestParametersWrapper requestParametersWrapper = new RequestParametersWrapper(request, username);
        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        username = JwtTokenUtils.getUsername(token);
        String role = JwtTokenUtils.getUserRole(token);
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
        }
        return null;
    }

    private class RequestParametersWrapper extends HttpServletRequestWrapper{
        //新body集合
        private Map map;

        public RequestParametersWrapper(HttpServletRequest request, String username) {
            super(request);
            Map params = request.getParameterMap();
            map = new ConcurrentHashMap(params);
            map.put("username", username);
        }

//        @Override
//        public Enumeration<String> getParameterNames() {
//            return new Vector<String>(map.keySet()).elements();
//        }
//
//        @Override
//        public String[] getParameterValues(String name) {
//            return (String[]) map.get(name);
//        }
//
//        @Override
//        public String getParameter(String name) {
//            return (String) map.get(name);
//        }

        @Override
        public BufferedReader getReader() throws IOException {
            return super.getReader();
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return super.getInputStream();
        }

        //        @Override
//        public Map<String, String[]> getParameterMap() {
//            return map;
//        }
    }
}
