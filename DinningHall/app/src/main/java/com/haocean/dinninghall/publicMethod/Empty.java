package com.haocean.dinninghall.publicMethod;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.contexts.Contexts;
import com.haocean.dinninghall.contexts.MyDialogs;

import java.util.Map;
import java.util.Set;

/**
 * Created by haocean on 2016/9/25.
 */
public class Empty {
    //判断信息是否空
    public  static   boolean isToEmpty(Map<String, String> maps,Activity myActivity){
        Set keyset = maps.keySet();
        boolean isok=false;
        for(Object keyname:keyset){

            if(TextUtils.isEmpty(maps.get(keyname).toString())){
                String mymessages= Contexts.sendMessages.get(keyname.toString());
                MyDialogs.isAlertDialog(mymessages, myActivity);
                isok=true;
                break;
            }
        }
        return isok;
    }
}
