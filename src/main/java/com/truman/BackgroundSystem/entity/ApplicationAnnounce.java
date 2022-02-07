package com.truman.BackgroundSystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("application_announce")
@ApiModel(value = "ApplicationAnnounce对象", description = "")
public class ApplicationAnnounce implements Serializable {

    private static final long serialVersionUID = 1L;

    private String applicationId;

    private String announceId;

    private Boolean condition;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
    public String getAnnounceId() {
        return announceId;
    }

    public void setAnnounceId(String announceId) {
        this.announceId = announceId;
    }
    public Boolean getCondition() {
        return condition;
    }

    public void setCondition(Boolean condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "ApplicationAnnounce{" +
            "applicationId=" + applicationId +
            ", announceId=" + announceId +
            ", condition=" + condition +
        "}";
    }
}
