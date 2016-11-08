package com.haocean.dinninghall.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.DataList;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by haocean on 2016/11/3.
 */
public class TableAdapter  extends BaseAdapter {
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private Activity context;
    /**构造函数*/
    public TableAdapter( Activity context) {

        this.context=context;
        this.mInflater = LayoutInflater.from(context);


    }
    private class ViewHolder{
        TextView name;
        EditText count;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.alert, null);
            holder = new ViewHolder();
            /**得到各个控件的对象*/

            holder.name = (TextView) view.findViewById(R.id.tablewarename);

            holder.count = (EditText) view.findViewById(R.id.tablewarecount);

            String list= DataList.list;
            String[] arr=list.split(",");
            holder.name.setText(arr[i]);
            view.setTag(holder);//绑定ViewHolder对象
        }
        else{

            holder = (ViewHolder)view.getTag();//取出ViewHolder对象

        }
        return null;
    }
}
