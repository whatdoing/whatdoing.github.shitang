package com.haocean.dinninghall.Runnable;

import android.content.SharedPreferences;
import android.os.Handler;

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
    private static SharedPreferences userShareInfo;
    private String curIP;
    private String jsession;

    private String id;
    private Handler handler;
    private String TypeRecord;
    public DeleteRunnable(){
        /*userShareInfo= AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        curIP=userShareInfo.getString("curIP", "");
        jsession=userShareInfo.getString("jsession", "");*/
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
        String url="http://192.168.199.142/4.1banben/"+TypeRecord+"Android/deleted?id="+ id;
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
