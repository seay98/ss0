package org.hai.springsecuritysample.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String localizedMessage = exception.getLocalizedMessage();
        //组装返回数据
        HashMap map = new HashMap();
        map.put("code", -1);
        map.put("message", localizedMessage);
        //将数据转为json字符串
        String json = JSON.toJSONString(map);

        //设置http头信息，并返回
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
