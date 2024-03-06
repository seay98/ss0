package org.hai.springsecuritysample.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取用户信息
        Object principal = authentication.getPrincipal();
//        Object credentials = authentication.getCredentials();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        //组装返回数据
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("message", "登录成功");
        map.put("data", principal);
        //将数据转为json字符串
        String json = JSON.toJSONString(map);

        //设置http头信息，并返回
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
