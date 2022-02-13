package com.truman.BackgroundSystem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@TableName("user_login")
@ApiModel(value = "UserLogin对象", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;


    private String password;

    private String role;

}
