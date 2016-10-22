package com.haocean.dinninghall.document;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnDismissListener;
import com.bigkoo.alertview.OnItemClickListener;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.setup.about.AboutActivity;
import com.haocean.dinninghall.setup.contacts.ContactsList;
import com.haocean.dinninghall.setup.user.UserActivity;

import cn.hugeterry.updatefun.UpdateFunGO;
import cn.hugeterry.updatefun.config.DownloadKey;
import cn.hugeterry.updatefun.module.Download;
import cn.hugeterry.updatefun.utils.GetAppInfo;


/**
 * Created by haocean on 2016/7/7.
 */
public class Fragment3 extends Fragment implements View.OnClickListener {
    private DocumentActivity activity;
    private LinearLayout geren,about,revise,contacts,update;
    private TextView updatetext;
    private Float version;
//设置
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_setup, null);
        activity = (DocumentActivity)getActivity();
        geren= (LinearLayout) view.findViewById(R.id.geren);
        updatetext= (TextView) view.findViewById(R.id.updatetext);
        about= (LinearLayout) view.findViewById(R.id.about);
        revise= (LinearLayout) view.findViewById(R.id.revise);
        contacts= (LinearLayout) view.findViewById(R.id.contacts);
        update= (LinearLayout) view.findViewById(R.id.update);
        version=Float.valueOf( GetAppInfo.getAppVersionName(activity));
        System.out.println(version);
        if (Float.valueOf(DownloadKey.version)>version){
            updatetext.setText("存在新版本："+ DownloadKey.version);
        }
         else{
            updatetext.setText("当前为最新版本！");
        }
        geren.setOnClickListener(this);
        about.setOnClickListener(this);
        revise.setOnClickListener(this);
        contacts.setOnClickListener(this);
        update.setOnClickListener(this);

        return view;


    }



    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.geren:
                Intent geren=new Intent(getActivity(), UserActivity.class);
                System.out.println("进入个人信息");
                startActivityForResult(geren, 2);
                break;
            case R.id.about:
                Intent about=new Intent(activity, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.contacts:
                Intent intent=new Intent(activity,ContactsList.class);

                startActivity(intent);
                break;
            case R.id.update:
                if (Float.valueOf(DownloadKey.version)>version){
                    UpdateFunGO.manualStart(activity);
                }
                break;

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        UpdateFunGO.onResume(activity);
    }

    @Override
    public void onStop() {
        super.onStop();
        UpdateFunGO.onStop(activity);
    }

}
