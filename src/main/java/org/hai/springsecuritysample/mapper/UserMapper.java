package org.hai.springsecuritysample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.hai.springsecuritysample.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
