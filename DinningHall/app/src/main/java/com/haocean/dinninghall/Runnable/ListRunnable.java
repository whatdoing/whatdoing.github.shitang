package com.haocean.dinninghall.Runnable;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.manManagement.ManManagementIndexActivity;
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.record.DataList;
import com.haocean.dinninghall.record.RecordListActivity;
import com.haocean.dinninghall.review.ReviewListActivity;
import com.haocean.dinninghall.safety.SafetyListActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class ListRunnable  implements Runnable {
    public SharedPreferences loginShare;


    public String contactsList;
    private String url;
    private String typeMan=null;

    String address="";
    Context context;
    public ListRunnable( Context context){
        this.context=context;

    }
    public void setTypeMan(String typeMan){
        this.typeMan=typeMan;

        loginShare = context.getSharedPreferences("userFile", Context.MODE_PRIVATE);
        this.address=loginShare.getString("address","");
        System.out.println("----address--"+address);
    }

    public void getDataList(){
        HttpClient httpClient = new DefaultHttpClient();
        if(typeMan==null){

        }else{
            url=address+typeMan+"Android/setBar";

        }

        System.out.println("---ListRunnable里的typeMan----"+typeMan);

System.out.println("-----url-----"+url);
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setHeader("Cookie", "PHPSESSID="+ UserData.getJession());
        httpPost.setHeader("User-Agent", "android");

        HttpResponse response;
        try {

            response = httpClient.execute(httpPost);
            HttpEntity responseEntityPublic = response.getEntity();
            contactsList = EntityUtils.toString(responseEntityPublic,"utf-8");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--cccc--"+contactsList);
        if(!contactsList.contains("{")){
            contactsList=contactsList.substring(2,contactsList.length()-1).replace("\"","");
            DataList.list=contactsList;
        }
        else{
            try {
                JSONObject dataJson = new JSONObject(contactsList);
                if(typeMan.equals("RecyclingProcess")) {
                    String company = dataJson.getJSONArray("recovery_company").toString();
                    String address = dataJson.getJSONArray("recovery_address").toString();
                    String contacts = dataJson.getJSONArray("contacts").toString();
                    String contactsphone = dataJson.getJSONArray("contactsphone").toString();


                    DataList.list=company.substring(1, company.length() - 1).replace("\"", "");
                    DataList.recovery_address = address.substring(1, address.length() - 1).replace("\"", "");
                    DataList.contacts = contacts.substring(1, contacts.length() - 1).replace("\"", "");
                    DataList.contactsphone = contactsphone.substring(1, contactsphone.length() - 1).replace("\"", "");

                }
                else{
                    String name = dataJson.getJSONArray("name").toString();
                    String manufacturer = dataJson.getJSONArray("manufacturer").toString();
                    String manufacture_date = dataJson.getJSONArray("manufacture_date").toString();
                    String quality = dataJson.getJSONArray("quality").toString();
                    String supplyunit = dataJson.getJSONArray("supplyunit").toString();
                    String purchase_date = dataJson.getJSONArray("purchase_date").toString();
                    String purchasenum = dataJson.getJSONArray("purchasenum").toString();


                    DataList.name = name.substring(1, name.length() - 1).replace("\"", "");
                    DataList.manufacturer = manufacturer.substring(1, manufacturer.length() - 1).replace("\"", "");
                    DataList.manufacture_date = manufacture_date.substring(1, manufacture_date.length() - 1).replace("\"", "");
                    DataList.quality = quality.substring(1, quality.length() - 1).replace("\"", "");
                    DataList.supplyunit = supplyunit.substring(1, supplyunit.length() - 1).replace("\"", "");
                    DataList.purchase_date = purchase_date.substring(1, purchase_date.length() - 1).replace("\"", "");
                    DataList.purchasenum = purchasenum.substring(1, purchasenum.length() - 1).replace("\"", "");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run(){

       getDataList();
    }
}
