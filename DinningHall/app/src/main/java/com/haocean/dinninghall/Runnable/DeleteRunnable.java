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
 * Created by Administrator on 2016/10/12 0012.
 */
public class DeleteRunnable implements Runnable{
    private static SharedPreferences loginShare;


    private String id,address;
    private Handler handler;
    private String TypeRecord;
    public DeleteRunnable(){
        this.loginShare = AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        this.address=loginShare.getString("address","");
    }
    public void setTypeRecord(String TypeRecord){
        this.TypeRecord=TypeRecord;
    }

    public void getid(String id){
        this.id=id;
        System.out.println("-----删除的id----"+id);
    }
    public void getHand(Handler handler){
        this.handler=handler;

    }


    @Override
    public void run() {
        HttpClient httpClient = new DefaultHttpClient();
        String url=address+TypeRecord+"Android/deleted?id="+ id;
        System.out.println("--删除的url---"+url);
        HttpPost httpPost = new HttpPost(url);// 是ids??
        System.out.println("----**-*-*---");
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("User-Agent", "android");
        try {

            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity responseEntityPublic = response.getEntity();

            String str = EntityUtils.toString(responseEntityPublic,"utf-8");

            if (str.contains("ok")) {
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
