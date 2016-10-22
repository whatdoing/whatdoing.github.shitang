package com.haocean.dinninghall.Runnable;

import android.content.Context;
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
 * Created by Administrator on 2016/9/29 0029.
 */
public class ContactsRunnable implements Runnable{
    private SharedPreferences userShareInfo;
    private String curIP,jsession,docId;
    private Context context;
    private Handler handSupervise;
    private static  String contacts = "";
    //private static  String contacts="[{\"createDate\":\"1995-02-17\",\"tablewarename\":\"大碗\",\"tablewarecount\":\"21\",\"cleantype\":\"手工清洗\",\"startDate\":\"1996-02-17\",\"endDate\":\"1997-02-03\",\"cleanCondition\":\"未保洁\",\"liablePerson\":\"刘小平\"},{\"createDate\":\"1995-02-17\",\"tablewarename\":\"大碗\",\"tablewarecount\":\"21\",\"cleantype\":\"手工清洗\",\"startDate\":\"1996-02-17\",\"endDate\":\"1997-02-03\",\"cleanCondition\":\"未保洁\",\"liablePerson\":\"刘小平\"},{\"createDate\":\"1995-02-17\",\"tablewarename\":\"大碗\",\"tablewarecount\":\"21\",\"cleantype\":\"手工清洗\",\"startDate\":\"1996-02-17\",\"endDate\":\"1997-02-03\",\"cleanCondition\":\"未保洁\",\"liablePerson\":\"刘小平\"}]";


    public static String getContacts() {
        return contacts;
    }
    public ContactsRunnable(Context context){
        this.context=context;
    }
    public void createHand(Handler handSupervise){
        this.handSupervise = handSupervise;
        this.userShareInfo = context.getSharedPreferences("userFile", Context.MODE_PRIVATE);
        this.curIP=userShareInfo.getString("curIP", "").toString().trim();
        this.jsession=userShareInfo.getString("jsession", "");
    }
    public void getDataList(){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://192.168.199.142/4.1banben/UserAndroid/listInfo");
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("User-Agent", "android");
        HttpResponse response;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntityPublic = response.getEntity();
            contacts = EntityUtils.toString(responseEntityPublic,"utf-8");
            System.out.println("---------"+contacts);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 发送适配器
        if (contacts.length() > 4) {
            handSupervise.obtainMessage(0).sendToTarget();
        }
        else
        {
            contacts="";
            handSupervise.obtainMessage(2).sendToTarget();

        }

    }
    @Override
    public void run() {

            getDataList();
    }
}
