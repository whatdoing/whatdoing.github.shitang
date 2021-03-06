package com.haocean.dinninghall.review;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CreateRecordRunnable;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.review.CreateTitleFragment;
import com.haocean.dinninghall.safety.SafetyListActivity;

public class CreateReviewActivity extends Activity implements View.OnClickListener{
    public   String typeRecord;
    private Button submit;
    private Fragment fragment;
    private String id;
    private  String tempString;
String  num;
    CreateRecordRunnable createRecordRunnable;
    public  void init() {
        Intent intent = getIntent();
        typeRecord = intent.getStringExtra("typeRecord");

        tempString=intent.getStringExtra("tempString");
        id=intent.getStringExtra("id");
        num=intent.getStringExtra("num");
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_title, new CreateTitleFragment(),"createFragmentTitle").commit();

        //解析tempString
        fragment= RecordList.Fragment.get(typeRecord);
        fragmentManager.beginTransaction().replace(R.id.fragment_context, fragment,"createFragment").commit();

    }
    public String getTypeRecord(){
        return typeRecord;
    }
    public String  getNum(){
        return num;
    }
    public String getId(){
        return id;
    }
    public String getTempString(){
        return tempString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_review);

        init();
        submit= (Button) findViewById(R.id.submit);

        submit.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        switch (viewId){
            case R.id.submit:
                Object object=  ValueUtils.setValue(typeRecord,CreateReviewActivity.this);

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
                    Intent intent = new Intent();
                    intent.setClass(CreateReviewActivity.this, SafetyListActivity.class);
                    setResult(2, intent);
                    finish();
                    Toast.makeText(CreateReviewActivity.this, "新建成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:

                    Toast.makeText(CreateReviewActivity.this, "新建失败", Toast.LENGTH_SHORT).show();
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
                    intent.setClass(CreateReviewActivity.this, SafetyListActivity.class);
                    setResult(3, intent);
                    finish();
                    Toast.makeText(CreateReviewActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:

                    Toast.makeText(CreateReviewActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

}
