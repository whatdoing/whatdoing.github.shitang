package com.haocean.dinninghall.setup.about;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;


/**
 * Created by haocean on 2016/7/7.
 */
public class feedbackFragmentOk extends Fragment implements View.OnClickListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_ok, container, false);//初始化fragment
        Button back= (Button) view.findViewById(R.id.back);
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
                FragmentManager fragmentManager=  getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_context, new AboutFragment(),"AboutFragment").commit();
            }
            break;
        }

    }
}
