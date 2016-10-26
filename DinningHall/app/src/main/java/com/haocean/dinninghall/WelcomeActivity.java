package com.haocean.dinninghall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONException;
import org.json.JSONObject;

import im.fir.sdk.FIR;
import im.fir.sdk.VersionCheckCallback;

public class WelcomeActivity extends Activity {

    private SharedPreferences isFirst;
    private SharedPreferences.Editor editor ;
    private Boolean isF;
    private Intent intent;


    public Float getVersion() {
         try {
                   PackageManager manager = this.getPackageManager();
                  PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
                Float version = Float.valueOf(info.versionName);
                  return version;
             } catch (Exception e) {
                  e.printStackTrace();
               return null;
              }
       }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);






        // 进来后先写一个true进去，用来判断是否第一次登录这个APP
        isFirst = getSharedPreferences("lxt", Context.MODE_PRIVATE);

        editor = isFirst.edit();// 获取编辑器

//        FIR.checkForUpdateInFIR("d1cd90368dbd4dddbc5fba129b123316" , new VersionCheckCallback() {
//            @Override
//            public void onSuccess(String versionJson) {
//                Log.i("fir","check from fir.im success! " + "\n" + versionJson);
//                try {
//                    JSONObject jsonObject=new JSONObject(versionJson);
//                    editor.putString("installUrl", jsonObject.getString("installUrl"));
//                    editor.putString("changelog", jsonObject.getString("changelog"));
//                   Float netVersion=Float.valueOf(jsonObject.getString("versionShort"));
//                    editor.putString("netVersion",netVersion.toString());
//                    Float pageVersion=getVersion();
//                    if(pageVersion!=null){
//                        editor.putString("pageVersion", pageVersion.toString());
//                    }
//                    if(netVersion>pageVersion) {
//                        editor.putBoolean("isUp", true);
//                    }else{
//                        editor.putBoolean("isUp", false);
//                    }
//                    editor.commit();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFail(Exception exception) {
//                Log.i("fir", "check fir.im fail! " + "\n" + exception.getMessage());
//            }
//
//            @Override
//            public void onStart() {
//                // Toast.makeText(getApplicationContext(), "正在获取", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFinish() {
//                //Toast.makeText(getApplicationContext(), "获取完成", Toast.LENGTH_SHORT).show();
//            }
//        });


        //读取,缺省返回为true
        isF = isFirst.getBoolean("isfirst", true);
        if (isF) {// 第一次
            editor.putBoolean("isfirst", false).commit();
            // 此方法开始动画
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    intent = new Intent(WelcomeActivity.this, ViewPagerActivity.class);
                    startActivity(intent);
                    WelcomeActivity.this.finish();
                }
            }, 2000);
        } else {
            // Toast.makeText(WelcomeActivity.this, "不是第一次",0).show(); //此方法开始动画
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    WelcomeActivity.this.finish();
                }
            }, 2000);

        }


    }


}
