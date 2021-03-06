package com.haocean.dinninghall.entity.record;

public class RecyclingProcess {

	private String create_date;//创建日期（录入时间）
	private String swillnum;//泔水数量（公斤）
	private String recyclinguse;//回收用途(文本录入)
//	private String catering_signature;//餐饮单位经手人签名[签名 文本]
//	private String recovery_signature;//回收单位经手人签名[签名 文本]
	private String userid;//创建该条记录的用户id
	private String school_id;//该记录所属学校id[关联字段]
	private String recovery_company;//[底部 回收单位的数据字段]回收单位名称
	private String recovery_address;//回收单位地址
	private String contacts;//联系人
	private String contactsphone;//电话
	private String canteenName;//食堂名称

	public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getSwillnum() {
		return swillnum;
	}

	public void setSwillnum(String swillnum) {
		this.swillnum = swillnum;
	}

	public String getRecyclinguse() {
		return recyclinguse;
	}

	public void setRecyclinguse(String recyclinguse) {
		this.recyclinguse = recyclinguse;
	}

//	public String getCatering_signature() {
//		return catering_signature;
//	}
//
//	public void setCatering_signature(String catering_signature) {
//		this.catering_signature = catering_signature;
//	}
//
//	public String getRecovery_signature() {
//		return recovery_signature;
//	}
//
//	public void setRecovery_signature(String recovery_signature) {
//		this.recovery_signature = recovery_signature;
//	}

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

	public String getRecovery_company() {
		return recovery_company;
	}

	public void setRecovery_company(String recovery_company) {
		this.recovery_company = recovery_company;
	}

	public String getRecovery_address() {
		return recovery_address;
	}

	public void setRecovery_address(String recovery_address) {
		this.recovery_address = recovery_address;
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
}
