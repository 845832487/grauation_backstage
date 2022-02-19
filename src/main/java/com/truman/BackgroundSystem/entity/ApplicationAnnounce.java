package com.truman.BackgroundSystem.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@TableName("application_announce")
@ApiModel(value = "ApplicationAnnounce对象", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationAnnounce implements Serializable {

    private static final long serialVersionUID = 1L;

    private String applicationId;

    private String applicantId;

    private String announceId;

    private Boolean isFinish;

    private LocalDateTime createDate;

    private LocalDateTime finishDate;

}
