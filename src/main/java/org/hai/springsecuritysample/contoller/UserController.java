package org.hai.springsecuritysample.contoller;

import jakarta.annotation.Resource;
import org.hai.springsecuritysample.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.hai.springsecuritysample.entity.User;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER') or authentication.name=='test'")
    public List<User> getList() {
        return userService.list();
    }

    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyAuthority('USER_ADD')")
    public void add(@RequestBody User user) {
        userService.addUserDetails(user);
    }
}
