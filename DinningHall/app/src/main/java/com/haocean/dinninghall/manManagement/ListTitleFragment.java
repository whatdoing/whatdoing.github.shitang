package com.haocean.dinninghall.manManagement;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.ManManagementList;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.record.RecordListActivity;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by haocean on 2016/7/7.
 */
public class ListTitleFragment extends Fragment implements View.OnClickListener{
    TextView  name;
    TextView  number;
    ManManagementIndexActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_man_management_item, container, false);//初始化fragment
        activity=((ManManagementIndexActivity)getActivity());
        LinearLayout  title= (LinearLayout) view.findViewById(R.id.title);
        title.setBackgroundResource(R.color.hui);
        name= (TextView) view.findViewById(R.id.name);

        name.setTextSize(18);
        number= (TextView) view.findViewById(R.id.number);
        number.setTextSize(18);
        setTitle();
       setCount();
        title.setOnClickListener(this);
        return view;
    }
    public void setTitle(){
        String typeMan;
        if(activity.getTypeMan()==null){
            typeMan= activity.getIntent().getStringExtra("typeMan");
        }else{
            typeMan=activity.getTypeMan();
        }
       // System.out.println("------------"+RecordList.RecordLists.get(typeMan));
        name.setText(ManManagementList.ManManagementLists.get(typeMan));
    }
    public void setCount(){
        try {
            JSONObject jsonObject=new JSONObject(UserData.getCount());

            number.setText("共"+jsonObject.getString(activity.getTypeMan())+"条");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.title: {
                activity.LeftSlidingMenuIsShow();
            }
            break;
        }

    }

}
