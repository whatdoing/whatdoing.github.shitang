package com.haocean.dinninghall.setup.user;

import android.Manifest;
import android.app.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import android.view.View;
import android.view.Window;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.haocean.dinninghall.LoginActivity;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CreateRecordRunnable;
import com.haocean.dinninghall.Runnable.RecordRunnable;
import com.haocean.dinninghall.Runnable.UserRunnable;
import com.haocean.dinninghall.contexts.Contexts;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.record.utils.ValueUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by haocean on 2016/10/17.
 */
public class UserActivity extends Activity implements View.OnClickListener{
    LinearLayout avater,Realname,Sex,Phone,Email,quit;
    TextView username,realname,sex,role,phone,email,school_name;
    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;
    public static final int userto = 1;
    public static final int userok = 2;
    String typeMan="User";
    Intent intent;
    private ArrayList<String> mSelectPath;
    public  void init() {
        avater=(LinearLayout)findViewById(R.id.avater);
        quit=(LinearLayout)findViewById(R.id.quit);
        Realname=(LinearLayout)findViewById(R.id.Realname);
        Sex=(LinearLayout)findViewById(R.id.Sex);
        Phone=(LinearLayout)findViewById(R.id.Phone);
        Email=(LinearLayout)findViewById(R.id.Email);
        username=(TextView)findViewById(R.id.username);
        realname=(TextView)findViewById(R.id.realname);
        sex=(TextView)findViewById(R.id.sex);
        role=(TextView)findViewById(R.id.role);
        phone=(TextView)findViewById(R.id.phone);
        email=(TextView)findViewById(R.id.email);
        school_name=(TextView)findViewById(R.id.school_name);
    }
    public void data(){


            username.setText(UserData.getUsername());
            realname.setText(UserData.getRealname());
            sex.setText(UserData.getSex());
            role.setText(UserData.getRole());
            phone.setText(UserData.getPhone());
            email.setText(UserData.getEmail());
        school_name.setText(UserData.getSchoolName());


            RecordList.UserSave.put("realname",UserData.getRealname());
            RecordList.UserSave.put("sex",UserData.getSex());
            RecordList.UserSave.put("role",UserData.getRole());
            RecordList.UserSave.put("phone",UserData.getPhone());
            RecordList.UserSave.put("email",UserData.getEmail());


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user);
        init();
        data();

        avater.setOnClickListener(this);
        quit.setOnClickListener(this);
        Realname.setOnClickListener(this);
        Sex.setOnClickListener(this);
        Phone.setOnClickListener(this);
        Email.setOnClickListener(this);

    }
    //选头像
    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        }else {
            boolean showCamera = true;
           // int maxNum = 9;//选择数量


            MultiImageSelector selector = MultiImageSelector.create(UserActivity.this);
            selector.showCamera(showCamera);
           // selector.count(maxNum);

                selector.single();
                //selector.multi();//多个选择

            selector.origin(mSelectPath);
            selector.start(UserActivity.this, REQUEST_IMAGE);
        }
    }
    private void requestPermission(final String permission, String rationale, final int requestCode){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(UserActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for(String p: mSelectPath){
                    sb.append(p);
                    sb.append("\n");
                }

            }
        }
       else if(requestCode == UserActivity.userto){
            if(resultCode == UserActivity.userok){
                data();
            }
        }
    }
    private void jump(int i){
        intent=new Intent();
        intent.setClass(UserActivity.this,InfoActivity.class);
        intent.putExtra("typeMan",typeMan);
        intent.putExtra("num",i);
        startActivityForResult(intent, UserActivity.userto);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.avater: {
                pickImage();
            }
            break;
            case R.id.Realname: {
                jump(0);
            }
            break;
            case R.id.Sex: {
                jump(1);
            }
            break;
            case R.id.Phone: {
                jump(2);
            }
            break;
            case R.id.Email: {
                jump(3);
            }
            break;
            case R.id.quit: {
             Intent intent=new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
            finish();
            }
            break;
        }
    }
}
