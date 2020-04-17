package com.lyy.dataobject;

public class UserDO {
    private Integer id;

    private String name;

    private Byte gender;

    private Integer age;

    private String telphone;

    private String regisitMode;

    private Integer thirdPartyId;

    public UserDO(Integer id, String name, Byte gender, Integer age, String telphone, String regisitMode, Integer thirdPartyId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.telphone = telphone;
        this.regisitMode = regisitMode;
        this.thirdPartyId = thirdPartyId;
    }

    public UserDO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getRegisitMode() {
        return regisitMode;
    }

    public void setRegisitMode(String regisitMode) {
        this.regisitMode = regisitMode == null ? null : regisitMode.trim();
    }

    public Integer getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(Integer thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", telphone='" + telphone + '\'' +
                ", regisitMode='" + regisitMode + '\'' +
                ", thirdPartyId=" + thirdPartyId +
                '}';
    }
}