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
import com.haocean.dinninghall.Runnable.CountRunnable;
import com.haocean.dinninghall.adapter.MyGridAdapter;
import com.haocean.dinninghall.contexts.Application;
import com.haocean.dinninghall.manManagement.ManIndexFrament;
import com.haocean.dinninghall.manManagement.ManManagementIndexActivity;
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.record.CreateTitleFragment;
import com.haocean.dinninghall.record.RecordIndex;
import com.haocean.dinninghall.record.leftOrRightFragment.LeftFragment;
import com.haocean.dinninghall.review.ReviewIndex;
import com.haocean.dinninghall.safety.SafetyIndex;
import com.haocean.dinninghall.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by haocean on 2016/7/7.
 */
public class Fragment2 extends Fragment {
    private LinearLayout recordList;
    private DocumentActivity activity;
    private MyGridAdapter myGridAdapter;
    public int[] imgs = { R.mipmap.man_manger, R.mipmap.record,
            R.mipmap.app_phonecharge, R.mipmap.app_creditcard,
            R.mipmap.app_movie, R.mipmap.app_lottery,
            R.mipmap.app_facepay, R.mipmap.app_close, R.mipmap.app_plane };
    private String[] img_text = { "rygl", "gcjl", "glrz", "yhbg"};
    private List<Integer>  _imgs=new ArrayList<Integer>();
    private Fragment[] Fragments = { new  ManIndexFrament(), new  RecordIndex(), new SafetyIndex(), new ReviewIndex()};
    private List<Fragment> listFragment = new ArrayList<Fragment>();
//管理日志
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);//初始化fragment
        activity = (DocumentActivity)getActivity();

        recordList = (LinearLayout) view.findViewById(R.id.recordList);//要添加内容的布局recordList

        View recordview=inflater.inflate(R.layout.grid_view, null);
        MyGridView gridview= (MyGridView) recordview.findViewById(R.id.gridview);
        List<String> _text=quanxian();
        myGridAdapter=new MyGridAdapter(AppController.getInstance(),_text,_imgs);

        gridview.setAdapter(myGridAdapter);

        gridview.setOnItemClickListener(new GridviewListener());
        recordList.addView(recordview);

        return view;
    }
    private List<String> quanxian(){
         List<String> _text=new ArrayList<String>();
        try {
            for(int i=0;i<img_text.length;i++){
                if(UserData.getJurisdiction().contains(img_text[i])){
                    int 	strid=  R.string.class.getField(img_text[i]).getInt(null);
                    _text.add(getString(strid));
                    _imgs.add(imgs[i]);
                    listFragment.add(Fragments[i]);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return _text;
    }
    private class GridviewListener implements GridView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if(activity instanceof DocumentActivity) {
                System.out.println("map"+   adapterView.getTag(i));
            }
            FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
            Fragment fragment = null;
            fragment= listFragment.get(i);
            if(fragment!=null){
                fragmentManager.beginTransaction().add(R.id.realtabcontent, fragment,"twoFragment").commit();
            }
            FragmentLoginTitle fragmentTitle=  (FragmentLoginTitle)fragmentManager.findFragmentById(R.id.title);
            fragmentTitle.showBack();


        }
    }
}
