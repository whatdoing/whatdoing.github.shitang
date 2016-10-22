package com.haocean.dinninghall.Runnable;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.haocean.dinninghall.contexts.ManManagementList;
import com.haocean.dinninghall.manManagement.ManManagementIndexActivity;
import com.haocean.dinninghall.record.RecordListActivity;
import com.haocean.dinninghall.review.ReviewIndex;
import com.haocean.dinninghall.review.ReviewListActivity;
import com.haocean.dinninghall.safety.SafetyListActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;


public class RecordRunnable implements Runnable {
    public SharedPreferences loginShare;
    private SharedPreferences.Editor loginEdit;
    private String curIP,jsession,docId;
    private RecordListActivity activity;
    private ManManagementIndexActivity manManagementIndexActivity;
    Map<String, String> data;
    private   String contacts,firstime="",lasttime="";
    private String url;
    private String typeMan=null;
    private ReviewListActivity reviewListActivity;
    private SafetyListActivity safetyListActivity;
    String address="";
    public void createHand(Activity activity,Map<String, String> data){
        this.activity=(RecordListActivity)activity;
        this.data=data;
        System.out.println("----搜索的data-----"+data);

        loginShare = activity.getSharedPreferences("loginFile", Context.MODE_PRIVATE);

        this.address=loginShare.getString("address","");

        /*this.userShareInfo = activity.getSharedPreferences("userFile", Context.MODE_PRIVATE);

        this.curIP=userShareInfo.getString("curIP", "").toString().trim();
        this.jsession=userShareInfo.getString("jsession", "");*/
    }
    public void setManagerType(Activity activity,String typeMan,Map<String, String> data){
        this.manManagementIndexActivity=(ManManagementIndexActivity)activity;
        this.typeMan=typeMan;
        this.data=data;

    }
    public void setSafetyType(Activity activity,String typeMan,Map<String, String> data){
        this.safetyListActivity=(SafetyListActivity) activity;
        this.typeMan=typeMan;
        this.data=data;
    }
    public void setReviewType(Activity activity,String typeMan,Map<String, String> data){
        this.reviewListActivity=(ReviewListActivity) activity;
        this.typeMan=typeMan;
        this.data=data;
    }

    public void getDataList(){
        HttpClient httpClient = new DefaultHttpClient();
        if(typeMan==null){
            url="http://192.168.199.142/4.1banben/"+activity.getTypeRecord()+"Android/listInfo?firstime="+firstime+"&lastime="+lasttime;
        }else{
            url="http://192.168.199.142/4.1banben/"+typeMan+"Android/listInfo?";
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
            contacts = EntityUtils.toString(responseEntityPublic,"utf-8");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("nnnnnnn-"+contacts);

        if(contacts.contains(":") && contacts!="null") {

        }else if(contacts.contains("[]")){

            contacts="[]";
            activity.updataButtomList("[]");
        }else{

            contacts=null;
        }
        if(typeMan==null){
            activity.updataButtomList(contacts);
        }
        else if(typeMan.equals("MorningInspection")||typeMan.equals("Roster")){

            System.out.println("--cccc----"+contacts);
            manManagementIndexActivity.updataButtomList(contacts);
        }
        else if(typeMan.equals("ReviewRecord")){
            reviewListActivity.updataButtomList(contacts);
        }
        else{
            safetyListActivity.updataButtomList(contacts);
        }
    }

    @Override
    public void run(){

        getDataList();
    }
}
