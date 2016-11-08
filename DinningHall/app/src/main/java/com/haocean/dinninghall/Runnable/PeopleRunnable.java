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
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by haocean on 2016/11/3.
 */
public class PeopleRunnable implements Runnable {
    private String job="",department="",address="",typeMan="";
    static String contacts="";
    public SharedPreferences loginShare;
    Handler handler;
    public PeopleRunnable(String job, String department, String typeMan, Handler handler){
        this.job=job;
        this.department=department;
        this.typeMan=typeMan;
        this.handler=handler;
        loginShare = AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        this.address=loginShare.getString("address","");
    }
    public static String getContacts(){
        return contacts;
    }
    public void getData(){
        HttpClient httpClient = new DefaultHttpClient();

         String   url=address+typeMan+"Android/getMorningList?jobtype="+job+"&department="+department;
        System.out.println("----PeopleRunnable 的url-----"+url);

        HttpPost httpPost = null;

        httpPost = new HttpPost(url);


        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("User-Agent", "android");
        httpPost.setHeader("Cookie", "PHPSESSID="+ UserData.getJession());

        HttpResponse response;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntityPublic = response.getEntity();
            contacts = EntityUtils.toString(responseEntityPublic,"utf-8");
            System.out.println("contacts----people-------"+contacts);
            handler.obtainMessage(0).sendToTarget();
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
