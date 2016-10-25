package com.haocean.dinninghall.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.ManManagementList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by haocean on 2016/7/14.
 */
public class DocumentsAdapter extends BaseAdapter {

    private class ViewHolder{
        TextView name;
        TextView number;
    }


    /** 新建一个类继承BaseAdapter，实现视图与数据的绑定
     */
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private JSONObject listItem;

    /**构造函数*/
    public DocumentsAdapter(String Jsondata) {
        this.mInflater = LayoutInflater.from(AppController.getInstance());
        try {
            listItem =new JSONObject(Jsondata);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return listItem.length();//返回数组的长度
    }

    @Override
    public Object getItem(int position) {
        return null;

    }

    @Override

    public long getItemId(int position) {

        return 0;

    }




    /**书中详细解释该方法*/

    @Override

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        //观察convertView随ListView滚动情况

        //  Log.v("MyListViewBase", "getView " + position + " " + convertView);

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.list_man_management_item,null);

            holder = new ViewHolder();

            /**得到各个控件的对象*/

            holder.name = (TextView) convertView.findViewById(R.id.name);

            holder.number = (TextView) convertView.findViewById(R.id.number);
            try {
                String keys=ManManagementList.ManManagementStr.get(position);
                /**设置TextView显示的内容，即我们存放在动态数组中的数据*/
                holder.name.setText(ManManagementList.ManManagementLists.get(keys));
                holder.number.setText("共"+listItem.getString(keys)+"条");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            convertView.setTag(holder);//绑定ViewHolder对象

        }

        else{

            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象

        }
        return convertView;

    }

}










