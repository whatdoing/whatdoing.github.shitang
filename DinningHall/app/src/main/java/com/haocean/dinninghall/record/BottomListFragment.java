package com.haocean.dinninghall.record;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.adapter.ListViewAdapter;
import com.haocean.dinninghall.view.MyListView.XListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by haocean on 2016/7/7.
 */
public class BottomListFragment extends Fragment implements XListView.IXListViewListener{
    private XListView listview;
    private RecordListActivity activity;
      String respond;
    ListViewAdapter mAdapter;

    public void dataChanged(){
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_buttom, container, false);//初始化fragment
        activity=((RecordListActivity)getActivity());
        listview= (XListView)view.findViewById(R.id.superviseList);//数据列表

        listview.setXListViewListener(this);
        return view;
    }


    public void updata(String data,int i){

        this.respond=data;
        handlist.obtainMessage(i).sendToTarget();
    }
    //hand消息
    // UI操作
    Handler handlist = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            switch(msg.what){
                case 0:
                    if(mAdapter!=null){
                        mAdapter.setJsonAry(respond);
                        dataChanged();
                    }else{
                        mAdapter = new ListViewAdapter(respond,activity.getTypeRecord(),activity);
                        listview.setAdapter(mAdapter);//为ListView绑定Adapter
                    }
                    break;
                case 1:
                    mAdapter = new ListViewAdapter(respond,activity.getTypeRecord(),activity);
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
