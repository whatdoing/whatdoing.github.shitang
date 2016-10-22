package com.haocean.dinninghall.document;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.record.RecordIndex;

import cn.hugeterry.updatefun.UpdateFunGO;


/**
 * Created by haocean on 2016/9/25.
 */
public class DocumentActivity extends FragmentActivity implements FragmentTabHost.OnTabChangeListener  {

    /**
     * FragmentTabhost
     */
    private FragmentTabHost mTabHost;


    /**
     * 布局填充器
     *
     */
    private LayoutInflater mLayoutInflater;

    /**
     * Fragment数组界面
     *
     */
    private Class mFragmentArray[] = { RecordIndex.class, Fragment2.class,
            Fragment3.class };
    /**
     * 存放图片数组
     *
     */
    private int mImageArray[] = { R.drawable.record_select,
            R.drawable.function_select, R.drawable.more_select
           };

    /**
     * 选修卡文字
     *
     */
    private String mTextArray[] = { "过程记录", "功能列表", "更多" };
    /**
     *
     *
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化组件
     */
    private void initView() {



        mLayoutInflater = LayoutInflater.from(this);

        // 找到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setOnTabChangedListener(this);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec  tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
          //  mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.BLUE);//设置背景色
          //  mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.record_select);//设置背景图片

        }
        UpdateFunGO.init(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateFunGO.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGO.onStop(this);
    }
    /**
     *
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);

        return view;
    }


    /**
     *
     * Tab改变事件,去掉管理日志里的第二层页面
     */
    @Override
    public void onTabChanged(String s) {
        FragmentManager fragmentManager=  getSupportFragmentManager();
        Fragment fragment=  fragmentManager.findFragmentByTag("twoFragment");
        FragmentLoginTitle fragmentLoginTitle=  (FragmentLoginTitle)fragmentManager.findFragmentById(R.id.title);
        switch (s){
            case "记录表":
                fragment=  fragmentManager.findFragmentByTag("twoFragment");
                break;
            case "管理日志":
                 fragment =  fragmentManager.findFragmentByTag("twoFragment");
                break;
            case "设置":
                 fragment =  fragmentManager.findFragmentByTag("twoFragment");
                break;
        }
        if(fragment!=null){
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        if(fragmentLoginTitle!=null)
        {
            fragmentLoginTitle.hideBack();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager=getSupportFragmentManager();
      FragmentLoginTitle fragmentLoginTitle= (FragmentLoginTitle) fragmentManager.findFragmentById(R.id.title);
        fragmentLoginTitle.setData();
    }


}
