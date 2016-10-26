package com.haocean.dinninghall.record.CreateRecordFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;

/**
 * Created by haocean on 2016/10/8.
 */
public class OilRecoveryFragment extends Fragment implements View.OnClickListener{
    private CreateRecordActivity activity;
    private  Button startDate;
    private  Button endDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oil, container, false);//初始化fragment

        activity= (CreateRecordActivity)getActivity();
        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("OilRecovery", view, tempString);
        }
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

        }

    }
}
