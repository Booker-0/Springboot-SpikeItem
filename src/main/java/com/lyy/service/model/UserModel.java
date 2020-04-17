package com.lyy.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//处理业务逻辑的核心模型
//DO只是对数据库的一个映射
public class UserModel {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String name;
    @NotNull(message = "性别不能不填写")
    private Byte gender;
    @NotNull(message = "年龄不能不填写")
    @Min(value = 0,message = "年龄必须大于0岁")
    @Max(value = 120,message = "年龄必须小于120岁")
    private Integer age;
    @NotBlank(message = "手机号不能为空")
    private String telphone;

    private String regisitMode;

    private Integer thirdPartyId;
    //虽然在两张表，但是属于同一个领域模型
    @NotBlank(message = "密码不能为空")
    private String encrptPassword;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Byte getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getTelphone() {
        return telphone;
    }

    public String getRegisitMode() {
        return regisitMode;
    }

    public Integer getThirdPartyId() {
        return thirdPartyId;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public void setRegisitMode(String regisitMode) {
        this.regisitMode = regisitMode;
    }

    public void setThirdPartyId(Integer thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }
}
