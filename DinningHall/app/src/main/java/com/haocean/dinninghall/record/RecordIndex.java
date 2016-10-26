package com.haocean.dinninghall.record;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.service.voice.VoiceInteractionSession;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CountRunnable;
import com.haocean.dinninghall.adapter.SafetyAdapter;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.RecordListActivity;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * Created by haocean on 2016/7/7.
 */
public class RecordIndex extends Fragment{
    private LinearLayout recordList;
    private ListView list;
    static  String   DocString;
    String type="countProcess";
    public void Run(){
        CountRunnable countRunnable=new CountRunnable(handlist,type);
        Thread dataThread=new Thread(countRunnable);
        dataThread.start();
    }
//记录表
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment1, container, false);//初始化fragment

        recordList = (LinearLayout) view.findViewById(R.id.recordList);//要添加内容的布局recordList
        list= (ListView) view.findViewById(R.id.list);
        Run();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("到这里了吗？");
                Intent intent=new Intent(getActivity(), RecordListActivity.class);
                intent.putExtra("typeRecord",RecordList.ArrStr.get(i));

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
                    DocString=CountRunnable.getContacts();
                    // 解析json，
                    SafetyAdapter mAdapter = new SafetyAdapter(DocString,2);
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
