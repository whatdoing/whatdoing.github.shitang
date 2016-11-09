package com.haocean.dinninghall.entity.record;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class RatingCriteria {
    private String create_date;//
    private String safety_system;//1.健康管理和培训
    private String personnel;//2.无有效健康证明，每发现一人，扣0.5分。
    private String personnel2;//3.无晨检记录，扣2分
    private String personnel3;//4.患有《食品安全法实施条例》
    private String personnel4;//5.无食品安全管理员，
    private String selfdiscipline;//6.未开展或参加食品安全知识和职业道德培训，
    private String selfdiscipline2;//7.许可证、食品安全等级、
    private String selfdiscipline3;//8.食品安全失信行为★：
    private String layout_environment;//9.布局流程不符合要求、
    private String layout_environment2;//10.大型以上餐馆，供餐人数300人以上的学校
    private String layout_environment3;//11.场所内发现鼠迹、蟑螂或苍蝇等害虫，
    private String layout_environment4;//12.更衣场所与加工场所不在同一建筑物内，
    private String layout_environment5;//13.墙面、天花板、门窗、地面材料（含颜色）不符合规范要求，
    private String layout_environment6;//14.无餐厨废弃油脂和泔水回收协议、无回收记录，各扣1分；

    private String facility;//15.无通风排烟设施，扣2分；
    private String facility2;//16.洗手消毒设施★：
    private String facility3;//17.动物类、植物类、
    private String facility4;//18.餐具、拖把、
    private String facility5;//19.冷藏设施不能正常运转，
    private String facility6;//20.紫外线灯、净水设备、
    private String facility7;//21.洗手消毒设施、
    private String purchase;//22.食品原辅材料（含食品添加剂）
    private String purchase2;//23.有毒有害物品与食品混放★，
    private String production;//24.加工动物性、植物性、
    private String production2;//25.半成品容器直接放地面，扣1分；
    private String production3;//26.食品添加剂★：超范围使用食品添加剂，扣5分；
    private String production4;//27.食品留样★：留样容器未清洗消毒、未标注加工时间、
    private String tableware_disinfection;//28.餐用具清洗消毒★：餐用具不消毒、
    private String tableware_disinfection2;//29.提供集中消毒餐饮具的单位不具备资质，
    private String room_management;//30.专间区域★：未按要求设立预进间，
    private String room_management2;//31.墙裙未铺设到顶，扣2分；设制明沟或使用不带水封地漏，

    private String transportation;//32.无检验室、未开展检验，扣3分；检
    private String transportation2;//33.配送食品的最小使用包装或食品容器包装上的标签未标明加工单位、
    private String transportation3;//34.运输★：运输车辆为非专用封闭式，扣5分；
    private String othertext;//

    private String bonusitems;//
    private String inspection_result;//
    private String earned_score;//
    private String realization_score;//
    private String standardized_score;//
    private String assessment_level;//
    private String school_name;//
    private String new_safety_system;//一.食品安全管理制度
    private String new_personnel;//二.人员管理
    private String new_selfdiscipline;//三.诚信建设
    private String new_layout_environment;//四.布局流程和场所环境
    private String new_facility;//五.设施设备
    private String new_purchase;//六.采购贮存
    private String new_production;//七.加工制作
    private String new_tableware_disinfection;//八.餐用具清洗消毒
    private String new_room_management;//九.专间管理
    private String new_transportation;//十.检验运输（适用集体用餐配送单位和中央厨房）
    private String new_othertext;//十一.其它
    private String new_bonusitems;//十二.加分项

	private String month;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	private String permit;//
	private String address;//
	private String userid;//
	private String school_id;//

	private String legal;//
	private String contactPhone;//
	private String inspect_people;//

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getPersonnel2() {
		return personnel2;
	}

	public void setPersonnel2(String personnel2) {
		this.personnel2 = personnel2;
	}

	public String getPersonnel3() {
		return personnel3;
	}

	public void setPersonnel3(String personnel3) {
		this.personnel3 = personnel3;
	}

	public String getPersonnel4() {
		return personnel4;
	}

	public void setPersonnel4(String personnel4) {
		this.personnel4 = personnel4;
	}

	public String getSelfdiscipline2() {
		return selfdiscipline2;
	}

	public void setSelfdiscipline2(String selfdiscipline2) {
		this.selfdiscipline2 = selfdiscipline2;
	}

	public String getSelfdiscipline3() {
		return selfdiscipline3;
	}

	public void setSelfdiscipline3(String selfdiscipline3) {
		this.selfdiscipline3 = selfdiscipline3;
	}

	public String getLayout_environment2() {
		return layout_environment2;
	}

	public void setLayout_environment2(String layout_environment2) {
		this.layout_environment2 = layout_environment2;
	}

	public String getLayout_environment3() {
		return layout_environment3;
	}

	public void setLayout_environment3(String layout_environment3) {
		this.layout_environment3 = layout_environment3;
	}

	public String getLayout_environment4() {
		return layout_environment4;
	}

	public void setLayout_environment4(String layout_environment4) {
		this.layout_environment4 = layout_environment4;
	}

	public String getLayout_environment5() {
		return layout_environment5;
	}

	public void setLayout_environment5(String layout_environment5) {
		this.layout_environment5 = layout_environment5;
	}

	public String getLayout_environment6() {
		return layout_environment6;
	}

	public void setLayout_environment6(String layout_environment6) {
		this.layout_environment6 = layout_environment6;
	}

	public String getFacility2() {
		return facility2;
	}

	public void setFacility2(String facility2) {
		this.facility2 = facility2;
	}

	public String getFacility3() {
		return facility3;
	}

	public void setFacility3(String facility3) {
		this.facility3 = facility3;
	}

	public String getFacility4() {
		return facility4;
	}

	public void setFacility4(String facility4) {
		this.facility4 = facility4;
	}

	public String getFacility5() {
		return facility5;
	}

	public void setFacility5(String facility5) {
		this.facility5 = facility5;
	}

	public String getFacility6() {
		return facility6;
	}

	public void setFacility6(String facility6) {
		this.facility6 = facility6;
	}

	public String getFacility7() {
		return facility7;
	}

	public void setFacility7(String facility7) {
		this.facility7 = facility7;
	}

	public String getPurchase2() {
		return purchase2;
	}

	public void setPurchase2(String purchase2) {
		this.purchase2 = purchase2;
	}

	public String getProduction2() {
		return production2;
	}

	public void setProduction2(String production2) {
		this.production2 = production2;
	}

	public String getProduction3() {
		return production3;
	}

	public void setProduction3(String production3) {
		this.production3 = production3;
	}

	public String getProduction4() {
		return production4;
	}

	public void setProduction4(String production4) {
		this.production4 = production4;
	}

	public String getTableware_disinfection2() {
		return tableware_disinfection2;
	}

	public void setTableware_disinfection2(String tableware_disinfection2) {
		this.tableware_disinfection2 = tableware_disinfection2;
	}

	public String getRoom_management2() {
		return room_management2;
	}

	public void setRoom_management2(String room_management2) {
		this.room_management2 = room_management2;
	}

	public String getTransportation2() {
		return transportation2;
	}

	public void setTransportation2(String transportation2) {
		this.transportation2 = transportation2;
	}

	public String getTransportation3() {
		return transportation3;
	}

	public void setTransportation3(String transportation3) {
		this.transportation3 = transportation3;
	}

	public String getNew_safety_system() {
		return new_safety_system;
	}

	public void setNew_safety_system(String new_safety_system) {
		this.new_safety_system = new_safety_system;
	}

	public String getNew_personnel() {
		return new_personnel;
	}

	public void setNew_personnel(String new_personnel) {
		this.new_personnel = new_personnel;
	}

	public String getNew_selfdiscipline() {
		return new_selfdiscipline;
	}

	public void setNew_selfdiscipline(String new_selfdiscipline) {
		this.new_selfdiscipline = new_selfdiscipline;
	}

	public String getNew_layout_environment() {
		return new_layout_environment;
	}

	public void setNew_layout_environment(String new_layout_environment) {
		this.new_layout_environment = new_layout_environment;
	}

	public String getNew_facility() {
		return new_facility;
	}

	public void setNew_facility(String new_facility) {
		this.new_facility = new_facility;
	}

	public String getNew_purchase() {
		return new_purchase;
	}

	public void setNew_purchase(String new_purchase) {
		this.new_purchase = new_purchase;
	}

	public String getNew_production() {
		return new_production;
	}

	public void setNew_production(String new_production) {
		this.new_production = new_production;
	}

	public String getNew_tableware_disinfection() {
		return new_tableware_disinfection;
	}

	public void setNew_tableware_disinfection(String new_tableware_disinfection) {
		this.new_tableware_disinfection = new_tableware_disinfection;
	}

	public String getNew_room_management() {
		return new_room_management;
	}

	public void setNew_room_management(String new_room_management) {
		this.new_room_management = new_room_management;
	}

	public String getNew_transportation() {
		return new_transportation;
	}

	public void setNew_transportation(String new_transportation) {
		this.new_transportation = new_transportation;
	}

	public String getNew_othertext() {
		return new_othertext;
	}

	public void setNew_othertext(String new_othertext) {
		this.new_othertext = new_othertext;
	}

	public String getNew_bonusitems() {
		return new_bonusitems;
	}

	public void setNew_bonusitems(String new_bonusitems) {
		this.new_bonusitems = new_bonusitems;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}


    public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
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
	public String getOthertext() {
		return othertext;
	}
	public void setOthertext(String othertext) {
		this.othertext = othertext;
	}
	public String getBonusitems() {
		return bonusitems;
	}
	public void setBonusitems(String bonusitems) {
		this.bonusitems = bonusitems;
	}
	public String getInspection_result() {
		return inspection_result;
	}
	public void setInspection_result(String inspection_result) {
		this.inspection_result = inspection_result;
	}
	public String getEarned_score() {
		return earned_score;
	}
	public void setEarned_score(String earned_score) {
		this.earned_score = earned_score;
	}
	public String getRealization_score() {
		return realization_score;
	}
	public void setRealization_score(String realization_score) {
		this.realization_score = realization_score;
	}
	public String getStandardized_score() {
		return standardized_score;
	}
	public void setStandardized_score(String standardized_score) {
		this.standardized_score = standardized_score;
	}
	public String getAssessment_level() {
		return assessment_level;
	}
	public void setAssessment_level(String assessment_level) {
		this.assessment_level = assessment_level;
	}

	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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

	public String getInspect_people() {
		return inspect_people;
	}
	public void setInspect_people(String inspect_people) {
		this.inspect_people = inspect_people;
	}

   
    
}
