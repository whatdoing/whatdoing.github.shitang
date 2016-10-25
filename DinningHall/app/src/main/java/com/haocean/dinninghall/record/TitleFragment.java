package com.haocean.dinninghall.record;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haocean.dinninghall.R;


/**
 * Created by haocean on 2016/7/7.
 */
public class TitleFragment extends Fragment implements View.OnClickListener{
    RecordListActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_title, container, false);//初始化fragment
        activity= (RecordListActivity)getActivity();

        LinearLayout back= (LinearLayout) view.findViewById(R.id.back);
        ImageView search= (ImageView) view.findViewById(R.id.search);
        ImageView function= (ImageView) view.findViewById(R.id.function);
        search.setOnClickListener(this);
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
            case R.id.search: {
                activity.RightSlidingMenuIsShow();
            }
            break;
            case R.id.function: {
                Intent intent=new Intent(getActivity(),CreateRecordActivity.class);
                intent.putExtra("typeRecord",activity.getTypeRecord());
                startActivity(intent);
            }
            break;
        }

    }
}
