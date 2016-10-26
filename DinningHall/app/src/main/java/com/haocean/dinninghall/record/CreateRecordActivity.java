package com.haocean.dinninghall.record;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CreateRecordRunnable;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.utils.ValueUtils;

import java.util.Map;

/**
 * Created by haocean on 2016/9/29.
 */
public class CreateRecordActivity  extends Activity  implements View.OnClickListener{
    public String typeRecord;
    private Button submit;
    private Fragment fragment;
    private String id,con;
    private  String tempString;

    CreateRecordRunnable createRecordRunnable;
    public  void init() {
        Intent intent = getIntent();
        typeRecord = intent.getStringExtra("typeRecord");
        tempString=intent.getStringExtra("tempString");
        System.out.println("---解析tempString--"+tempString);
        id=intent.getStringExtra("id");


        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_title, new CreateTitleFragment(),"createFragmentTitle").commit();

        //布局
        fragment= RecordList.Fragment.get(typeRecord);
        fragmentManager.beginTransaction().replace(R.id.fragment_context, fragment,"createFragment").commit();

    }

    public String getTempString(){
        return tempString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_record);

        init();
        submit= (Button) findViewById(R.id.submit);

        submit.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        switch (viewId){
            case R.id.submit:
                Object object=  ValueUtils.setValue(typeRecord,CreateRecordActivity.this);


                if(id==null){
                    createRecordRunnable=new CreateRecordRunnable(hand,object);
                    createRecordRunnable.setTypeRecord(typeRecord);
                }
                else{
                    createRecordRunnable=new CreateRecordRunnable(handEdit,object);
                    createRecordRunnable.setTypeRecord(typeRecord,id);
                }

                Thread dataThread=new Thread(createRecordRunnable);
                dataThread.start();
                break;
        }
    }
    Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:

                    finish();
                    Toast.makeText(CreateRecordActivity.this, "新建成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:

                    Toast.makeText(CreateRecordActivity.this, "新建失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
    Handler handEdit=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    //  myPublicClass.clearBimp();
                    finish();
                    Toast.makeText(CreateRecordActivity.this, "编辑成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    //   myPublicClass.clearBimp();
                    Toast.makeText(CreateRecordActivity.this, "编辑失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };


}
