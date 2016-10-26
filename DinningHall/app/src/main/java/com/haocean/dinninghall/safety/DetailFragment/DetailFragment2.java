package com.haocean.dinninghall.safety.DetailFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.safety.CreateSafetyActivity;
import com.haocean.dinninghall.safety.DetailActivity;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class DetailFragment2 extends Fragment {
    private DetailActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail2, container, false);//初始化fragment

        activity= (DetailActivity)getActivity();

        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.detail("RatingCriteria", view, tempString);

        }

        return view;
    }
}
