package com.haocean.dinninghall.setup.revise;

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
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.setup.user.UserActivity;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class ReviseActivity extends Activity implements View.OnClickListener{
EditText data;
    ImageView submit;
    LinearLayout back;
    String typeMan="User";
    Map<String,String> dataP=new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise);
        data=(EditText)findViewById(R.id.data);
        submit=(ImageView)findViewById(R.id.submit);
        back=(LinearLayout)findViewById(R.id.back);
        data.setText(UserData.getPassword());
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }
    public static String MD5(String str) {
        MessageDigest md5 =null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }

        char[] charArray = str.toCharArray();
        byte[] byteArray =new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue =new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) &0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    public void Run(){
        System.out.println("-----有进来吗----");
        String pass=MD5(data.getText().toString());
        dataP.put("password",pass);
        UserRunnable userRunnable=new UserRunnable(dataP,hand, typeMan);
        Thread dataThread=new Thread(userRunnable);
        dataThread.start();
    }
    Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                   // String value=data.getText().toString();
                   /* Intent intent = new Intent();
                    intent.setClass(ReviseActivity.this, UserActivity.class);
                    startActivity(intent);*/
                    finish();
                    Toast.makeText(ReviseActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:

                    Toast.makeText(ReviseActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };


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
