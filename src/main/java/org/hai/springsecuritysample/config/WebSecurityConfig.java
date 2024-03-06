package org.hai.springsecuritysample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity //基于方法的授权
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
//                //具有USER_LIST权限的用户可以访问/user/list
//                .requestMatchers("/user/list").hasAnyAuthority("USER_LIST")
//                //具有USER_ADD权限的用户可以访问/user/add
//                .requestMatchers("/user/add").hasAnyAuthority("USER_ADD")
                //具有管理员角色的用户可访问/user下所有接口
//                .requestMatchers("/user/**").hasRole("ADMIN")
                //对所有请求开启授权保护
                .anyRequest()
                //已认证的请求会被自动授权
                .authenticated()
            );
//          .formLogin(withDefaults());
        //登录设置
        http.formLogin(form -> {
            form.loginPage("/login").permitAll() //设置自定义登录页面地址，并设置无需权限可访问
    //                        .usernameParameter("myusername") //自定义表单用户名name
    //                        .passwordParameter("mypassword") //自定义表单密码name
    //                        .failureUrl("/login?failure") //自动以校验失败时的跳转地址，默认为?error
                    .successHandler(new MyAuthenticationSuccessHandler()) //认证成功时的处理
                    .failureHandler(new MyAuthenticationFailureHandler()) //认证失败时的出理
            ;
        });
        //登出设置
        http.logout(logout -> {
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler());
        });

        //错误处理
        http.exceptionHandling(exception -> {
            //请求未被认证时的处理，原为跳转至登录页
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());
            //请求被拒绝时的处理
            exception.accessDeniedHandler(new MyAccessDeniedHandler());
        });

        //会话管理
        http.sessionManagement(session -> {
            //设置最大会话数，并自定义处理逻辑
            session.maximumSessions(1).expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });

        //开启跨域访问权限
        http.cors(withDefaults());

        //关闭默认的csrf攻击防御
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    // 基于内存的用户认证
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // 创建管理内存信息的类
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        // 创建用户，加入内存
//        manager.createUser(User.withDefaultPasswordEncoder().username("xin").password("password").roles("USER").build());
//        return manager;
//    }

    // 基于数据库的用户认证
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // 创建管理内存信息的类
//        DbUserDetailsManager manager = new DbUserDetailsManager();
//        return manager;
//    }

}
