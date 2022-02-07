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
@TableName("worker_detail")
@ApiModel(value = "WorkerDetail对象", description = "")
public class WorkerDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String telephone;

    private String email;

    private Boolean sex;

    private String icon;

    private Integer job;

    private String buildingInCharge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }
    public String getBuildingInCharge() {
        return buildingInCharge;
    }

    public void setBuildingInCharge(String buildingInCharge) {
        this.buildingInCharge = buildingInCharge;
    }

    @Override
    public String toString() {
        return "WorkerDetail{" +
            "id=" + id +
            ", name=" + name +
            ", telephone=" + telephone +
            ", email=" + email +
            ", sex=" + sex +
            ", icon=" + icon +
            ", job=" + job +
            ", buildingInCharge=" + buildingInCharge +
        "}";
    }
}
