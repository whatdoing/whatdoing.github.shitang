package com.haocean.dinninghall.record.CreateRecordFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.ListRunnable;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.DataList;
import com.haocean.dinninghall.record.utils.ValueUtils;

/**
 * Created by haocean on 2016/10/8.
 */
public class AdditiveRegistrationFragment extends Fragment implements View.OnClickListener{
    private CreateRecordActivity activity;
    private  Button purchase_date,name,manufacturer,manufacture_date,quality,supplyunit,purchasenum,userecord;
    String[] userecords={"采购","验收","使用","销毁"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additive, container, false);//初始化fragment

        activity= (CreateRecordActivity)getActivity();
        Run();
        purchase_date= (Button) view.findViewById(R.id.purchase_date);
        manufacture_date= (Button) view.findViewById(R.id.manufacture_date);
        name= (Button) view.findViewById(R.id.name);
        manufacturer= (Button) view.findViewById(R.id.manufacturer);
        quality= (Button) view.findViewById(R.id.quality);
        supplyunit= (Button) view.findViewById(R.id.supplyunit);
        purchasenum= (Button) view.findViewById(R.id.purchasenum);
        userecord= (Button) view.findViewById(R.id.userecord);

        purchase_date.setOnClickListener(this);
        manufacture_date.setOnClickListener(this);

        name.setOnClickListener(this);
        manufacturer.setOnClickListener(this);
        quality.setOnClickListener(this);
        supplyunit.setOnClickListener(this);
        purchasenum.setOnClickListener(this);
        userecord.setOnClickListener(this);
        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("AdditiveRegistration", view, tempString);
        }
        return view;
    }

    public void Run(){
        ListRunnable listRunnable=new ListRunnable(activity);
        listRunnable.setTypeMan(activity.typeRecord);
        Thread thread=new Thread(listRunnable);
        thread.start();
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.purchase_date: {
                String list= DataList.purchase_date;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(purchase_date,arr,activity);
            }
            break;
            case R.id.manufacture_date: {
                String list= DataList.manufacture_date;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(manufacture_date,arr,activity);
            }
            break;
            case R.id.name: {
                String list= DataList.name;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(name,arr,activity);
            }
            break;
            case R.id.manufacturer: {
                String list= DataList.manufacturer;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(manufacturer,arr,activity);
            }
            break;
            case R.id.quality: {
                String list= DataList.quality;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(quality,arr,activity);
            }
            break;
            case R.id.supplyunit: {
                String list= DataList.supplyunit;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(supplyunit,arr,activity);
            }
            break;
            case R.id.purchasenum: {
                String list= DataList.purchasenum;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(purchasenum,arr,activity);
            }
            break;
            case R.id.userecord: {
                MyDialogs.cereateDialog(userecord,userecords,activity);
            }
            break;
        }

    }
}
