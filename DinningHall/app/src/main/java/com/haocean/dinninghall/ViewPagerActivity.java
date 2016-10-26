package com.haocean.dinninghall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;


import com.haocean.dinninghall.adapter.FragAdapter;
import com.haocean.dinninghall.view.pager.Fragment1;
import com.haocean.dinninghall.view.pager.Fragment2;
import com.haocean.dinninghall.view.pager.Fragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haocean on 2016/7/7.
 */
public class ViewPagerActivity extends FragmentActivity {


    private ViewPager vp;
    //构造适配器,加入各种View
    List<Fragment> fragments = new ArrayList<Fragment>();

    public void fvid(){
        vp = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_pager);
        fvid();
        //加入fragment页面
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());

        //设定适配器
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);


    }//onCreate


}
