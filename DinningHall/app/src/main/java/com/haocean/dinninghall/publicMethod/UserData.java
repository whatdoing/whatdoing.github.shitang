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


    public static String getCount() {
        return loginShare.getString("count",null);
    }

    public static void setCount(String value) {
        AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE).edit().putString("count",value).commit();
    }

    public static String getJession() {
        return loginShare.getString("jession",null);
    }


    public static String getPassword() {
        return loginShare.getString("password",null);
    }

    public static void setPassword(String value) {
        AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE).edit().putString("password",value).commit();
    }

    public static String getUsername() {
        return  loginShare.getString("username",null);
    }

    public static void setUsername(String value) {
        AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE).edit().putString("username",value).commit();
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
       AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE).edit().putString("realname",value).commit();

    }
    public static void setSex(String value){
        AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE).edit().putString("sex",value).commit();

    }
    public static void setRole(String value){
        AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE).edit().putString("role",value).commit();

    }
    public static void setEmail(String value){
        AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE).edit().putString("email",value).commit();

    }
    public static void setPhone(String value){
        AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE).edit().putString("phone",value).commit();

    }
    public static void setUrl(String value){
        AppController.getInstance().getSharedPreferences("userFile", Context.MODE_PRIVATE).edit().putString("url",value).commit();
    }
}