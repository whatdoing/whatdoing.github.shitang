package com.haocean.dinninghall.setup.about;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haocean.dinninghall.R;

import cn.hugeterry.updatefun.UpdateFunGO;
import cn.hugeterry.updatefun.config.DownloadKey;
import cn.hugeterry.updatefun.utils.GetAppInfo;


/**
 * Created by haocean on 2016/7/7.
 */
public class AboutFragment extends Fragment implements View.OnClickListener{
private  Float fversion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);//初始化fragment
        LinearLayout feedback= (LinearLayout) view.findViewById(R.id.feedback);
        LinearLayout banbenLayout= (LinearLayout) view.findViewById(R.id.banbenLayout);
        TextView version= (TextView) view.findViewById(R.id.version);
        TextView banben= (TextView) view.findViewById(R.id.banben);
         fversion=Float.valueOf(GetAppInfo.getAppVersionName(getActivity()));
        version.setText("当前版本："+ fversion);
        if (Float.valueOf(DownloadKey.version)>fversion){
            banben.setText("存在新版本："+ DownloadKey.version);
        }
        else{
            banben.setText("当前为最新版本！");
        }
        //需要设置返回主页的图标
       banbenLayout.setOnClickListener(this);
        feedback.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.feedback: {
                FragmentManager fragmentManager=  getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_context, new feedbackFragment(),"feedbackFragment").commit();
            }
            break;
            case R.id.banbenLayout: {
                if (Float.valueOf(DownloadKey.version)>fversion){
                    UpdateFunGO.manualStart(getActivity());
                }
            }
            break;
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        UpdateFunGO.onResume(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        UpdateFunGO.onStop(getActivity());
    }
}
