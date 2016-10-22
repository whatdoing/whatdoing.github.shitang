package com.haocean.dinninghall.safety.CreateSafetyFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.safety.CreateSafetyActivity;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class RatingCriteriaFragment extends Fragment implements View.OnClickListener {
    private CreateSafetyActivity activity;
    private Button inspect_date,assessment_level;
    String[] level={"优秀","良好","一般","整改"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);//初始化fragment

        activity= (CreateSafetyActivity)getActivity();
        inspect_date=(Button)view.findViewById(R.id.inspect_date);
        assessment_level=(Button)view.findViewById(R.id.assessment_level);
        inspect_date.setOnClickListener(this);
        assessment_level.setOnClickListener(this);
        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("RatingCriteria", view, tempString);

        }

        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.inspect_date: {
                MyDialogs.cereateDateDialog(inspect_date, activity);
            }
            break;
            case R.id.assessment_level: {
                MyDialogs.cereateDialog(inspect_date,level, activity);
            }
            break;
        }
    }
}
