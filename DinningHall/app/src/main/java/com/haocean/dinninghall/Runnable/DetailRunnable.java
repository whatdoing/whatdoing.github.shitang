package com.haocean.dinninghall.Runnable;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.haocean.dinninghall.AppController;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2016/10/25 0025.
 */
public class DetailRunnable implements Runnable {
    private static SharedPreferences loginShare;


    private String id,address,contacts;
    private Handler handler;
    private String TypeRecord;
    public DetailRunnable(String TypeRecord,String id,Handler handler){
        this.loginShare = AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        this.address=loginShare.getString("address","");
        this.TypeRecord=TypeRecord;
        this.id=id;
        this.handler=handler;
    }

    public String getContacts() {
        return contacts;
    }

    @Override
    public void run() {
        HttpClient httpClient = new DefaultHttpClient();
        String url=address+TypeRecord+"Android/detail?id="+ id;
       System.out.println("-------"+url);
        HttpPost httpPost = new HttpPost(url);// 是ids??

        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("User-Agent", "android");
        try {

            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity responseEntityPublic = response.getEntity();

            contacts = EntityUtils.toString(responseEntityPublic,"utf-8");
System.out.println("ccccc-"+contacts);
            if (contacts.length()>4) {
                handler.obtainMessage(0).sendToTarget();
            } else {
                handler.obtainMessage(1).sendToTarget();
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
