package com.haocean.dinninghall.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.review.CreateReviewActivity;
import com.haocean.dinninghall.safety.CreateSafetyActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by haocean on 2016/7/14.
 */
public class ListViewAdapter extends BaseAdapter {


    /** 新建一个类继承BaseAdapter，实现视图与数据的绑定
     */
    Intent intent;
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private String typeRecord;
    private Context context;
    private   JSONArray jsonAry;
    /**构造函数*/
    public ListViewAdapter(String listItem, String typeRecord, Context context) {
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.typeRecord=typeRecord;

        try {
            jsonAry = new JSONArray(listItem);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
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

            Class   clazz= RecordList.Class.get(typeRecord);

            Object holder;
            //观察convertView随ListView滚动情况

            if (convertView == null) {

                convertView = mInflater.inflate( RecordList.Layout.get(typeRecord),null);
                System.out.println("首先还是得看类型"+typeRecord);
                holder=clazz.newInstance();
            /*  在这里动手的  */
                final JSONObject  temp = (JSONObject) jsonAry.get(position);
                System.out.println("难道他拒绝我了？"+temp);
                Field[]  fields=clazz.getDeclaredFields();
                System.out.println("----有没有东西啊-----"+fields);
                for(Field field:fields){
                    field.setAccessible(true);
                    System.out.println("到这边看看"+field.getName());
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

                            //编辑按钮
                            if(field.getName().contains("bianji")){
                                ImageView bianjiView=(ImageView) convertView.findViewById(viewId);

                                bianjiView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        System.out.println("-----adapter   typeRecord----"+typeRecord);
                                        if(typeRecord.equals("Roster")||typeRecord.equals("MorningInspection")){
                                            intent=new Intent(context,CreateManActivity.class);
                                        }
                                        else if(typeRecord.equals("CheckLog")||typeRecord.equals("RatingCriteria")||typeRecord.equals("SafetyReport")){
                                            intent=new Intent(context,CreateSafetyActivity.class);
                                        }
                                        else if(typeRecord.equals("ReviewRecord")){
                                            intent=new Intent(context,CreateReviewActivity.class);
                                        }
                                        else{
                                            intent=new Intent(context,CreateRecordActivity.class);
                                        }
                                        intent.putExtra("tempString",tempString);
                                        intent.putExtra("typeRecord",typeRecord);
                                        intent.putExtra("id",id);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        ((Activity)context).startActivity(intent);

                                    }
                                });
                                field.set(holder,bianjiView);
                            }else if(field.getName().contains("shanchu")){
                                final ImageView shanchuView=(ImageView) convertView.findViewById(viewId);


                                shanchuView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        System.out.println(id+"id");
                                        DeleteRunnable deleteRunnable=new DeleteRunnable();
                                        deleteRunnable.getHand(deleteHandler);
                                        deleteRunnable.setTypeRecord(typeRecord);
                                        deleteRunnable.getid(id);

                                        Thread thread = new Thread(deleteRunnable);

                                        MyDialogs.isAlertDialog("您确定删除此条记录？",context,thread);

                                    }
                                });
                                field.set(holder,shanchuView);
                            }else{
                                System.out.println("-------"+viewId);
                                TextView c= (TextView) convertView.findViewById(viewId);
                                c.setText(temp.getString(field.getName()));
                                field.set(holder,c);
                            }

                        }
                    }
                }
                convertView.setTag(holder);//绑定ViewHolder对象

            }
            else{
                holder = convertView.getTag();//取出ViewHolder对象
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



}










