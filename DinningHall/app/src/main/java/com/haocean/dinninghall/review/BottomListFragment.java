package com.haocean.dinninghall.review;

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
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CreateRecordRunnable;
import com.haocean.dinninghall.adapter.ListViewAdapter;
import com.haocean.dinninghall.entity.record.ReviewRecord;
import com.haocean.dinninghall.safety.SafetyListActivity;
import com.haocean.dinninghall.view.MyListView.XListView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by haocean on 2016/7/7.
 */
public class BottomListFragment extends Fragment implements XListView.IXListViewListener, OnItemClickListener {
    private XListView listview;
    private ReviewListActivity activity;
    String respond;
    private AlertView mAlertViewExt;//窗口拓展例子
    ListViewAdapter mAdapter;
    String type;
    TextView tvx;
    String id="";
    JSONObject jsonObject;
    // handler类接收数据
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 0:
                    Toast.makeText(getActivity(), "办结成功！", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getActivity(), "办结失败！", Toast.LENGTH_LONG).show();
                    break;
            }
        };
    };
    @Override
    public void onItemClick(Object o, int position) {
        if(position != AlertView.CANCELPOSITION){
            if(position==2){
                ReviewRecord reviewRecord=new ReviewRecord();
                reviewRecord.setDealstate("办结");
                CreateRecordRunnable createRecordRunnable=new CreateRecordRunnable(handler,reviewRecord);
                createRecordRunnable.setTypeRecord(activity.getTypeRecord(),id);
                Thread thread=new Thread(createRecordRunnable);
                thread.start();

            }else{
                Intent intent=new Intent(activity, CreateReviewActivity.class);
                intent.putExtra("typeRecord","ReviewRecord");
                switch (position){
                    case 0:
                        intent.putExtra("num",0);//整改
                        break;
                    case 1:
                        intent.putExtra("num",1);
                        break;

                }
                intent.putExtra("tempString",jsonObject.toString());
                intent.putExtra("id",id);
                startActivity(intent);
            }
        }



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_buttom, container, false);//初始化fragment
        activity=((ReviewListActivity)getActivity());
        listview= (XListView)view.findViewById(R.id.superviseList);//数据列表
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                tvx=(TextView)arg1.findViewById(R.id.id);//真id
                id=tvx.getText().toString().trim();
                try {
                    jsonObject= mAdapter.getJsonAry().getJSONObject(arg2-1);
                    System.out.println("-----单条数据--"+jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                alertShow();
            }

        });
        listview.setXListViewListener(this);
        return view;
    }
    public void alertShow() {
        mAlertViewExt=   new AlertView(null, null, null,null ,
                new String[]{"添加整改记录", "添加复查记录", "办结"},
                activity, AlertView.Style.Alert,this ).setCancelable(true);
        mAlertViewExt.show();
    }
    public void dataChanged(){
        mAdapter.notifyDataSetChanged();
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
            boolean t = false;
            if(type==null){
                type=activity.getTypeRecord();
                t=false;
            }else{
                t= type.equals(activity.getTypeRecord());
                if(!t){
                    type=activity.getTypeRecord();
                }
            }
            switch(msg.what){
                case 0:
                    if(mAdapter!=null&&t){
                        mAdapter.setJsonAry(respond,activity.getTypeRecord());
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
