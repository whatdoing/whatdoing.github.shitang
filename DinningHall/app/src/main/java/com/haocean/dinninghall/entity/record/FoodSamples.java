package com.haocean.dinninghall.entity.record;

public class FoodSamples {

	private String create_date;//创建日期（录入时间）
	private String number;//餐次
//	private String foodname;//留样食品名称
//	private String foodcount;//数量
	private String start_date;//留样开始时间[留样开始时间——留样结束时间输出时候 去除00：00：00]
	private String end_date;// 留样结束时间
	private String peoplenum;//人次
	private String liable_person;//责任人
	private String userid;//创建该条记录的用户id
	private String school_id;//该记录所属学校id[关联字段]
	private String tableandroidnum;//名称数量
	private String canteenName;//食堂名称

	public String getCanteenName() {
		return canteenName;
	}

	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}

	public String getTableandroidnum() {
		return tableandroidnum;
	}

	public void setTableandroidnum(String tableandroidnum) {
		this.tableandroidnum = tableandroidnum;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

//	public String getFoodname() {
//		return foodname;
//	}
//
//	public void setFoodname(String foodname) {
//		this.foodname = foodname;
//	}
//
//	public String getFoodcount() {
//		return foodcount;
//	}
//
//	public void setFoodcount(String foodcount) {
//		this.foodcount = foodcount;
//	}

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

	public String getPeoplenum() {
		return peoplenum;
	}

	public void setPeoplenum(String peoplenum) {
		this.peoplenum = peoplenum;
	}

	public String getLiable_person() {
		return liable_person;
	}

	public void setLiable_person(String liable_person) {
		this.liable_person = liable_person;
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
}
