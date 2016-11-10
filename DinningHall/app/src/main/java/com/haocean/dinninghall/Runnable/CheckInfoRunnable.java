package com.haocean.dinninghall.Runnable;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.haocean.dinninghall.publicMethod.UserData;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;


/**
 * Created by haocean on 2016/11/3.
 */
public class CheckInfoRunnable implements Runnable {
    public SharedPreferences loginShare;
    private SharedPreferences.Editor loginEdit;

    private Activity activity;
    public  static  String bujige;
    public  static  String contacts;
    private String url;
    private String typeMan=null;

    String address="";
    Handler handler;
    public void createHand(Activity activity,String typeMan,Handler handler){


        this.typeMan=typeMan;
        this.activity=activity;
    this.handler=handler;


        loginShare = activity.getSharedPreferences("userFile", Context.MODE_PRIVATE);

        this.address=loginShare.getString("address","");


    }
    public static String getContacts(){
        return contacts;
    }
    public static String getBujige(){
        return bujige;
    }
    public void getData(){
        url=address+typeMan+"Android/listInfo";
        System.out.println("----CheckInfo 的url-----"+url);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("User-Agent", "android");
        httpPost.setHeader("Cookie", "PHPSESSID="+ UserData.getJession());

        HttpResponse response;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntityPublic = response.getEntity();
            contacts = EntityUtils.toString(responseEntityPublic,"utf-8");


            if(contacts!=null){
                String u=address+"CheckLogAndroid/project";//bujige
                httpPost = new HttpPost(u);
                httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
                httpPost.setHeader("User-Agent", "android");
                httpPost.setHeader("Cookie", "PHPSESSID="+ UserData.getJession());

                httpClient = new DefaultHttpClient();

                response = httpClient.execute(httpPost);
                HttpEntity re = response.getEntity();
                String a=EntityUtils.toString(re,"utf-8");
                if(a!=null){
                    bujige = a;

                }else{

                }
                System.out.println("-*-*-*-*"+bujige);
                handler.obtainMessage(0).sendToTarget();

            }else{

            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void run() {
        getData();
    }
}
