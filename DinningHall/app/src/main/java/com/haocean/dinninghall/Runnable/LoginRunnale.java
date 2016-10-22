package com.haocean.dinninghall.Runnable;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;


import com.haocean.dinninghall.contexts.Contexts;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haocean on 2016/7/8.
 */


public class LoginRunnale implements Runnable {
    private Handler loginhand;
    public SharedPreferences userShare;
    private SharedPreferences.Editor userEdit;
    private Context context;

    private boolean loginSuccess=false;
    private String username;
    private String password;
    private String httpurl;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRunnale(Context context, String httpurl, Handler loginhand) {
        userShare = context.getSharedPreferences("userFile", Context.MODE_PRIVATE);
        userEdit = userShare.edit();


        this.httpurl = httpurl;
        this.context = context;
        this.loginhand=loginhand;
    }



    @Override
    public void run() {
        if (login(httpurl + "/LoginAndroid/login", username, password)) {
            //登录UI操作
            loginhand.obtainMessage(0).sendToTarget();
        } else {
            loginhand.obtainMessage(1).sendToTarget();
        }
    }

    //登录需要
    public boolean login(String strUrl, String username, String password) {

        /**
         * 完成form表单的提交
         */
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(strUrl);//login的uri,
        System.out.println("----登录-----"+strUrl);
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");// 添加请求头部信息,和普通http网页请求区分开来
        httpPost.setHeader("User-Agent", "android");
        try {
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("ajax", "ajax"));// 区分普通http網頁
            parameters.add(new BasicNameValuePair("username", username));
            parameters.add(new BasicNameValuePair("password", password));

            HttpEntity entity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            // 得到服务器响应实体对象
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String context = EntityUtils.toString(responseEntity,"utf-8");// 获取资源

                System.out.println("this is context:" + context);//以后可以根据这个判断登录的状态

                //这个context判断是否包含登录成功
                if (context.contains("ok")) {
                    JSONObject jsonObject=new JSONObject(context);
                    userEdit.putString("id",jsonObject.getString("id")).commit();
                  //  Contexts.userId=jsonObject.getString("id");
                System.out.println("http://192.168.199.142/4.1banben/UserAndroid/listInfo?id="+jsonObject.getString("id"));

                    //一定要在这里加,,同一个cookie队列
                   httpPost = new HttpPost("http://192.168.199.142/4.1banben/UserAndroid/listInfo?id="+jsonObject.getString("id"));
                    httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
                    httpPost.setHeader("User-Agent", "android");

                    HttpResponse res = httpClient.execute(httpPost);
                    HttpEntity responseEntityPublic = res.getEntity();
                    String userInfo= EntityUtils.toString(responseEntityPublic,"utf-8");
                    JSONArray json=new JSONArray(userInfo);
                   JSONObject jsonobject= json.getJSONObject(0);
                    userEdit.putString("username",jsonobject.getString("username")).commit();
                    userEdit.putString("realname",jsonobject.getString("realname")).commit();
                    userEdit.putString("sex",jsonobject.getString("sex")).commit();
                    userEdit.putString("role",jsonobject.getString("role")).commit();
                    userEdit.putString("email",jsonobject.getString("email")).commit();
                    userEdit.putString("phone",jsonobject.getString("phone")).commit();
                    userEdit.putString("url",jsonobject.getString("url")).commit();
                    userEdit.putString("school_name",jsonobject.getString("school_name")).commit();
                    System.out.println("jsonobject.getString(school_name)"+jsonobject.getString("school_name"));
                    loginSuccess = true;
                } else {
                    loginSuccess = false;
                }
            } else {
                loginSuccess = false;
            }
            return loginSuccess;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            httpClient.getConnectionManager().shutdown();
        }
        return loginSuccess;


    }// login登录需要


}
