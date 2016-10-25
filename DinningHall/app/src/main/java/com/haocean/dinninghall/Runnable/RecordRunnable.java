package com.haocean.dinninghall.Runnable;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.haocean.dinninghall.contexts.Contexts;
import com.haocean.dinninghall.contexts.ManManagementList;
import com.haocean.dinninghall.manManagement.ManManagementIndexActivity;
import com.haocean.dinninghall.record.RecordListActivity;
import com.haocean.dinninghall.review.ReviewIndex;
import com.haocean.dinninghall.review.ReviewListActivity;
import com.haocean.dinninghall.safety.SafetyListActivity;
import com.haocean.dinninghall.setup.user.UserActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


public class RecordRunnable implements Runnable {
    public SharedPreferences loginShare;
    private SharedPreferences.Editor loginEdit;

    private Activity activity;

    Map<String, String> data;
    private   String contacts;
    private String url;
    private String typeMan=null;

    String address="";
    public void createHand(Activity activity,String typeMan,Map<String, String> data){


        this.typeMan=typeMan;
        this.activity=activity;
        this.data=data;
        System.out.println("----搜索的data-----"+data);

        loginShare = activity.getSharedPreferences("userFile", Context.MODE_PRIVATE);

        this.address=loginShare.getString("address","");


    }

    public void getDataList(){
        HttpClient httpClient = new DefaultHttpClient();
        if(typeMan==null){

        }else{
            url=address+typeMan+"Android/listInfo?";
            System.out.println("----RecordRunnable 的url-----"+url);
        }

        System.out.println("---RecordRunnable里的typeMan----"+typeMan);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            if(!entry.getKey().equals("searchOk")|| !entry.getKey().equals("searchBack")){
                url=url+"&"+entry.getKey()+"="+entry.getValue();
            }

        }


        System.out.println("----搜索的url----"+url);
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("User-Agent", "android");
       /* httpPost.setHeader("Cookie","JSESSIONID=" + jsession);*/
        HttpResponse response;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntityPublic = response.getEntity();
            System.out.println("-------------"+responseEntityPublic);
            contacts = EntityUtils.toString(responseEntityPublic,"utf-8");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(contacts.contains(":") && contacts!="null") {
        }else if(contacts.contains("[]")){
            contacts="[]";
        }else{
            contacts=null;
        }

        try {
            Class clazz=activity.getClass();
            Method method = clazz.getMethod("updataButtomList",String.class);
            method.invoke(activity,contacts);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

        getDataList();
    }
}
