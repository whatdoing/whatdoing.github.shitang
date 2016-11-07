package com.haocean.dinninghall.record.CreateRecordFragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.ListRunnable;
import com.haocean.dinninghall.adapter.TableAdapter;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.DataList;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.setup.revise.ReviseActivity;

/**
 * Created by haocean on 2016/10/8.
 */
public class TablewareDisinfectionFragment extends Fragment implements View.OnClickListener,OnItemClickListener {
    private CreateRecordActivity activity;
    private  Button start_date;
    private  Button end_date,clean_condition,tableandroidnum,cleantype,disinfection_type;
    String[] str_cleantype={"手工清洗","洗碗机清洗","其他"};
    String[] str_disinfection_type={"蒸汽消毒","煮沸消毒","红外线消毒","化学消毒","其他"};
    String[] con={"正常","未保洁"};
ListView listView;
    View view1;
    AlertView mAlertViewExt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tableware, container, false);//初始化fragment

        activity= (CreateRecordActivity)getActivity();
        Run();
        start_date= (Button) view.findViewById(R.id.start_date);
        end_date= (Button) view.findViewById(R.id.end_date);
        clean_condition=(Button)view.findViewById(R.id.clean_condition);
        cleantype=(Button)view.findViewById(R.id.cleantype);
        disinfection_type=(Button)view.findViewById(R.id.disinfection_type);
        tableandroidnum=(Button)view.findViewById(R.id.tableandroidnum);

        start_date.setOnClickListener(this);
        end_date.setOnClickListener(this);
        clean_condition.setOnClickListener(this);
        cleantype.setOnClickListener(this);
        disinfection_type.setOnClickListener(this);
        tableandroidnum.setOnClickListener(this);

       view1=LayoutInflater.from(AppController.getInstance()).inflate(R.layout.dialog, null);
        listView=(ListView)view1.findViewById(R.id.listview);

        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("TablewareDisinfection", view, tempString);
        }
        return view;
    }
    Handler handlist = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case 0:
                    System.out.println("---0000----");
                    TableAdapter tableAdapter=new TableAdapter(getActivity());
                    listView.setAdapter(tableAdapter);
                    break;
                case 1:
                    Toast.makeText(getActivity(), "无查询数据", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    public void Run(){
        ListRunnable listRunnable=new ListRunnable(activity);
        listRunnable.setTypeMan(activity.typeRecord,handlist);
        Thread thread=new Thread(listRunnable);
        thread.start();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.start_date: {
                MyDialogs.cereateDateDialog(start_date,activity);
            }
            break;
            case R.id.end_date: {
                MyDialogs.cereateDateDialog(end_date,activity);
            }
            break;
            case R.id.clean_condition: {
                MyDialogs.cereateDialog(clean_condition,con,activity);
            }
            break;
            case R.id.cleantype: {
                MyDialogs.cereateDialog(cleantype,str_cleantype,activity);
            }
            break;
            case R.id.disinfection_type: {
                MyDialogs.cereateDialog(disinfection_type,str_disinfection_type,activity);
            }
            break;
            case R.id.tableandroidnum: {
               /* AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setView(R.layout.dialog);
                builder.show();*/
                mAlertViewExt = new AlertView("提示", "请填入旧密码！", "取消", null, new String[]{"完成"},getActivity(), AlertView.Style.Alert, this);
                mAlertViewExt.addExtView(view1);
            }
            break;
        }

    }

    @Override
    public void onItemClick(Object o, int position) {
        if(o == mAlertViewExt && position != AlertView.CANCELPOSITION){
           System.out.println("------成功----");
            return;
        }
    }
}
