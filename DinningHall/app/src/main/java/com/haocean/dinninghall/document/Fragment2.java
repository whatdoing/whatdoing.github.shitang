package com.haocean.dinninghall.document;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.adapter.MyGridAdapter;
import com.haocean.dinninghall.contexts.Application;
import com.haocean.dinninghall.manManagement.ManIndexFrament;
import com.haocean.dinninghall.record.CreateTitleFragment;
import com.haocean.dinninghall.record.RecordIndex;
import com.haocean.dinninghall.record.leftOrRightFragment.LeftFragment;
import com.haocean.dinninghall.review.ReviewIndex;
import com.haocean.dinninghall.safety.SafetyIndex;
import com.haocean.dinninghall.view.MyGridView;


/**
 * Created by haocean on 2016/7/7.
 */
public class Fragment2 extends Fragment {
    private LinearLayout recordList;
    private DocumentActivity activity;
//管理日志
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);//初始化fragment
        activity = (DocumentActivity)getActivity();

        recordList = (LinearLayout) view.findViewById(R.id.recordList);//要添加内容的布局recordList

        View recordview=inflater.inflate(R.layout.grid_view, null);
        MyGridView gridview= (MyGridView) recordview.findViewById(R.id.gridview);


        gridview.setAdapter(new MyGridAdapter(AppController.getInstance()));

        gridview.setOnItemClickListener(new GridviewListener());
        recordList.addView(recordview);

        return view;
    }
    private class GridviewListener implements GridView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
            Fragment fragment = null;
            switch (i){
                case 0:
                    fragment=new  ManIndexFrament();
                    break;
                case 1:
                    fragment=new  RecordIndex();
                    break;
                case 2:
                    fragment=new SafetyIndex();
                    break;
                case 3:
                    fragment=new ReviewIndex();
                    break;
            default:

                break;
            }
            if(fragment!=null){
                fragmentManager.beginTransaction().add(R.id.realtabcontent, fragment,"twoFragment").commit();
            }
            FragmentLoginTitle fragmentTitle=  (FragmentLoginTitle)fragmentManager.findFragmentById(R.id.title);
            fragmentTitle.showBack();


        }
    }
}
