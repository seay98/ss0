package org.hai.springsecuritysample.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;
import java.util.HashMap;

public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {

        //组装返回数据
        HashMap map = new HashMap();
        map.put("code", -1);
        map.put("message", "账号已在其他设备登录");
        //将数据转为json字符串
        String json = JSON.toJSONString(map);

        //设置http头信息，并返回
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
