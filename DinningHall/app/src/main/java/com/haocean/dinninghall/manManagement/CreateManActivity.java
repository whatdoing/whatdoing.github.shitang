package com.haocean.dinninghall.manManagement;

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
import com.haocean.dinninghall.contexts.ManManagementList;
import com.haocean.dinninghall.record.*;
import com.haocean.dinninghall.record.utils.ValueUtils;

/**
 * Created by haocean on 2016/9/29.
 */
public class CreateManActivity extends Activity  implements View.OnClickListener{
   private String typeMan,tempString,id;
    private Button submit;
    private Fragment fragment;
    CreateRecordRunnable createRecordRunnable;
    public  void init(){
        Intent intent = getIntent();
        typeMan = intent.getStringExtra("typeRecord");
        tempString=intent.getStringExtra("tempString");
        id=intent.getStringExtra("id");


        fragment= ManManagementList.ManFragment.get(typeMan);
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_title, new CreateTitleFragment(),"createFragmentTitle").commit();
        fragmentManager.beginTransaction().replace(R.id.fragment_context, fragment,"createFragment").commit();
    }
    public String getTypeMan(){
        return typeMan;
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
              Object object=  ValueUtils.setValue(typeMan,CreateManActivity.this);
              //数据提交
                if(id==null){
                    createRecordRunnable=new CreateRecordRunnable(hand,object);
                    createRecordRunnable.setTypeRecord(typeMan);
                }
                else{
                    createRecordRunnable=new CreateRecordRunnable(handEdit,object);
                    createRecordRunnable.setTypeRecord(typeMan,id);
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
                    Intent intent = new Intent();
                    intent.setClass(CreateManActivity.this, ManManagementIndexActivity.class);
                    setResult(2, intent);
                    finish();
                    Toast.makeText(CreateManActivity.this, "新建成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(CreateManActivity.this, "新建失败", Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent();
                    intent.setClass(CreateManActivity.this, ManManagementIndexActivity.class);
                    setResult(3, intent);
                    finish();
                    Toast.makeText(CreateManActivity.this, "编辑成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(CreateManActivity.this, "编辑失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };




}
