package com.haocean.dinninghall.review.CreateSafetyFragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.review.CreateReviewActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class ReviewRecordFragment extends Fragment implements View.OnClickListener{
    private CreateReviewActivity activity;
    private Button find_date,review_time,liable_date;
private LinearLayout zgyj,zgqx,fcyj,fcsj;
  int type ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);//初始化fragment
        activity= (CreateReviewActivity)getActivity();
        find_date= (Button) view.findViewById(R.id.find_date);
        review_time= (Button) view.findViewById(R.id.review_time);
        liable_date= (Button) view.findViewById(R.id.liable_date);
        zgyj=(LinearLayout)view.findViewById(R.id.zgyj);
        zgqx=(LinearLayout)view.findViewById(R.id.zgqx);
        fcyj=(LinearLayout)view.findViewById(R.id.fcyj);
        fcsj=(LinearLayout)view.findViewById(R.id.fcsj);

        find_date.setOnClickListener(this);
        review_time.setOnClickListener(this);
        liable_date.setOnClickListener(this);

        String tempString=activity.getTempString();
        if(tempString!=null) {
            //三种情况：编辑   整改   复查

            type=activity.getNum();
            System.out.println("---------"+type);
            if(type==0){
                fcsj.setVisibility(View.GONE);
                fcyj.setVisibility(View.GONE);

            }
            else if(type==1){

            }
            else{
                //编辑
                try {
                    JSONObject jsonObject=new JSONObject(tempString);
                    if(jsonObject.getString("rectification_opinions").equals("")){
                        zgqx.setVisibility(View.GONE);
                        zgyj.setVisibility(View.GONE);
                    }
                    if(jsonObject.getString("review_results").equals("")){
                        fcsj.setVisibility(View.GONE);
                        fcyj.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            Object object = ValueUtils.getValue("ReviewRecord", view, tempString);
        }else{
            //为空说明是新建
            zgqx.setVisibility(View.GONE);
            zgyj.setVisibility(View.GONE);
            fcsj.setVisibility(View.GONE);
            fcyj.setVisibility(View.GONE);
        }


        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.find_date: {
                MyDialogs.cereateDateDialog(find_date, activity);
            }
            break;
            case R.id.review_time: {
                MyDialogs.cereateDateDialog(review_time, activity);
            }
            break;
            case R.id.liable_date:{
                MyDialogs.cereateDateDialog(liable_date, activity);
            }
            break;
        }
    }
}
