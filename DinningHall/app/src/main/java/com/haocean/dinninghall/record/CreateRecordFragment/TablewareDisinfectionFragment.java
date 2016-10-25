package com.haocean.dinninghall.record.CreateRecordFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.ListRunnable;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.DataList;
import com.haocean.dinninghall.record.utils.ValueUtils;

/**
 * Created by haocean on 2016/10/8.
 */
public class TablewareDisinfectionFragment extends Fragment implements View.OnClickListener{
    private CreateRecordActivity activity;
    private  Button start_date;
    private  Button end_date,clean_condition,tablewarename;
    String[] con={"正常","未保洁"};
String list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tableware, container, false);//初始化fragment

        activity= (CreateRecordActivity)getActivity();
        Run();
        start_date= (Button) view.findViewById(R.id.start_date);
        end_date= (Button) view.findViewById(R.id.end_date);
        clean_condition=(Button)view.findViewById(R.id.clean_condition);
        tablewarename=(Button)view.findViewById(R.id.tablewarename);

        start_date.setOnClickListener(this);
        end_date.setOnClickListener(this);
        clean_condition.setOnClickListener(this);
        tablewarename.setOnClickListener(this);

        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("TablewareDisinfection", view, tempString);
        }
        return view;
    }

    public void Run(){
        ListRunnable listRunnable=new ListRunnable(activity);
        listRunnable.setTypeMan(activity.typeRecord);
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
            case R.id.tablewarename: {

                String list= DataList.list;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(tablewarename,arr,activity);
            }
            break;
        }

    }
}
