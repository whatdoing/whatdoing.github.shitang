package com.haocean.dinninghall.contexts;

import android.app.Fragment;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.entity.record.AdditiveRegistration;
import com.haocean.dinninghall.entity.record.FoodSamples;
import com.haocean.dinninghall.entity.record.OilRecovery;
import com.haocean.dinninghall.entity.record.RecyclingProcess;
import com.haocean.dinninghall.entity.record.TablewareDisinfection;
import com.haocean.dinninghall.entity.record.UltravioletDisinfection;
import com.haocean.dinninghall.manManagement.CreateManFragment.RosterFragment;
import com.haocean.dinninghall.manManagement.CreateManFragment.MorningInspectionFragment;
import com.haocean.dinninghall.manManagement.ManManagementIndexActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haocean on 2016/9/25.
 */
public class ManManagementList {
    public final static Map<String,Class> ManManagementClass=new HashMap<String,Class>();
    public final static Map<String,Class> sixRecordEntity=new HashMap<String,Class>();
    public final static Map<String, Integer> sixRecordLayout=new HashMap<String, Integer>();
    public final static Map<String, Fragment> ManFragment=new HashMap<String, Fragment>();
    public final static Map<String,String> ManManagementLists=new HashMap<String,String>();
    public final static ArrayList<String>  ManManagementStr=new ArrayList<String>();
    static {
        ManFragment.put("Roster",new RosterFragment());
        ManFragment.put("MorningInspection",new MorningInspectionFragment());

        ManManagementLists.put("Roster","各岗位工作人员整改信息");
        ManManagementLists.put("MorningInspection","餐饮从业人员晨检表");


        sixRecordLayout.put("TablewareDisinfection",R.layout.tableware_list_item);
        sixRecordLayout.put("RecyclingProcess",R.layout.recycling_list_item);
        sixRecordLayout.put("FoodSamples",R.layout.food_list_item);
        sixRecordLayout.put("AdditiveRegistration",R.layout.additive_list_item);
        sixRecordLayout.put("OilRecovery",R.layout.oil_list_item);
        sixRecordLayout.put("UltravioletDisinfection",R.layout.ultraviolet_list_item);



        ManManagementClass.put("MorningInspection",ManManagementIndexActivity.class);


        sixRecordEntity.put("TablewareDisinfection", TablewareDisinfection.class);
        sixRecordEntity.put("RecyclingProcess",RecyclingProcess.class);
        sixRecordEntity.put("FoodSamples",FoodSamples.class);
        sixRecordEntity.put("AdditiveRegistration",AdditiveRegistration.class);
        sixRecordEntity.put("OilRecovery", OilRecovery.class);
        sixRecordEntity.put("UltravioletDisinfection",UltravioletDisinfection.class);

        ManManagementStr.add(0,"Roster");
        ManManagementStr.add(1,"MorningInspection");


    }


}
