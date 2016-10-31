package com.haocean.dinninghall.publicMethod;

import android.content.Context;
import android.content.SharedPreferences;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.contexts.Application;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class UserData {
    static SharedPreferences loginShare= AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);

//    {"addresstree":[],"xtsz":{"yhgl":"1111","jsgl":"1111","mkgl":"1111","xxgl":"1000"},"gcjl":{"yhgl":"1111","jsgl":"1111","mkgl":"1111","xxgl":"1000"},"rygl":{"yhgl":"1111","jsgl":"1111","mkgl":"1111","xxgl":"1000"},"glrz":{"yhgl":"1111","jsgl":"1111","mkgl":"1111","xxgl":"1000"},"yhbg":{"yhgl":"1111","jsgl":"1111","mkgl":"1111","xxgl":"1000"}}}

    public static String getCount() {
        return loginShare.getString("count",null);
    }
    public static String getJurisdiction(){
        return loginShare.getString("jurisdiction",null);
    }

    public static void setCount(String value) {
        loginShare.edit().putString("count",value).commit();
    }

    public static String getJession() {
        return loginShare.getString("jession",null);
    }

    public static String getPassword() {
        return loginShare.getString("password",null);
    }

    public static void setPassword(String value) {
        loginShare.edit().putString("password",value).commit();
    }

    public static String getUsername() {
        return  loginShare.getString("username",null);
    }

    public static void setUsername(String value) {
        loginShare.edit().putString("username",value).commit();
    }


      public static String getId(){

        return  loginShare.getString("id",null);
    }
    public static String getRealname(){
       // SharedPreferences loginShare= AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        return  loginShare.getString("realname",null);
    }
    public static String getSex(){
      //  SharedPreferences loginShare= AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        return  loginShare.getString("sex",null);
    }
    public static String getRole(){
      //  SharedPreferences loginShare= AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        return  loginShare.getString("role",null);
    }
    public static String getEmail(){
      //  SharedPreferences loginShare= AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        return  loginShare.getString("email",null);
    }
    public static String getPhone(){
      //  SharedPreferences loginShare= AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        return  loginShare.getString("phone",null);
    }
    public static String getUrl(){
      //  SharedPreferences loginShare= AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        return  loginShare.getString("url",null);
    }
    public static String getSchoolName(){
        //  SharedPreferences loginShare= AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE);
        return  loginShare.getString("school_name",null);
    }



    public static void setRealname(String value){
       loginShare.edit().putString("realname",value).commit();

    }
    public static void setSex(String value){
        loginShare.edit().putString("sex",value).commit();

    }
    public static void setRole(String value){
        loginShare.edit().putString("role",value).commit();

    }
    public static void setEmail(String value){
        loginShare.edit().putString("email",value).commit();

    }
    public static void setPhone(String value){
        loginShare.edit().putString("phone",value).commit();

    }
    public static void setUrl(String value){
        loginShare.edit().putString("url",value).commit();
    }
}
