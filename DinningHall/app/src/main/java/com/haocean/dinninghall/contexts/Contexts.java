package com.haocean.dinninghall.contexts;

import android.app.Fragment;

import com.haocean.dinninghall.entity.record.AdditiveRegistration;
import com.haocean.dinninghall.entity.record.FoodSamples;
import com.haocean.dinninghall.entity.record.MorningInspection;
import com.haocean.dinninghall.entity.record.OilRecovery;
import com.haocean.dinninghall.entity.record.RecyclingProcess;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haocean on 2016/9/25.
 */
public class Contexts {


    public final static Map<String,String> sendMessages=new HashMap<String,String>();
    public final static Map<String,Class> Entity=new HashMap<String,Class>();
    public final static Map<String,Fragment> fragment=new HashMap<String,Fragment>();
    static {
        sendMessages.put("username","用户名为空,请输入用户名！");
        sendMessages.put("password","密码为空，请输入密码！");


        Entity.put("TablewareDisinfection", TablewareDisinfection.class);
        Entity.put("RecyclingProcess",RecyclingProcess.class);
        Entity.put("FoodSamples",FoodSamples.class);
        Entity.put("AdditiveRegistration",AdditiveRegistration.class);
        Entity.put("OilRecovery", OilRecovery.class);
        Entity.put("UltravioletDisinfection",UltravioletDisinfection.class);

        Entity.put("Roster",Roster.class);
        Entity.put("MorningInspection",MorningInspection.class);

        Entity.put("SafetyReport",SafetyReport.class);
        Entity.put("CheckLog",SafetyReport.class);
        Entity.put("RatingCriteria",SafetyReport.class);

    }



}
