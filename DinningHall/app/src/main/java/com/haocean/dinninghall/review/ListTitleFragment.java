package com.haocean.dinninghall.review;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.safety.SafetyListActivity;


/**
 * Created by haocean on 2016/7/7.
 */
public class ListTitleFragment extends Fragment implements View.OnClickListener{
    TextView  recordname;
    ReviewListActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_record_item, container, false);//初始化fragment
        activity=((ReviewListActivity)getActivity());
        System.out.println("---LIST TITLE---");
        LinearLayout  title= (LinearLayout) view.findViewById(R.id.title);
        title.setBackgroundResource(R.color.hui);
        recordname= (TextView) view.findViewById(R.id.recordname);
        recordname.setTextSize(18);
        setTitle();
        ImageView isyinye= (ImageView) view.findViewById(R.id.isyinye);
        isyinye.setVisibility(View.GONE);
        title.setOnClickListener(this);
        return view;
    }
    public void setTitle(){
        String typeRecord;
        if(activity.getTypeRecord()==null){
            typeRecord= activity.getIntent().getStringExtra("typeRecord");
        }else{
            typeRecord=activity.getTypeRecord();
        }
        System.out.println("------list title  safe------"+RecordList.ReviewLists.get(typeRecord));
        recordname.setText(RecordList.ReviewLists.get(typeRecord));
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.title: {
                ((ReviewListActivity)getActivity()).LeftSlidingMenuIsShow();
            }
            break;
        }

    }

}
