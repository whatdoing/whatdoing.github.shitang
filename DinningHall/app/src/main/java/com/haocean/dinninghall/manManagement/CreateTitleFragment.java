package com.haocean.dinninghall.manManagement;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.record.CreateRecordActivity;


/**
 * Created by haocean on 2016/7/7.
 */
public class CreateTitleFragment extends Fragment implements View.OnClickListener{
    CreateManActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_title, container, false);//初始化fragment
        activity= (CreateManActivity)getActivity();
        LinearLayout back= (LinearLayout) view.findViewById(R.id.back);
        ImageView search= (ImageView) view.findViewById(R.id.search);
        search.setVisibility(View.GONE);
        ImageView function= (ImageView) view.findViewById(R.id.function);
        //需要设置返回主页的图标
        //search.setOnClickListener(this);
        back.setOnClickListener(this);
        function.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.back: {
                activity.finish();
            }
             break;
            case R.id.function: {
               //执行一个返回首页的操作
            }
            break;
        }

    }
}
