package com.haocean.dinninghall.Runnable;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.haocean.dinninghall.AppController;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class CreateRecordRunnable implements Runnable  {
    private SharedPreferences loginShare;

    private Handler hand;
    private Object object;
    private String TypeRecord;
    private String id;
    private  String url,address;

    public CreateRecordRunnable(Handler hand, Object object){
        this.hand=hand;
        this.object=object;

        this.loginShare = AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        this.address=loginShare.getString("address","");
    }
    public void setTypeRecord(String TypeRecord){
        this.TypeRecord=TypeRecord;

    }
    public void setTypeRecord(String TypeRecord, String id){
        this.TypeRecord=TypeRecord;
        this.id=id;
        System.out.println("----编辑的id----"+id);
    }
    private List<NameValuePair>  getData(){

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();

        try {
        Class clazz=object.getClass();
        Field[]  fields=clazz.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            String value=(String) field.get(object);
            System.out.println("---进来了吗-----"+value);
            if(value!=null&&!value.contains("null")){
                parameters.add(new BasicNameValuePair(field.getName(), (String) field.get(object)));
            }
        }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return  parameters;
    }
    public void getDataList(){
        HttpClient httpClient = new DefaultHttpClient();
        System.out.println("---新建有没有id啊-----"+id);
        if(id==null){
           url=address+TypeRecord+"Android/save";
        }
        else{
            System.out.println("-------HAHAHA---------");
            url=address+TypeRecord+"Android/save?id="+id;
        }
        System.out.println("----编辑的url----"+url);
        HttpPost httpPost = new HttpPost(url);
        System.out.println("----lalalala----");
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("User-Agent", "android");

        try {
            List<NameValuePair> parameters = getData();
            HttpEntity entity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            // 得到服务器响应实体对象
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String context = EntityUtils.toString(responseEntity,"utf-8");// 获取资源
                System.out.println("---context-----"+context);
                if(context.contains("ok")){
                    hand.obtainMessage(0).sendToTarget();
                }else{
                    hand.obtainMessage(1).sendToTarget();
                }
            } else {
            }



        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void run(){

        getDataList();
    }
}
