package com.haocean.dinninghall.entity.record;

public class UltravioletDisinfection {

	private String entry_date;//手动输入时间
	private String create_date;//创建日期（录入时间）
	private String disinfection_area;//消毒区域
	private String start_date;//消毒开始时间
	private String end_date;//消毒结束时间
	private String cumulative_time;//累计时间
	private String liable_person;//责任人
	private String remarks;//备注
	private String userid;//创建该条记录的用户id
	private String schoolid;//该记录所属学校id[关联字段]
	private String canteenName;//该记录所属学校id[关联字段]

	public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

	public String getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getDisinfection_area() {
		return disinfection_area;
	}

	public void setDisinfection_area(String disinfection_area) {
		this.disinfection_area = disinfection_area;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getCumulative_time() {
		return cumulative_time;
	}

	public void setCumulative_time(String cumulative_time) {
		this.cumulative_time = cumulative_time;
	}

	public String getLiable_person() {
		return liable_person;
	}

	public void setLiable_person(String liable_person) {
		this.liable_person = liable_person;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}
}
