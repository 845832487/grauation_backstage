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
@TableName("application_term_finish_checkout")
@ApiModel(value = "ApplicationTermFinishCheckout对象", description = "")
public class ApplicationTermFinishCheckout implements Serializable {

    private static final long serialVersionUID = 1L;

    private String applicationId;

    private String applicantId;

    private String approvalId;

    private String fileAddr;

    private LocalDateTime createTime;

    private String message;

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
    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
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
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "ApplicationTermFinishCheckout{" +
            "applicationId=" + applicationId +
            ", applicantId=" + applicantId +
            ", approvalId=" + approvalId +
            ", fileAddr=" + fileAddr +
            ", createTime=" + createTime +
            ", message=" + message +
            ", finishTime=" + finishTime +
        "}";
    }
}
