package com.truman.BackgroundSystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("application_late_return")
@ApiModel(value = "ApplicationLateReturn对象", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLateReturn implements Serializable {

    private static final long serialVersionUID = 1L;

    private String applicationId;

    private String applicantId;

    private String approvalId;

    private String message;

    private String fileAddr;

    private LocalDateTime lateDate;

    private LocalDateTime createTime;

    private LocalDateTime finishTime;


}
