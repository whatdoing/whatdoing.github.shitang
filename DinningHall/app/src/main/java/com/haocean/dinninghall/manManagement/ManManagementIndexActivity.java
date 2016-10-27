package com.haocean.dinninghall.manManagement;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.RecordRunnable;
import com.haocean.dinninghall.adapter.ListViewAdapter;
import com.haocean.dinninghall.manManagement.leftOrRightFragment.LeftFragment;
import com.haocean.dinninghall.manManagement.leftOrRightFragment.RightFragment;
import com.haocean.dinninghall.record.CreateRecordActivity;


import java.util.HashMap;
import java.util.Map;

import lib.SlidingMenu;
import lib.app.SlidingFragmentActivity;

/**
 * Created by haocean on 2016/10/8.
 */
public class ManManagementIndexActivity  extends SlidingFragmentActivity implements LeftFragment.onItemListener,RightFragment.onButtonListener, com.haocean.dinninghall.manManagement.TitleFragment.onCreateButton, ListViewAdapter.onEditButton {

    FragmentManager fragmentManager;
    private SlidingMenu _SlidingMenu; // 侧边栏菜单
    private LeftFragment _LeftFragment; // 左侧菜单Fragment
    private RightFragment _RightFragment; // 右侧菜单Fragment

    private String typeMan;
    private TitleFragment titleFragment;
    private ListTitleFragment listTitleFragment;
    private BottomListFragment bottomListFragment;
    Map<String,String> data=new HashMap<String, String>();
    private void init() {
        Intent intent = getIntent();
        typeMan = intent.getStringExtra("typeRecord");

    }
    //获得类型
    //获取列表内容
    private void Datarunnable(Map<String,String> data){
        RecordRunnable recordRunnable=new RecordRunnable();
        recordRunnable.createHand(ManManagementIndexActivity.this,typeMan,data);
        Thread dataThread=new Thread(recordRunnable);
        dataThread.start();
    }
    public void updataButtomList(String manData){
        System.out.println("-----Mandata-----"+manData);

        if(manData==null){
            bottomListFragment.updata(manData,2);
        }else if(manData.trim().equals("[]")){
            bottomListFragment.updata(manData,1);
        }else{
            bottomListFragment.updata(manData,0);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.list_activity);
        initSlidingMenu();
        init();//加载
        Datarunnable(data);
    }

    /*
  * 初始化右侧搜索菜单
  * */
    private void initRightMenu(SlidingMenu sideingMenu){
        _RightFragment = new RightFragment();// 创建右边菜单Fragment
        sideingMenu.setSecondaryMenu(R.layout.right_menu_layout); // 设置右菜单默认VIEW
        sideingMenu.setSecondaryShadowDrawable(R.drawable.shadowright); // 设置右菜单阴影
        sideingMenu.setRightBehindWidthRes(R.dimen.right_menu_width); // 设置右菜单的宽度,该值为右菜单展开的宽度
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rightMenu, _RightFragment).commit(); // 将右菜单默认VIEW替换为右菜单Fragment
    }

    /**
     * 初始化侧边栏菜单
     */
    private void initSlidingMenu() {

        titleFragment =new TitleFragment();
        listTitleFragment =new ListTitleFragment();
        bottomListFragment =new BottomListFragment();
        fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_title, titleFragment,"titleFragment").commit();
        fragmentManager.beginTransaction().add(R.id.fragment_context, listTitleFragment,"listTitleFragment").commit();
        fragmentManager.beginTransaction().add(R.id.fragment_bottom, bottomListFragment,"buttomListFragment").commit();
//        listTitleFragment =(ListTitleFragment) this.getFragmentManager().findFragmentById(R.id.titecontet);
        _LeftFragment = new LeftFragment(); // 创建左边菜单Fragment


        _SlidingMenu = getSlidingMenu(); // 由于Activity继承自SlidingFragmentActivity,所以直接获取当前的侧边栏菜单

        _SlidingMenu.setMode(SlidingMenu.LEFT_RIGHT); // 设置侧边栏菜单为左右模式
        _SlidingMenu.setBehindWidthRes(R.dimen.left_menu_width); // 设置左边菜单的宽度,该值为左菜单展开的宽度
        _SlidingMenu.setShadowDrawable(R.drawable.shadow); // 设置左菜单的阴影
        _SlidingMenu.setShadowWidth(10); // 设置阴影宽度
        _SlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); // 设置侧边栏菜单触摸模式为全屏模式

        setBehindContentView(R.layout.left_menu_layout); // 设置左菜单的默认VIEW
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.leftMenu, _LeftFragment).commit(); // 将左菜单默认VIEW替换为左菜单Fragment

        initRightMenu(_SlidingMenu);

    }

    //获得类型
    public String getTypeMan() {
        System.out.println("*-*-谁需要我*-*-");
        return typeMan;
    }


    @Override
    public void onOKItem(String typeMan) {
        this.typeMan = typeMan;
        LeftSlidingMenuIsShow();
        listTitleFragment.setTitle();
        //数据的加载
        System.out.println("typeMan" + typeMan);
        Datarunnable(data);
        //右侧搜索界面
        initRightMenu(_SlidingMenu);

    }

    public void LeftSlidingMenuIsShow() {
        if (!_SlidingMenu.isMenuShowing()) {
            _SlidingMenu.showMenu();
        } else {
            _SlidingMenu.showContent();
        }
    }

    public void RightSlidingMenuIsShow() {
        if (!_SlidingMenu.isSecondaryMenuShowing()) {
            _SlidingMenu.showSecondaryMenu();
        } else {
            _SlidingMenu.showContent();
        }
    }
    public void dataChanged(){
        bottomListFragment.dataChanged();
    }
    public void setCount(){
        listTitleFragment.Run();
    }
    @Override
    public void onBackListener(String data) {
        //清空搜索条件
        System.out.println("data" + data);
    }

    @Override
    public void onOKListener(Map<String, String> data) {
        RightSlidingMenuIsShow();
        System.out.println("data-----man-----" + data);
        Datarunnable(data);
        //缺少一个数据的加载
        //titleFragment的一个数据统计
        //buttomListFragment的一个数据列表
    }

    @Override
    public void CreateRecord() {
        System.out.println("从这里进的吗");
        Intent intent=new Intent(ManManagementIndexActivity.this,CreateManActivity.class);
        intent.putExtra("typeRecord",ManManagementIndexActivity.this.getTypeMan());
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent d) {
        System.out.println(requestCode);
        System.out.println("resultCode"+resultCode);
        System.out.println("怎么会没反应呢？");
        if(requestCode==1){
            switch (resultCode)
            {
                case 2:
                case 3:
                    Datarunnable(data);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, d);

    }
    @Override
    public void EditButton(Map<String, String> map) {
        Intent   intent=new Intent(this,CreateManActivity.class);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            intent.putExtra(entry.getKey(),entry.getValue());
        }

        startActivityForResult(intent, 1);
    }
}