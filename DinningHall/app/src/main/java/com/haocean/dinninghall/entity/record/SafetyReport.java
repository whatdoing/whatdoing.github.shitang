package com.haocean.dinninghall.entity.record;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class SafetyReport {
   /* private String promise;//*/
    private String safety_system;//
    private String personnel;//
    private String selfdiscipline;//
    private String layout_environment;//
    private String facility;//
    private String purchase;//
    private String production;//
    private String tableware_disinfection;//健康证到期期限
    private String room_management;//

    private String transportation;//
    private String specifications;//

    private String emergency;//
    private String rectification;//
    private String other_comments;//
    private String userid;//
    private String school_id;//健康证到期期限
    private String legal;//
    private String reportname;//

    private String create_date;//创建日期（录入时间）
    private String report_date;
    private String contacts;
    private String contactsphone;

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsphone() {
        return contactsphone;
    }

    public void setContactsphone(String contactsphone) {
        this.contactsphone = contactsphone;
    }

  /*  public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise;
    }*/
  private String report_month;

    public String getReport_month() {
        return report_month;
    }

    public void setReport_month(String report_month) {
        this.report_month = report_month;
    }

    public String getSafety_system() {
        return safety_system;
    }

    public void setSafety_system(String safety_system) {
        this.safety_system = safety_system;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getSelfdiscipline() {
        return selfdiscipline;
    }

    public void setSelfdiscipline(String selfdiscipline) {
        this.selfdiscipline = selfdiscipline;
    }

    public String getLayout_environment() {
        return layout_environment;
    }

    public void setLayout_environment(String layout_environment) {
        this.layout_environment = layout_environment;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getTableware_disinfection() {
        return tableware_disinfection;
    }

    public void setTableware_disinfection(String tableware_disinfection) {
        this.tableware_disinfection = tableware_disinfection;
    }

    public String getRoom_management() {
        return room_management;
    }

    public void setRoom_management(String room_management) {
        this.room_management = room_management;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getRectification() {
        return rectification;
    }

    public void setRectification(String rectification) {
        this.rectification = rectification;
    }

    public String getOther_comments() {
        return other_comments;
    }

    public void setOther_comments(String other_comments) {
        this.other_comments = other_comments;
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

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }
}
