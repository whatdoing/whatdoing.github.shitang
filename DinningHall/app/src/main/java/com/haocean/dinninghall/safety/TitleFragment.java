package com.haocean.dinninghall.safety;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.RecordIndex;
import com.haocean.dinninghall.record.RecordListActivity;


/**
 * Created by haocean on 2016/7/7.
 */
public class TitleFragment extends Fragment implements View.OnClickListener{
    SafetyListActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_title, container, false);//初始化fragment
        activity= (SafetyListActivity)getActivity();
System.out.println("----TITLE----");
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
                Intent intent = new Intent();
                intent.setClass(activity, SafetyIndex.class);
                activity.setResult(2, intent);
                activity.finish();
            }
             break;
            case R.id.search: {
                activity.RightSlidingMenuIsShow();
            }
            break;
            case R.id.function: {
//                Intent intent=new Intent(getActivity(),CreateSafetyActivity.class);
//                intent.putExtra("typeRecord",activity.getTypeRecord());
//                startActivity(intent);

                onCreateButton CreateButton=(onCreateButton)activity;
                System.out.println("来了吗");
                CreateButton.CreateRecord();
            }
            break;
        }

    }
    public interface onCreateButton {
        void CreateRecord();
    }
}
