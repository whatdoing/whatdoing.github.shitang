package com.haocean.dinninghall.contexts;

import android.app.Fragment;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.adapter.recordview.AdditiveView;
import com.haocean.dinninghall.adapter.recordview.CheckLogView;
import com.haocean.dinninghall.adapter.recordview.FoodView;

import com.haocean.dinninghall.adapter.recordview.MorningInspectionView;
import com.haocean.dinninghall.adapter.recordview.OilView;
import com.haocean.dinninghall.adapter.recordview.RatingCriteriaView;
import com.haocean.dinninghall.adapter.recordview.RecyclingView;

import com.haocean.dinninghall.adapter.recordview.ReviewRecordView;
import com.haocean.dinninghall.adapter.recordview.RosterView;
import com.haocean.dinninghall.adapter.recordview.SafetyView;
import com.haocean.dinninghall.adapter.recordview.TablewareView;
import com.haocean.dinninghall.adapter.recordview.UltravioletView;
import com.haocean.dinninghall.entity.record.AdditiveRegistration;
import com.haocean.dinninghall.entity.record.CheckLog;
import com.haocean.dinninghall.entity.record.FeedBack;
import com.haocean.dinninghall.entity.record.FoodSamples;

import com.haocean.dinninghall.entity.record.MorningInspection;
import com.haocean.dinninghall.entity.record.OilRecovery;
import com.haocean.dinninghall.entity.record.RatingCriteria;
import com.haocean.dinninghall.entity.record.RecyclingProcess;

import com.haocean.dinninghall.entity.record.ReviewRecord;
import com.haocean.dinninghall.entity.record.Roster;
import com.haocean.dinninghall.entity.record.SafetyReport;
import com.haocean.dinninghall.entity.record.TablewareDisinfection;
import com.haocean.dinninghall.entity.record.UltravioletDisinfection;
import com.haocean.dinninghall.record.CreateRecordFragment.AdditiveRegistrationFragment;
import com.haocean.dinninghall.record.CreateRecordFragment.FoodSamplesFragment;
import com.haocean.dinninghall.record.CreateRecordFragment.OilRecoveryFragment;
import com.haocean.dinninghall.record.CreateRecordFragment.RecyclingProcessFragment;
import com.haocean.dinninghall.record.CreateRecordFragment.TablewareDisinfectionFragment;
import com.haocean.dinninghall.record.CreateRecordFragment.UltravioletDisinfectionFragment;
import com.haocean.dinninghall.review.CreateSafetyFragment.ReviewRecordFragment;
import com.haocean.dinninghall.safety.CreateSafetyFragment.CheckLogFragment;
import com.haocean.dinninghall.safety.CreateSafetyFragment.RatingCriteriaFragment;
import com.haocean.dinninghall.safety.CreateSafetyFragment.SafetyReportFragment;
import com.haocean.dinninghall.safety.DetailActivity;
import com.haocean.dinninghall.safety.DetailFragment.DetailFragment1;
import com.haocean.dinninghall.safety.DetailFragment.DetailFragment2;
import com.haocean.dinninghall.safety.DetailFragment.DetailFragment3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haocean on 2016/9/25.
 */
public class RecordList {

    public final static Map<String,Class> Class=new HashMap<String,Class>();
    public final static Map<String,Class> Entity=new HashMap<String,Class>();
    public final static Map<String, Integer> Layout=new HashMap<String, Integer>();
    public final static Map<String, Integer> RightLayout=new HashMap<String, Integer>();
    public final static Map<String, Fragment> Fragment=new HashMap<String, Fragment>();
    public final static Map<String, Fragment> DetailFragment=new HashMap<String, Fragment>();
    public final static Map<String,String> Lists=new HashMap<String,String>();
    public final static ArrayList<String> ArrStr=new ArrayList<String>();


    public final static ArrayList<String> SafetyArrStr=new ArrayList<String>();
    public final static Map<String,String> SafetyLists=new HashMap<String,String>();

    public final static Map<String,String> ReviewLists=new HashMap<String,String>();
    public final static ArrayList<String> ReviewArrStr=new ArrayList<String>();

    public final static ArrayList<String> UserInfoArr=new ArrayList<String>();
    public final static Map<String,String> UserSave=new HashMap<String,String>();
    static {

        UserInfoArr.add(0,"realname");
        UserInfoArr.add(1,"sex");
        UserInfoArr.add(2,"phone");
        UserInfoArr.add(3,"email");

        Lists.put("TablewareDisinfection","餐具清洗消毒记录表");
        Lists.put("OilRecovery","废弃油脂回收处理记录表");
        Lists.put("RecyclingProcess","泔水类废弃物回收处理记录表");
        Lists.put("FoodSamples","食品留样记录表");
        Lists.put("AdditiveRegistration","食品添加剂采购、验收、使用、销毁登记表");
        Lists.put("UltravioletDisinfection","紫外线消毒记录表");

        SafetyLists.put("RatingCriteria","餐饮服务单位食品安全自评标准");
        SafetyLists.put("CheckLog","餐饮服务单位食品安全自查日志表");
        SafetyLists.put("SafetyReport","餐饮服务单位食品安全综合报告");

        ReviewLists.put("ReviewRecord","整改及复查记录表");

        Layout.put("TablewareDisinfection",R.layout.tableware_list_item);
        Layout.put("RecyclingProcess",R.layout.recycling_list_item);
        Layout.put("FoodSamples",R.layout.food_list_item);
        Layout.put("AdditiveRegistration",R.layout.additive_list_item);
        Layout.put("OilRecovery",R.layout.oil_list_item);
        Layout.put("UltravioletDisinfection",R.layout.ultraviolet_list_item);

        Layout.put("Roster",R.layout.roster_list_item);
        Layout.put("MorningInspection",R.layout.morning_list_item);

        Layout.put("SafetyReport",R.layout.safety_list_item);
        Layout.put("RatingCriteria",R.layout.rating_list_item);
        Layout.put("CheckLog",R.layout.check_list_item);

        Layout.put("ReviewRecord",R.layout.review_list_item);


       // Layout.put("ReviewRecord",)

        RightLayout.put("TablewareDisinfection",R.layout.right_fragment_menu0);
        RightLayout.put("RecyclingProcess",R.layout.right_fragment_menu1);
        RightLayout.put("FoodSamples",R.layout.right_fragment_menu2);
        RightLayout.put("AdditiveRegistration",R.layout.right_fragment_menu3);
        RightLayout.put("OilRecovery",R.layout.right_fragment_menu4);
        RightLayout.put("UltravioletDisinfection",R.layout.right_fragment_menu5);
        RightLayout.put("Roster",R.layout.right_man_fragment);
        RightLayout.put("MorningInspection",R.layout.right_morning_fragment);

        RightLayout.put("SafetyReport",R.layout.safety_report_menu0);
        RightLayout.put("RatingCriteria",R.layout.rating_criteria_menu0);
        RightLayout.put("CheckLog",R.layout.check_log_menu0);

        RightLayout.put("ReviewRecord",R.layout.review_record_menu0);

        Class.put("TablewareDisinfection", TablewareView.class);
        Class.put("RecyclingProcess",RecyclingView.class);
        Class.put("FoodSamples",FoodView.class);
        Class.put("AdditiveRegistration",AdditiveView.class);
        Class.put("OilRecovery", OilView.class);
        Class.put("UltravioletDisinfection",UltravioletView.class);

        Class.put("Roster", RosterView.class);
        Class.put("MorningInspection", MorningInspectionView.class);

        Class.put("SafetyReport", SafetyView.class);
        Class.put("RatingCriteria", RatingCriteriaView.class);
        Class.put("CheckLog", CheckLogView.class);

        Class.put("ReviewRecord", ReviewRecordView.class);

        Entity.put("TablewareDisinfection", TablewareDisinfection.class);
        Entity.put("RecyclingProcess",RecyclingProcess.class);
        Entity.put("FoodSamples",FoodSamples.class);
        Entity.put("AdditiveRegistration",AdditiveRegistration.class);
        Entity.put("OilRecovery", OilRecovery.class);
        Entity.put("UltravioletDisinfection",UltravioletDisinfection.class);

        Entity.put("Roster",Roster.class);
        Entity.put("MorningInspection",MorningInspection.class);

        Entity.put("SafetyReport",SafetyReport.class);
        Entity.put("CheckLog",CheckLog.class);
        Entity.put("RatingCriteria",RatingCriteria.class);

        Entity.put("ReviewRecord",ReviewRecord.class);

        Entity.put("FeedBack",FeedBack.class);

        ArrStr.add(0,"TablewareDisinfection");
        ArrStr.add(1,"RecyclingProcess");
        ArrStr.add(2,"FoodSamples");
        ArrStr.add(3,"AdditiveRegistration");
        ArrStr.add(4,"OilRecovery");
        ArrStr.add(5,"UltravioletDisinfection");

        SafetyArrStr.add(0,"SafetyReport");
        SafetyArrStr.add(1,"RatingCriteria");
        SafetyArrStr.add(2,"CheckLog");

        ReviewArrStr.add(0,"ReviewRecord");

        Fragment.put("TablewareDisinfection",new TablewareDisinfectionFragment());
        Fragment.put("RecyclingProcess",new RecyclingProcessFragment());
        Fragment.put("FoodSamples",new FoodSamplesFragment());
        Fragment.put("AdditiveRegistration",new AdditiveRegistrationFragment());
        Fragment.put("OilRecovery",new OilRecoveryFragment());
        Fragment.put("UltravioletDisinfection",new UltravioletDisinfectionFragment());

        Fragment.put("SafetyReport",new SafetyReportFragment());
        Fragment.put("RatingCriteria",new RatingCriteriaFragment());
        Fragment.put("CheckLog",new CheckLogFragment());

        Fragment.put("ReviewRecord",new ReviewRecordFragment());

        DetailFragment.put("SafetyReport",new DetailFragment1());
        DetailFragment.put("RatingCriteria",new DetailFragment2());
        DetailFragment.put("CheckLog",new DetailFragment3());

    }


}
