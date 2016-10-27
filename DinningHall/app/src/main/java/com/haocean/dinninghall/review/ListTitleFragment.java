package com.haocean.dinninghall.review;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CountRunnable;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.safety.SafetyListActivity;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by haocean on 2016/7/7.
 */
public class ListTitleFragment extends Fragment implements View.OnClickListener{
    TextView  recordname,Mnumber,Ynumber;
    ReviewListActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_record_item, container, false);//初始化fragment
        activity=((ReviewListActivity)getActivity());
        LinearLayout  title= (LinearLayout) view.findViewById(R.id.title);
        title.setBackgroundResource(R.color.hui);
        recordname= (TextView) view.findViewById(R.id.recordname);
        Mnumber= (TextView) view.findViewById(R.id.Mnumber);
        Ynumber= (TextView) view.findViewById(R.id.Ynumber);
        recordname.setTextSize(18);
        setTitle();
        setCount();
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
    public void setCount(){
        try {
            JSONObject jsonObject=new JSONObject(UserData.getCount());
            String strType=jsonObject.getString(activity.getTypeRecord());

            JSONObject data = new JSONObject(strType);

            Mnumber.setText(data.getString("Mnumber"));
            Ynumber.setText(data.getString("Ynumber"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void Run(){
        CountRunnable countRunnable=new CountRunnable(handlist,"countSafe");
        Thread dataThread=new Thread(countRunnable);
        dataThread.start();
    }
    Handler handlist = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            switch(msg.what){
                case 0:
                    setCount();
                    break;
                case 1:
//                    Toast.makeText(getActivity(), "系统维护数据无法查询到，请稍后重试！", Toast.LENGTH_SHORT).show();
                    break;
                case 2:

//                    Toast.makeText(getActivity(), "无查询数据", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
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
