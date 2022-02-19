package com.truman.BackgroundSystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 冯有恒
 * @since 2022-02-06
 */
@TableName("application_checkout")
@ApiModel(value = "ApplicationCheckout对象", description = "")
public class ApplicationCheckout implements Serializable {

    private static final long serialVersionUID = 1L;

    private String applicationId;

    private String applicantId;

    private String dormkeeperId;

    private String counselorId;

    private String dormmanagerId;

    private String message;

    private String fileAddr;

    private LocalDateTime createTime;

    private LocalDateTime finishTime;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public String getDormkeeperId() {
        return dormkeeperId;
    }

    public void setDormkeeperId(String dormkeeperId) {
        this.dormkeeperId = dormkeeperId;
    }
    public String getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(String counselorId) {
        this.counselorId = counselorId;
    }
    public String getDormmanagerId() {
        return dormmanagerId;
    }

    public void setDormmanagerId(String dormmanagerId) {
        this.dormmanagerId = dormmanagerId;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getFileAddr() {
        return fileAddr;
    }

    public void setFileAddr(String fileAddr) {
        this.fileAddr = fileAddr;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "ApplicationCheckout{" +
            "applicationId=" + applicationId +
            ", applicantId=" + applicantId +
            ", dormkeeperId=" + dormkeeperId +
            ", counselorId=" + counselorId +
            ", dormmanagerId=" + dormmanagerId +
            ", message=" + message +
            ", fileAddr=" + fileAddr +
            ", createTime=" + createTime +
            ", finishTime=" + finishTime +
        "}";
    }
}
