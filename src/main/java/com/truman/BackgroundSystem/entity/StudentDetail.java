package com.truman.BackgroundSystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@TableName("student_detail")
@ApiModel(value = "StudentDetail对象", description = "")
@Data
public class StudentDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String telephone;

    private String email;

    private Boolean sex;

    private String icon;

    private Boolean isDormLeader;

    private String buildingNum;

    private String dormNum;

    private Boolean inOut;


}
