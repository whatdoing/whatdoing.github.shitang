package com.haocean.dinninghall.Runnable;

import android.content.Context;
import android.os.Handler;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.publicMethod.UserData;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class UserRunnable implements Runnable {

    private String typeMan;
    Map<String,String> data;
    private Handler hand;
    public UserRunnable(Map<String, String> data, Handler hand, String typeMan){
        this.data=data;
        this.typeMan=typeMan;
        this.hand=hand;
    }


    private List<NameValuePair> getData(List<NameValuePair> parameters){
        parameters.add(new BasicNameValuePair("ajax", "ajax"));// 区分普通http網頁
        for (Map.Entry<String, String> entry : data.entrySet()) {
            parameters.add(new BasicNameValuePair( entry.getKey(), entry.getValue()));
        }
        return  parameters;
    }



    @Override
    public void run() {
        HttpClient httpClient = new DefaultHttpClient();

        String url="";
        if(UserData.getId()==null){

        }
        else{
            url="http://192.168.199.142/4.1banben/"+typeMan+"Android/save?id="+UserData.getId();
        }

        HttpPost httpPost = new HttpPost(url);
        System.out.println("----lalalala----");
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("User-Agent", "android");


        try {
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters=getData(parameters);


            HttpEntity entity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            // 得到服务器响应实体对象
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String context = EntityUtils.toString(responseEntity,"utf-8");// 获取资源
                System.out.println("那我这里获得的是什么呢？？？？"+context);
                if(context.contains("ok")){

                    hand.obtainMessage(0).sendToTarget();
                }else{

                    hand.obtainMessage(1).sendToTarget();
                }
            } else {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
