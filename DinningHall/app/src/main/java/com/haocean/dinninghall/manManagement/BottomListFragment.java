package com.haocean.dinninghall.manManagement;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.adapter.ListViewAdapter;
import com.haocean.dinninghall.record.RecordListActivity;
import com.haocean.dinninghall.view.MyListView.XListView;


/**
 * Created by haocean on 2016/7/7.
 */
public class BottomListFragment extends Fragment implements XListView.IXListViewListener{
private XListView listview;
private    String respond,typeMan;
    ListViewAdapter mAdapter;
    private ManManagementIndexActivity activity;
    public void setData(String data){
        respond=data;
        System.out.println("------输出----------"+data);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_buttom, container, false);//初始化fragment
        activity=((ManManagementIndexActivity)getActivity());
        listview= (XListView)view.findViewById(R.id.superviseList);//数据列表

        listview.setXListViewListener(this);
        return view;
    }





    public void updata(String manData,int i,String typeMan){

        this.respond=manData;
        this.typeMan=typeMan;
        System.out.println("---respond--"+respond);
        System.out.println("---hand得到的消息是-----"+i);
        handlist.obtainMessage(i).sendToTarget();
    }
    //hand消息
    // UI操作
    Handler handlist = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            switch(msg.what){
                case 0:
                    System.out.println("----------"+typeMan);
                    mAdapter = new ListViewAdapter(respond,typeMan,activity);
                    listview.setAdapter(mAdapter);//为ListView绑定Adapter
                    break;
                case 1:
                    mAdapter = new ListViewAdapter(respond,typeMan,activity);
                    listview.setAdapter(mAdapter);//为ListView绑定Adapter
                    Toast.makeText(getActivity(), "无查询数据", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getActivity(), "系统维护数据无法查询到，请稍后重试！", Toast.LENGTH_LONG).show();

                    break;
            }

        }
    };



    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
