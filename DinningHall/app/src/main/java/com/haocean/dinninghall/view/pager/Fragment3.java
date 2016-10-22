package com.haocean.dinninghall.view.pager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.LoginActivity;
import com.haocean.dinninghall.R;


/**
 * Created by haocean on 2016/7/7.
 */
public class Fragment3 extends Fragment {


    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.viewpager_layout3, container, false);

        //对View中控件的操作方法
        Button btn = (Button)view.findViewById(R.id.fragment1_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//				Toast.makeText(getActivity(), "点击了第3个fragment的BTN", Toast.LENGTH_SHORT).show();
                intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
