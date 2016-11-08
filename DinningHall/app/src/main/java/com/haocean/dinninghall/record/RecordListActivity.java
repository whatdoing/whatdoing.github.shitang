package com.haocean.dinninghall.record;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.RecordRunnable;

import com.haocean.dinninghall.adapter.ListViewAdapter;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.leftOrRightFragment.LeftFragment;
import com.haocean.dinninghall.record.leftOrRightFragment.RightFragment;
import com.haocean.dinninghall.record.utils.ValueUtils;

import java.util.HashMap;
import java.util.Map;

import lib.SlidingMenu;
import lib.app.SlidingFragmentActivity;

/**
 * Created by haocean on 2016/9/26.
 */
public class RecordListActivity  extends SlidingFragmentActivity implements LeftFragment.onItemListener,TitleFragment.onCreateButton, ListViewAdapter.onEditButton{
    FragmentManager fragmentManager;
    private SlidingMenu _SlidingMenu; // 侧边栏菜单
    private LeftFragment _LeftFragment; // 左侧菜单Fragment
    private Fragment _RightFragment; // 右侧菜单Fragment
    private TitleFragment titleFragment;
    private ListTitleFragment listTitleFragment;
    private String typeRecord;
    private BottomListFragment bottomListFragment;
    Map<String,String> data=new HashMap<String, String>();
    Map<String,String> searchMap=new HashMap<String, String>();
    private void init() {
        Intent intent = getIntent();
        typeRecord = intent.getStringExtra("typeRecord");
        System.out.println("typeRecord+"+typeRecord);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.list_activity);
        init();//加载
        initSlidingMenu();
        Datarunnable(data);

    }
    /*
    * 初始化右侧搜索菜单
    * */
    private void initRightMenu(SlidingMenu sideingMenu){
//        _RightFragment = new RightFragment();// 创建右边菜单Fragment
        _RightFragment=RecordList.RightFragment.get(typeRecord);
        System.out.println(_RightFragment+"--------"+typeRecord);
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
    public String getTypeRecord(){
        return  typeRecord;
    }
    //获取列表内容
    private void Datarunnable(Map<String,String> data){
        RecordRunnable recordRunnable=new RecordRunnable();
        recordRunnable.createHand(RecordListActivity.this,typeRecord,data);
        Thread dataThread=new Thread(recordRunnable);
        dataThread.start();
    }

    public void updataButtomList(String data){
        System.out.println("-----data-----"+data);
        if(data==null){
            bottomListFragment.updata(data,2);
        }else if(data.trim().equals("[]")){
            bottomListFragment.updata(data,1);
        }else{
            bottomListFragment.updata(data,0);
        }
    }

    public void dataChanged(){
        bottomListFragment.dataChanged();
    }
    public void setCount(){
        listTitleFragment.Run();
    }
    /*
    * 左侧菜单选择后执行
    * 菜单隐藏，数据加载
    */
    @Override
    public void onOKItem(String typeRecord) {
        if(!(this.typeRecord.trim().equals(typeRecord))){
            this.typeRecord=typeRecord;
            initRightMenu(_SlidingMenu);

            listTitleFragment.setTitle();

            //缺少一个数据的加载
            System.out.println("typeRecord"+typeRecord);
            Datarunnable(data);

        }
        LeftSlidingMenuIsShow();

        //titleFragment的一个数据统计
        //buttomListFragment的一个数据列表


    }

    public void LeftSlidingMenuIsShow(){
        if (!_SlidingMenu.isMenuShowing()) {
            _SlidingMenu.showMenu();
        } else {
            _SlidingMenu.showContent();
        }
    }
    public void RightSlidingMenuIsShow(){
        if (!_SlidingMenu.isSecondaryMenuShowing()) {
            _SlidingMenu.showSecondaryMenu();
        } else {
            _SlidingMenu.showContent();
        }
    }



    public void onBackListener(View view) {
        //清空搜索条件
        View viewall = getWindow().getDecorView();
        searchMap= ValueUtils.getAllChildViews(viewall);
        //遍历map
        for (Map.Entry<String, String> entry : searchMap.entrySet()) {


            try {
                //生成控件
                int id = R.id.class.getField(entry.getKey()).getInt(null);

                View view1=view.findViewById(id);
                if(view1 instanceof EditText){
                    entry.setValue("");
                    ((EditText) view1).setText("");
                }else if(view1 instanceof Button){
                    if(entry.getKey().contains("search")){

                    }else{
                        System.out.println("------成功了吗----");
                        entry.setValue("");
                        ((Button) view1).setText("");
                    }

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }
    }
    //搜索的条件
    private  Map<String,String> addMap(){

        View viewall = getWindow().getDecorView();
        searchMap= ValueUtils.getAllChildViews(viewall);
        System.out.println("------searchMap--------"+searchMap);
        return  searchMap;
    }

    public void onOKListener() {
        RightSlidingMenuIsShow();
        data=addMap();
        System.out.println("data"+data);
        Datarunnable(data);

    }


    @Override
    public void CreateRecord() {

        Intent intent=new Intent(RecordListActivity.this,CreateRecordActivity.class);
        intent.putExtra("typeRecord",RecordListActivity.this.getTypeRecord());
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent d) {

        if(requestCode==1){
            Datarunnable(data);
           switch (resultCode)
           {
               case 2:
                   setCount();
                   break;
               case 3:
                   break;
           }
        }
        super.onActivityResult(requestCode, resultCode, d);

    }

    @Override
    public void EditButton(Map<String,String> map) {
                Intent   intent=new Intent(this,CreateRecordActivity.class);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            intent.putExtra(entry.getKey(),entry.getValue());
        }

         startActivityForResult(intent, 1);
    }
}
