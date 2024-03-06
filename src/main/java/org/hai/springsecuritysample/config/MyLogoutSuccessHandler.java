package org.hai.springsecuritysample.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.util.HashMap;

public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //组装返回数据
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("message", "注销成功");
        //将数据转为json字符串
        String json = JSON.toJSONString(map);

        //设置http头信息，并返回
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
