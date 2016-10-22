package com.haocean.dinninghall.contexts;

import com.haocean.dinninghall.manManagement.ManManagementIndexActivity;
import com.haocean.dinninghall.record.RecordIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haocean on 2016/9/25.
 */
public class Application {
    public final static List<Class> ApplicationList=new ArrayList<Class>();

    static {
        ApplicationList.add(0,ManManagementIndexActivity.class);
        ApplicationList.add(1,RecordIndex.class);


    }


}
