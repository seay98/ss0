package org.hai.springsecuritysample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assert.*;

@SpringBootTest
class SpringSecurity0ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testPasswordEncrypt() {
        //工作因子，默认为10，最小为4，最大为31，越大越慢
        PasswordEncoder encoder = new BCryptPasswordEncoder(4);
        //明文：password
        //密文：即使明文相同，每次生成的密文也不一样
        String res = encoder.encode("password");
        System.out.println(res);

        Assert.isTrue(encoder.matches("password", res), "密码不一致");
    }

}
