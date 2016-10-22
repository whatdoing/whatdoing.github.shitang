package com.haocean.dinninghall.review;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.adapter.SafetyAdapter;
import com.haocean.dinninghall.safety.SafetyListActivity;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class ReviewIndex extends Fragment {
    private ListView list;
    private String Jsondata;

    //记录表
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);//初始化fragment


        list= (ListView) view.findViewById(R.id.list);
        Jsondata="{ReviewRecord:{Ynumber:34,Mnumber:14}}";

        handlist.obtainMessage(0).sendToTarget();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("到这里了吗？");
                String type=null;
                Intent intent=new Intent(getActivity(), ReviewListActivity.class);
                switch (i){
                    case 0:
                        type="ReviewRecord";
                        break;

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
                    System.out.println("----有没有  有没有------");
                    // 解析json，
                    SafetyAdapter mAdapter = new SafetyAdapter(Jsondata,1);
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
