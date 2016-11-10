package com.haocean.dinninghall.safety;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.app.Fragment;
import android.widget.Toast;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CheckInfoRunnable;
import com.haocean.dinninghall.Runnable.CreateRecordRunnable;
import com.haocean.dinninghall.Runnable.RecordRunnable;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.entity.record.CheckLog;
import com.haocean.dinninghall.record.RecordListActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.safety.CreateSafetyFragment.CheckLogFragment;

import java.util.HashMap;
import java.util.Map;

public class CreateSafetyActivity extends Activity implements View.OnClickListener{

    public   String typeRecord;
    private Button submit;
    private Fragment fragment;
    private String id;
    private  String tempString;

    CreateRecordRunnable createRecordRunnable;
    FragmentManager fragmentManager;
    Map<String, String> data=new HashMap<String, String>();;
    public  void init() {
        Intent intent = getIntent();
        typeRecord = intent.getStringExtra("typeRecord");
        tempString=intent.getStringExtra("tempString");
        System.out.println("---  safe   tempString--"+tempString);
        id=intent.getStringExtra("id");


         fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_title, new CreateTitleFragment(),"createFragmentTitle").commit();

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
        Run();
        submit= (Button) findViewById(R.id.submit);

        submit.setOnClickListener(this);
    }

public void Run(){
    CheckInfoRunnable checkInfoRunnable=new CheckInfoRunnable();
    checkInfoRunnable.createHand(CreateSafetyActivity.this,"CheckInfo",handler);
    Thread dataThread=new Thread(checkInfoRunnable);
    dataThread.start();
}

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    System.out.println("获取成功了吗");
                    fragment= RecordList.Fragment.get(typeRecord);
                    fragmentManager.beginTransaction().replace(R.id.fragment_context, fragment,"createFragment").commit();
                    break;
                case 1:
                    Toast.makeText(CreateSafetyActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        switch (viewId){
            case R.id.submit:
                Object object=  ValueUtils.setValue(typeRecord,CreateSafetyActivity.this);
if(object instanceof CheckLog){
    System.out.println("-----------sfdsf"+((CheckLogFragment)fragment).getSelcetMap().toString());
    ((CheckLog)object).setYb("123");
    ((CheckLog)object).setZd("13213123");
}

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
                    intent.setClass(CreateSafetyActivity.this, SafetyListActivity.class);
                    setResult(2, intent);
                    finish();
                    Toast.makeText(CreateSafetyActivity.this, "新建成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(CreateSafetyActivity.this, "新建失败", Toast.LENGTH_SHORT).show();
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
                    intent.setClass(CreateSafetyActivity.this, SafetyListActivity.class);
                    setResult(3, intent);
                    finish();
                    Toast.makeText(CreateSafetyActivity.this, "编辑成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(CreateSafetyActivity.this, "编辑失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };


}
