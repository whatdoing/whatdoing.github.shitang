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
    public SharedPreferences loginShare,userShare;
    private SharedPreferences.Editor loginEdit,userEdit;
    Map<String,String> map;
    private void findById(){
        ceshi = (CheckBox) findViewById(R.id.ceshi);//选择测试地址
        remember = (CheckBox) findViewById(R.id.remember);//记住密码
        username = (EditText) findViewById(R.id.username);//用户名
        password = (EditText) findViewById(R.id.password);//密码
        login = (Button) findViewById(R.id.login);

        address="http://tzyh.safetybao.com/";

    }
    private void  init(){
        loginShare = getSharedPreferences("loginFile", Context.MODE_PRIVATE);
        loginEdit = loginShare.edit();//打开编辑
        //登录信息，帐号密码，记住密码
        userShare = getSharedPreferences("userFile", Context.MODE_PRIVATE);
        userEdit = userShare.edit();//打开编辑
        //登录信息，帐号密码，记住密码


        flag = loginShare.getString("flag", "false");
        if (flag.contains("true")) {
            // currentCity = loginShare.getString("addressURL", "测试服务器");
            username.setText(loginShare.getString("username", " ").toString());
            password.setText(loginShare.getString("password", " ").toString());
            if (loginShare.getString("address", "").toString().contains("http://tzyh.safetybao.com/")) {
                address="http://tzyh.safetybao.com/";
              //  ceshi.setChecked(true);
            }else if(loginShare.getString("address", "").toString().contains("http://test.safetybao.com/")){
                address="http://test.safetybao.com/";
                  ceshi.setChecked(true);
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
                    address="http://test.safetybao.com/";
                }else{
                    //不选中
                    address="http://tzyh.safetybao.com/";
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
                     userEdit.putString("address",address).commit();
                    LoginRunnale lr = new LoginRunnale(LoginActivity.this,address,loginhand);
                    lr.setPassword(password.getText().toString().trim());
                    lr.setUsername(username.getText().toString().trim());
                    Thread thread = new Thread(lr);
                    thread.start();
            }
        });

    }
//登录成功之后保存所有登录信息数据
    private void loginput(){
        flag=String.valueOf(remember.isChecked());
        if(flag.contains("true")){
            //选中
            loginEdit.putString("username",username.getText().toString().toString()).commit();
            loginEdit.putString("password",password.getText().toString().toString()).commit();
            loginEdit.commit();
        }else{
            loginEdit.clear();
            //不选中
        }
        loginEdit.putString("flag",flag).commit();
    }
    Handler loginhand = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                        loginput();//登录成功就跳转然后根据是否记住密码保存登录信息
                        Intent  intent = new Intent(LoginActivity.this, DocumentActivity.class);
                        startActivity(intent);
                        finish();
                    break;
                case 1:
                    Toast.makeText(LoginActivity.this, "登录失败,请重新登录!", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };//handle
}
