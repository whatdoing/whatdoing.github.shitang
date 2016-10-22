package com.haocean.dinninghall.entity.record;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
public class MorningInspection {

    private String create_date;//创建日期（录入时间）
    private String name;//姓名
    private String healthycode;//健康证号(文本)
    private String liable_person;//责任人
    private String isdiarrhea;//是否腹泻
    private String istrauma;//是否手部创伤（文本）
    private String iscold;//是否感冒发热（文本）
    private String ispurulent;//是否化脓性皮肤病（文本）
    private String isotherhealth;//其他健康状况(文本)
    private String userid;//创建该条记录的用户id
    private String school_id;//该记录所属学校id[关联字段]
    private String morningcheck;//晨检异常处置情况

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealthycode() {
        return healthycode;
    }

    public void setHealthycode(String healthycode) {
        this.healthycode = healthycode;
    }

    public String getIsdiarrhea() {
        return isdiarrhea;
    }

    public void setIsdiarrhea(String isdiarrhea) {
        this.isdiarrhea = isdiarrhea;
    }

    public String getLiable_person() {
        return liable_person;
    }

    public void setLiable_person(String liable_person) {
        this.liable_person = liable_person;
    }

    public String getIstrauma() {
        return istrauma;
    }

    public void setIstrauma(String istrauma) {
        this.istrauma = istrauma;
    }

    public String getIscold() {
        return iscold;
    }

    public void setIscold(String iscold) {
        this.iscold = iscold;
    }

    public String getIspurulent() {
        return ispurulent;
    }

    public void setIspurulent(String ispurulent) {
        this.ispurulent = ispurulent;
    }

    public String getIsotherhealth() {
        return isotherhealth;
    }

    public void setIsotherhealth(String isotherhealth) {
        this.isotherhealth = isotherhealth;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getMorningcheck() {
        return morningcheck;
    }

    public void setMorningcheck(String morningcheck) {
        this.morningcheck = morningcheck;
    }
}
