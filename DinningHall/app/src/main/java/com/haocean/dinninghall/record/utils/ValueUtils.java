package com.haocean.dinninghall.record.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.Contexts;
import com.haocean.dinninghall.contexts.RecordList;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haocean on 2016/10/8.
 */
public class ValueUtils {



    public static  Object setValue(String typeRecord, Activity activity){
        Object   object =null;
        try {
            Resources res = activity.getResources();
            int arrid= R.array.class.getField(typeRecord).getInt(null);//arrays.xml(中类型的id)
            String[] strSHArea = res.getStringArray(arrid);
            Class   clazz= RecordList.Entity.get(typeRecord);
            object  =clazz.newInstance();
            Field[]  fields=clazz.getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);

                System.out.println("strShArea"+strSHArea);
                if(Arrays.asList(strSHArea).contains(field.getName())){
                    int id= R.id.class.getField(field.getName()).getInt(null);
                    View view=activity.findViewById(id);
                    if(view instanceof EditText){
                        field.set(object,((EditText) view).getText().toString());
                    }else if(view instanceof Button){
                        field.set(object,((Button) view).getText().toString());
                    }

                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static  Object getValue(String typeRecord, View views, String data)  {
        Object   object =null;
        try {
            JSONObject jsonObject=new JSONObject(data);
            Class   clazz= RecordList.Entity.get(typeRecord);//哪个类
            object  =clazz.newInstance();//实例化 实体类
            Resources res = AppController.getInstance().getResources();
            int arrid= R.array.class.getField(typeRecord).getInt(null);//arrays.xml(中类型的id)
            String[] strSHArea = res.getStringArray(arrid);
            Field[]  fields=clazz.getDeclaredFields();
            System.out.println(strSHArea);
            for(Field field:fields){
                field.setAccessible(true);
                String value=jsonObject.getString(field.getName());
                System.out.println(1+"fieldname-----"+field.getName()+"--------value----------"+value);
                field.set(object,value);
                System.out.println("-------okokokook------");
                if(Arrays.asList(strSHArea).contains(field.getName())){
                    System.out.println("-------aaaaa------");
                    int id= R.id.class.getField(field.getName()).getInt(null);
                    System.out.println("id"+id);
                    View view=views.findViewById(id);
                    System.out.println("view"+view);
                    if(view instanceof EditText){
                        System.out.println("11111111111111");
                        ((EditText) view).setText(value);
                    }else if(view instanceof Button){
                        System.out.println("2222222222222");
                        ((Button) view).setText(value);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return object;
    }
    static  Map<String,String>  uploadimg=new HashMap<String, String>();
    //获得所有控件然后取数据
    public static Map<String,String> getAllChildViews(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                if(viewchild instanceof EditText){
                    String [] strids=vp.findViewById(viewchild.getId()).toString().split("/");
                    String strKey=strids[1].substring(0,strids[1].length()-1);
                    String strValue=((EditText) viewchild).getText().toString();
                    System.out.println("----value里的数据---"+strValue+"控件名称"+strKey);
                    uploadimg.put(strKey,strValue);
                    System.out.println("---upload---"+uploadimg);
                }
                if(viewchild instanceof Button){
                    String [] strids=vp.findViewById(viewchild.getId()).toString().split("/");
                    String strKey=strids[1].substring(0,strids[1].length()-1);
                    String strValue=((Button)viewchild).getText().toString();
                    if(!strValue.contains("提交")&&!strKey.contains("submit")){
                        uploadimg.put(strKey,strValue);
                    }
                }
                uploadimg.size();
                getAllChildViews(viewchild);
            }
        }
        return uploadimg;
    }
}
