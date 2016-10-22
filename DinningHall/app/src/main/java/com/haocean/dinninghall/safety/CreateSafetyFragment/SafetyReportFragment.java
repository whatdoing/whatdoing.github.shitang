package com.haocean.dinninghall.safety.CreateSafetyFragment;

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
import com.haocean.dinninghall.safety.CreateSafetyActivity;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class SafetyReportFragment extends Fragment implements View.OnClickListener{
    private CreateSafetyActivity activity;
private Button report_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_safety, container, false);//初始化fragment

        activity= (CreateSafetyActivity)getActivity();
        report_date=(Button)view.findViewById(R.id.report_date);
        report_date.setOnClickListener(this);
        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("SafetyReport", view, tempString);

        }

        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.report_date: {
                MyDialogs.cereateDateDialog(report_date, activity);
            }
            break;
        }
    }
}
