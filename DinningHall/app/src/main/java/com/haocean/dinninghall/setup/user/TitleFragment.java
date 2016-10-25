package com.haocean.dinninghall.setup.user;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.document.DocumentActivity;
import com.haocean.dinninghall.record.CreateRecordActivity;


/**
 * Created by haocean on 2016/7/7.
 */
public class TitleFragment extends Fragment implements View.OnClickListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_title, container, false);//初始化fragment
        LinearLayout back= (LinearLayout) view.findViewById(R.id.back);
        ImageView search= (ImageView) view.findViewById(R.id.search);
        ImageView function= (ImageView) view.findViewById(R.id.function);
        search.setVisibility(View.GONE);
        function.setVisibility(View.GONE);
        //需要设置返回主页的图标
        //search.setOnClickListener(this);
        back.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.back: {
                Intent intent=new Intent(getActivity(),DocumentActivity.class);
                getActivity().setResult(-1, intent);
                getActivity().finish();
            }
            break;
        }

    }
}
