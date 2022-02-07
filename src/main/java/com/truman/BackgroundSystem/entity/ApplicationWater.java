package com.truman.BackgroundSystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("application_water")
@ApiModel(value = "ApplicationWater对象", description = "")
public class ApplicationWater implements Serializable {

    private static final long serialVersionUID = 1L;

    private String applicationId;

    private String applicantId;

    private String approvalId;

    private Integer bucketNum;

    private Integer returnDeposit;

    private Integer addDeposit;

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
    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }
    public Integer getBucketNum() {
        return bucketNum;
    }

    public void setBucketNum(Integer bucketNum) {
        this.bucketNum = bucketNum;
    }
    public Integer getReturnDeposit() {
        return returnDeposit;
    }

    public void setReturnDeposit(Integer returnDeposit) {
        this.returnDeposit = returnDeposit;
    }
    public Integer getAddDeposit() {
        return addDeposit;
    }

    public void setAddDeposit(Integer addDeposit) {
        this.addDeposit = addDeposit;
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
        return "ApplicationWater{" +
            "applicationId=" + applicationId +
            ", applicantId=" + applicantId +
            ", approvalId=" + approvalId +
            ", bucketNum=" + bucketNum +
            ", returnDeposit=" + returnDeposit +
            ", addDeposit=" + addDeposit +
            ", createTime=" + createTime +
            ", finishTime=" + finishTime +
        "}";
    }
}
