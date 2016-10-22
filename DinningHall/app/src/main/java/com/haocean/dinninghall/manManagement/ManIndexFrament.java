package com.haocean.dinninghall.manManagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.RecordRunnable;
import com.haocean.dinninghall.adapter.DocumentsAdapter;
import com.haocean.dinninghall.adapter.ListViewAdapter;
import com.haocean.dinninghall.contexts.ManManagementList;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.RecordListActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * Created by haocean on 2016/7/7.
 */
public class ManIndexFrament extends Fragment{
    private ListView list;
    private String Jsondata;


    //记录表
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment1, container, false);//初始化fragment
            list= (ListView) view.findViewById(R.id.list);
           Jsondata="{Roster:34,MorningInspection:1546}";

        //访问http请求获得统计数据
        //获得数据执行线程
        handlist.obtainMessage(0).sendToTarget();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("到这里了吗？");
                String type=null;
                Intent intent=new Intent(getActivity(), ManManagementIndexActivity.class);
                switch (i){
                    case 0:
                        type="Roster";
                        break;
                    case 1:
                        type="MorningInspection";
                }
                intent.putExtra("typeRecord",type);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    Handler handlist = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            switch(msg.what){
                case 0:
                    // 解析json，
                    DocumentsAdapter mAdapter = new DocumentsAdapter(Jsondata);
                    list.setAdapter(mAdapter);//为ListView绑定Adapter
                    break;
                case 1:
                    Toast.makeText(getActivity(), "系统维护数据无法查询到，请稍后重试！", Toast.LENGTH_SHORT).show();
                    break;
                case 2:

                    Toast.makeText(getActivity(), "无查询数据", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
}
