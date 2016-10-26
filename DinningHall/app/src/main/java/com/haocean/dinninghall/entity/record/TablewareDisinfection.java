package com.haocean.dinninghall.entity.record;

public class TablewareDisinfection {
	

	private String create_date;//创建日期
	private String tablewarename;//餐具名称
	private String tablewarecount;//数量(避免中文)
	private String cleantype;//清洗方式(字数不多)
	private String start_date;//消毒开始时间[消毒开始时间——消毒结束时间输出时候 去除00：00：00]
	private String end_date;//消毒结束时间
	private String clean_condition;//消毒情况（字数不定）
	private String liable_person;//责任人[以上表格基本字段 以下关联字段]
	private String school_id;//创建的学校id
	private String userid;//创建人的用户id

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getTablewarename() {
		return tablewarename;
	}

	public void setTablewarename(String tablewarename) {
		this.tablewarename = tablewarename;
	}

	public String getTablewarecount() {
		return tablewarecount;
	}

	public void setTablewarecount(String tablewarecount) {
		this.tablewarecount = tablewarecount;
	}

	public String getCleantype() {
		return cleantype;
	}

	public void setCleantype(String cleantype) {
		this.cleantype = cleantype;
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

	public String getClean_condition() {
		return clean_condition;
	}

	public void setClean_condition(String clean_condition) {
		this.clean_condition = clean_condition;
	}

	public String getLiable_person() {
		return liable_person;
	}

	public void setLiable_person(String liable_person) {
		this.liable_person = liable_person;
	}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
