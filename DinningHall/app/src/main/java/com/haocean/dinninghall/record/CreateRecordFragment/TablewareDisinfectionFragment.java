package com.haocean.dinninghall.record.CreateRecordFragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.ListRunnable;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.DataList;
import com.haocean.dinninghall.record.utils.ValueUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by haocean on 2016/10/8.
 */
public class TablewareDisinfectionFragment extends Fragment implements View.OnClickListener {
    private CreateRecordActivity activity;
    private  Button start_date;

    private  Button end_date,clean_condition,tableandroidnum,cleantype,disinfection_type;
    String[] str_cleantype={"手工清洗","洗碗机清洗","其他"};
    String[] str_disinfection_type={"蒸汽消毒","煮沸消毒","红外线消毒","化学消毒","其他"};
    String[] con={"正常","未保洁"};
     AlertDialog dlg;//选择弹出框
   Map<String,String> count=new HashMap<String,String>();

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

                  View  view1=LayoutInflater.from(AppController.getInstance()).inflate(R.layout.dialog, null);
                    final String list= DataList.list;
                  final String[]  arr=list.split(",");
                    final String[] listIds=DataList.listId.split(",");
                    LinearLayout  listviews=(LinearLayout)view1.findViewById(R.id.listviews);

                    for(int i = 0; i<arr.length; i++){
                        View  view2=LayoutInflater.from(AppController.getInstance()).inflate(R.layout.alert, null);
                      EditText e= (EditText) view2.findViewById(R.id.tablewarecount);
                        final int finalI = i;
                        e.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                count.put(arr[finalI],s.toString() );

                            }
                        });

                     TextView t= (TextView) view2.findViewById(R.id.tablewarename);
                        t.setText(arr[i]);
                        listviews.addView(view2);
                    }



                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                final View layout = inflater.inflate(R.layout.dialog, null);//获取自定义布局
                    builder.setView(view1);

                    //确认按钮
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            String num="";
                            Iterator<Map.Entry<String, String>> it = count.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry<String, String> entry = it.next();
                                if(!entry.getValue().trim().equals("")){
                                    if(it.hasNext()){
                                        num+=entry.getKey() + ":" + entry.getValue()+",";

                                    }
                                    else{
                                        num+=entry.getKey() + ":" + entry.getValue();
                                    }
                                }
                            }
                            tableandroidnum.setText(num);

                        }
                    });
                    //取消
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    dlg = builder.create();
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
                dlg.show();
                dlg.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

            }
            break;
        }

    }


}
