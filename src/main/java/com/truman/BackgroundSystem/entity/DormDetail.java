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
@TableName("dorm_detail")
@ApiModel(value = "DormDetail对象", description = "")
public class DormDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String buildingId;

    private String dormId;

    private String member1;

    private String member2;

    private String member3;

    private String member4;

    private String member5;

    private String member6;

    private Integer spareMember;

    private Integer depositBucketNum;

    private Integer money;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }
    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }
    public String getMember1() {
        return member1;
    }

    public void setMember1(String member1) {
        this.member1 = member1;
    }
    public String getMember2() {
        return member2;
    }

    public void setMember2(String member2) {
        this.member2 = member2;
    }
    public String getMember3() {
        return member3;
    }

    public void setMember3(String member3) {
        this.member3 = member3;
    }
    public String getMember4() {
        return member4;
    }

    public void setMember4(String member4) {
        this.member4 = member4;
    }
    public String getMember5() {
        return member5;
    }

    public void setMember5(String member5) {
        this.member5 = member5;
    }
    public String getMember6() {
        return member6;
    }

    public void setMember6(String member6) {
        this.member6 = member6;
    }
    public Integer getSpareMember() {
        return spareMember;
    }

    public void setSpareMember(Integer spareMember) {
        this.spareMember = spareMember;
    }
    public Integer getDepositBucketNum() {
        return depositBucketNum;
    }

    public void setDepositBucketNum(Integer depositBucketNum) {
        this.depositBucketNum = depositBucketNum;
    }
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "DormDetail{" +
            "buildingId=" + buildingId +
            ", dormId=" + dormId +
            ", member1=" + member1 +
            ", member2=" + member2 +
            ", member3=" + member3 +
            ", member4=" + member4 +
            ", member5=" + member5 +
            ", member6=" + member6 +
            ", spareMember=" + spareMember +
            ", depositBucketNum=" + depositBucketNum +
            ", money=" + money +
        "}";
    }
}
