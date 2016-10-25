package com.haocean.dinninghall.safety;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.haocean.dinninghall.Runnable.CountRunnable;
import com.haocean.dinninghall.adapter.DocumentsAdapter;
import com.haocean.dinninghall.adapter.SafetyAdapter;
import com.haocean.dinninghall.contexts.RecordList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class SafetyIndex extends Fragment {
    private ListView list;
    private String Jsondata;
    String type="countLog";
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

        Run();
        list= (ListView) view.findViewById(R.id.list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("到这里了吗？");
                String type=null;
                Intent intent=new Intent(getActivity(), SafetyListActivity.class);
                intent.putExtra("typeRecord",RecordList.SafetyArrStr.get(i));
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
                    Jsondata=CountRunnable.getContacts();

                    // 解析json，

                    SafetyAdapter mAdapter = new SafetyAdapter(Jsondata,0);
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
