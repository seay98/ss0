package org.hai.springsecuritysample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sc_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private Boolean enabled;
}
