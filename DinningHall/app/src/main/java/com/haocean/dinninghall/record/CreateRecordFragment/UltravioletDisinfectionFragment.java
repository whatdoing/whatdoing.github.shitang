package com.haocean.dinninghall.record.CreateRecordFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;

/**
 * Created by haocean on 2016/10/8.
 */
public class UltravioletDisinfectionFragment extends Fragment implements View.OnClickListener{
    private CreateRecordActivity activity;
    private Button start_date,end_date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ultraviolet, container, false);//初始化fragment

        activity= (CreateRecordActivity)getActivity();
        start_date= (Button) view.findViewById(R.id.start_date);
        end_date= (Button) view.findViewById(R.id.end_date);


        start_date.setOnClickListener(this);
        end_date.setOnClickListener(this);
        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("UltravioletDisinfection", view, tempString);

        }

        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.start_date: {
                MyDialogs.cereateDateDialog(start_date, activity);
            }
            break;
            case R.id.end_date: {
                MyDialogs.cereateDateDialog(end_date, activity);
            }
            break;
        }
    }
}
