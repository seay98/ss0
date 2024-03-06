package org.hai.springsecuritysample.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hai.springsecuritysample.entity.User;

public interface UserService extends IService<User> {
    public void addUserDetails(User user);
}
