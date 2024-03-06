package org.hai.springsecuritysample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.hai.springsecuritysample.config.DbUserDetailsManager;
import org.hai.springsecuritysample.entity.User;
import org.hai.springsecuritysample.mapper.UserMapper;
import org.hai.springsecuritysample.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {

    @Resource
    DbUserDetailsManager dbUserDetailsManager;

    @Override
    public void addUserDetails(User user) {
        UserDetails userDetails =  org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }
}
