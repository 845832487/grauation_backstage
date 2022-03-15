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
@Data
@TableName("dorm_detail")
@ApiModel(value = "DormDetail对象", description = "")
public class DormDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String buildingId;

    private String dormId;

    private Integer roomsLeft;

    private Integer capacity;


}
