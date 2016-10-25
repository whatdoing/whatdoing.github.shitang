package com.haocean.dinninghall.entity.record;

public class AdditiveRegistration {

	private String create_date;//创建日期（录入时间）
	private String name;//品名(食品添加剂名称)
	private String manufacturer;//生产厂家
	private String manufacture_date;//生产日期[食品添加剂的生产日期]
	private String quality;//保质期
	private String supplyunit;//供货单位
	private String purchase_date;//采购日期[类型date 2016-9-25]
	private String purchasenum;//	采购数量
	private String userecord;//	使用记录(字数较多)
	private String destruction_record;//	销毁记录（字数较多）
	private String liable_person;//	责任人签名
	private String userid;//创建该条记录的用户id
	private String school_id;//该记录所属学校id[关联字段]


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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufacture_date() {
		return manufacture_date;
	}

	public void setManufacture_date(String manufacture_date) {
		this.manufacture_date = manufacture_date;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getSupplyunit() {
		return supplyunit;
	}

	public void setSupplyunit(String supplyunit) {
		this.supplyunit = supplyunit;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public String getPurchasenum() {
		return purchasenum;
	}

	public void setPurchasenum(String purchasenum) {
		this.purchasenum = purchasenum;
	}

	public String getUserecord() {
		return userecord;
	}

	public void setUserecord(String userecord) {
		this.userecord = userecord;
	}

	public String getDestruction_record() {
		return destruction_record;
	}

	public void setDestruction_record(String destruction_record) {
		this.destruction_record = destruction_record;
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
