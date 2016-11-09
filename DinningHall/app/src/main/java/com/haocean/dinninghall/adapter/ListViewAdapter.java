package com.haocean.dinninghall.adapter;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.Runnable.DeleteRunnable;
import com.haocean.dinninghall.adapter.recordview.FindIdAnno;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.manManagement.CreateManActivity;
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.review.CreateReviewActivity;
import com.haocean.dinninghall.safety.BottomListFragment;
import com.haocean.dinninghall.safety.CreateSafetyActivity;
import com.haocean.dinninghall.safety.DetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haocean on 2016/7/14.
 */
public class ListViewAdapter extends BaseAdapter {


    /** 新建一个类继承BaseAdapter，实现视图与数据的绑定
     */
    Intent intent;
    private int intposition;
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private String typeRecord;
    private Activity context;
    private   JSONArray jsonAry;
    /**构造函数*/
    public ListViewAdapter(String listItem, String typeRecord, Activity context) {

        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.typeRecord=typeRecord;

        try {
            jsonAry = new JSONArray(listItem);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setJsonAry(String listItem,String typeRecord){
        try {
            this.typeRecord=typeRecord;
            jsonAry = new JSONArray(listItem);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONArray getJsonAry(){
        return jsonAry;
    }

    @Override
    public int getCount() {
        return jsonAry.length();//返回数组的长度
    }

    @Override
    public Object getItem(int position) {
        return null;

    }

    @Override

    public long getItemId(int position) {

        return 0;

    }
    Handler deleteHandler=new Handler(){
        @TargetApi(Build.VERSION_CODES.KITKAT)
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    jsonAry.remove(intposition);
                    try {
                        Method m = context.getClass().getMethod("dataChanged");
                         m.invoke(context);
                        Method c = context.getClass().getMethod("setCount");
                        c.invoke(context);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context,"删除成功",Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(context, "记录无法删除，请稍后重试！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    /**书中详细解释该方法*/

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            System.out.println("个数有多少？"+typeRecord);
            Class   clazz= RecordList.Class.get(typeRecord);

            Object holder=null;
            Field[]  fields=clazz.getDeclaredFields();
            //观察convertView随ListView滚动情况

            if (convertView == null) {
                convertView = mInflater.inflate( RecordList.Layout.get(typeRecord),null);
                holder=clazz.newInstance();

                for(Field field:fields){
                    field.setAccessible(true);
                    //判断该属性上是否有FindIdAnno这个注解的类类型
                    FindIdAnno anno = field.getAnnotation(FindIdAnno.class);
                    //如果有就会拿到对应的注解信息，没有就会返回空
                    if(anno != null){
                        //判断该属性是否是属于View的子类类型，并且不是静态属性
                        if(View.class.isAssignableFrom(field.getType()) && !Modifier.isStatic(field.getModifiers())){
                            //拿到注解上的控件ID
                            int viewId = anno.value();
                           if(field.getName().contains("bianji")||field.getName().contains("shanchu")){
                                field.set(holder,(ImageView) convertView.findViewById(viewId));
                            }else{
                                field.set(holder,(TextView) convertView.findViewById(viewId));
                            }

                        }
                    }
                }
                convertView.setTag(holder);//绑定ViewHolder对象
            }
            else{
                holder = convertView.getTag();//取出ViewHolder对象
            }
            final JSONObject  temp = (JSONObject) jsonAry.get(position);
            for(Field field:fields){
                field.setAccessible(true);
                //判断该属性上是否有FindIdAnno这个注解的类类型
                FindIdAnno anno = field.getAnnotation(FindIdAnno.class);
                //如果有就会拿到对应的注解信息，没有就会返回空
                if(anno != null){
                    //判断该属性是否是属于View的子类类型，并且不是静态属性
                    if(View.class.isAssignableFrom(field.getType()) && !Modifier.isStatic(field.getModifiers())){
                        //拿到注解上的控件ID
                        int viewId = anno.value();

                           final String id=temp.getString("id");

                            final String tempString=temp.toString();
                        if(field.getName().contains("bianji")){
                            ( (ImageView)field.get(holder)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Map<String,String> map = new HashMap<String, String>();
                                        map.put("tempString",tempString);
                                        map.put("typeRecord",typeRecord);
                                        map.put("id",id);
                                        map.put("num","2");
                                        onEditButton editButton=(onEditButton)context;
                                        editButton.EditButton(map);

                                    }
                                });
                        }else if(field.getName().contains("shanchu")){
                            ((ImageView)field.get(holder)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        System.out.println(id+"id");
                                        DeleteRunnable deleteRunnable=new DeleteRunnable();
                                        intposition=position;
                                        deleteRunnable.getHand(deleteHandler);
                                        deleteRunnable.setTypeRecord(typeRecord);
                                        deleteRunnable.getid(id);
                                        Thread thread = new Thread(deleteRunnable);
                                        MyDialogs.isAlertDialog("您确定删除此条记录？",context,thread);

                                    }
                                });
                        }else{
                            ((TextView)field.get(holder)).setText(temp.getString(field.getName()));
                        }

                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;

    }

    public interface onEditButton {
        void EditButton(Map<String,String> map);
    }

}










