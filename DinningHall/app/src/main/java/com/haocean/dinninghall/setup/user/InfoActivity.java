package com.haocean.dinninghall.setup.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.UserRunnable;
import com.haocean.dinninghall.contexts.Contexts;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.publicMethod.UserData;

import java.util.HashMap;
import java.util.Map;

public class InfoActivity extends Activity implements View.OnClickListener {
    ImageView submit;
    LinearLayout back;
    String typeMan;
    EditText dataE;
    Map<String,String> data=new HashMap<String, String>();
    //哪个要修改
    int num;

    public  void init() {
        submit=(ImageView)findViewById(R.id.submit);
        back=(LinearLayout)findViewById(R.id.back);
        dataE=(EditText)findViewById(R.id.data);
        Intent intent=getIntent();
        typeMan=intent.getStringExtra("typeMan");
        num=intent.getIntExtra("num",0);
        dataE.setText(RecordList.UserSave.get(RecordList.UserInfoArr.get(num)));

    }
    public void Run(){
        data.put(RecordList.UserInfoArr.get(num),dataE.getText().toString());
        UserRunnable userRunnable=new UserRunnable(data,hand, typeMan);
        Thread dataThread=new Thread(userRunnable);
        dataThread.start();
    }
    Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    String value=dataE.getText().toString();
                    switch (num){
                        case 0:
                            UserData.setRealname(value);
                            break;
                        case 1:
                            UserData.setSex(value);
                            break;
                        case 2:
                            UserData.setPhone(value);
                            break;
                        case 3:
                            UserData.setEmail(value);
                            break;
                    }
                    Intent intent = new Intent();
                    intent.setClass(InfoActivity.this, UserActivity.class);
                    setResult(UserActivity.userok, intent);
                    finish();
                    Toast.makeText(InfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:

                    Toast.makeText(InfoActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        init();
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.back: {
               finish();
            }
            break;
            case R.id.submit: {
                Run();
            }
            break;
        }
    }
}
