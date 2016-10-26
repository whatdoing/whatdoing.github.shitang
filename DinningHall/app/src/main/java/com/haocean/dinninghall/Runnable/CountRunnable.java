package com.haocean.dinninghall.Runnable;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.publicMethod.UserData;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class CountRunnable implements Runnable {
    public SharedPreferences loginShare;

    private  static String contacts;
    private String url;

    String address="",type="";
    Handler handler;
    public CountRunnable(Handler handler,String type){

        loginShare = AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);

        this.address=loginShare.getString("address","");
        this.handler=handler;
        this.type=type;
    }

    public static String getContacts() {

        return contacts;

    }

    public void getDataList(){
        HttpClient httpClient = new DefaultHttpClient();

        url=address+"CountAndroid/"+type;

        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("User-Agent", "android");
        httpPost.setHeader("Cookie", "PHPSESSID="+ UserData.getJession());

        HttpResponse response;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntityPublic = response.getEntity();
            this.contacts = EntityUtils.toString(responseEntityPublic,"utf-8");
            System.out.println("----gungungugn----"+this.contacts);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("nnnnnnn---------"+contacts);
        if(contacts.contains(":") && contacts!="null") {
            handler.obtainMessage(0).sendToTarget();
        }else if(contacts.contains("[]")){
            handler.obtainMessage(1).sendToTarget();
        }else{
            handler.obtainMessage(2).sendToTarget();
        }



    }

    @Override
    public void run(){

        getDataList();
    }
}
