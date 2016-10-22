package com.haocean.dinninghall;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.haocean.dinninghall.Runnable.LoginRunnale;
import com.haocean.dinninghall.document.DocumentActivity;
import com.haocean.dinninghall.publicMethod.Empty;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by haocean on 2016/7/7.
 */
public class LoginActivity extends Activity {
private CheckBox ceshi,remember;
    private EditText username,password;
    private Button login;
    private String address,flag="false";//地址，是否记录密码
    public SharedPreferences loginShare;
    private SharedPreferences.Editor loginEdit;
    Map<String,String> map;
    private void findById(){
        ceshi = (CheckBox) findViewById(R.id.ceshi);//选择测试地址
        remember = (CheckBox) findViewById(R.id.remember);//记住密码
        username = (EditText) findViewById(R.id.username);//用户名
        password = (EditText) findViewById(R.id.password);//密码
        login = (Button) findViewById(R.id.login);

        address="http://192.168.199.142/4.1banben";

    }
    private void  init(){
        loginShare = getSharedPreferences("loginFile", Context.MODE_PRIVATE);
        loginEdit = loginShare.edit();//打开编辑
        //登录信息，帐号密码，记住密码


        flag = loginShare.getString("flag", "false");
        if (flag.contains("true")) {
            // currentCity = loginShare.getString("addressURL", "测试服务器");
            username.setText(loginShare.getString("username", " ").toString());
            password.setText(loginShare.getString("password", " ").toString());
            if (loginShare.getString("address", "").toString().contains("http://192.168.199.142/4.1banben")) {
                address="http://192.168.199.142/4.1banben";
                ceshi.setChecked(true);
            }else{
                address="http://192.168.199.142/4.1banben";
            }
            remember.setChecked(true);
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        findById();
        init();//加载
        //用户名
        username.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
                remember.setChecked(false);
                return false;
            }
        });

        //用户名有改变时，密码与记住密码取消
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                password.setText(null);
                remember.setChecked(false);
                ceshi.setChecked(false);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        //测试
        ceshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(ceshi.isChecked()){
                    //选中
                    address="http://ceshi.safetybao.net";
                }else{
                    //不选中
                    address="http://192.168.199.142/4.1banben";
                }
            }
        });
        //记住密码
        remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(remember.isChecked()){
                    //选中
                    flag="true";

                }else{
                    //不选中
                    flag="false";

                }
            }
        });
        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent  intent = new Intent(LoginActivity.this, DocumentActivity.class);
//                startActivity(intent);
//                finish();


                flag=String.valueOf(remember.isChecked());
                map=new HashMap<String,String>();
                map.put("username",username.getText().toString().trim());
                map.put("password",password.getText().toString().trim());
                map.put("address",address.trim());
                map.put("flag", flag.trim());//是否记住密码



                if(!Empty.isToEmpty(map,LoginActivity.this)){

                    LoginRunnale lr = new LoginRunnale(LoginActivity.this,address,loginhand);
                    lr.setPassword(password.getText().toString().trim());
                    lr.setUsername(username.getText().toString().trim());
                    Thread thread = new Thread(lr);
                    thread.start();
                    //此处还需要验证是否登录成功
                   //先模拟登录成功


                }
            }
        });

    }
//登录成功之后保存所有登录信息数据
    private void loginput(Map<String, String> maps){
        Set keyset = maps.keySet();
        if(flag.contains("true")){
            //选中
            for(Object keyname:keyset){
                loginEdit.putString(keyname.toString(),maps.get(keyname).toString());
            }
            loginEdit.commit();
        }else{
            loginEdit.clear();
            loginEdit.putString("flag",flag).commit();
            //不选中
        }
    }
    Handler loginhand = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //成功后，提交jsession共享

                    //loginEdit.putString("jsession", LoginRunnale.jseSsion);
                    if(true){
                        loginput(map);//登录成功就跳转然后根据是否记住密码保存登录信息

                        Intent  intent = new Intent(LoginActivity.this, DocumentActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    loginEdit.commit();

                    break;
                case 1:

                    Toast.makeText(LoginActivity.this, "登录失败,请重新登录!", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };//handle
}
