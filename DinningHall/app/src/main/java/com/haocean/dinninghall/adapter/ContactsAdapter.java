package com.haocean.dinninghall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haocean.dinninghall.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactsAdapter extends BaseAdapter {

    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private ArrayList<HashMap<String, Object>> listItem;
    public ContactsAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        this.mInflater = LayoutInflater.from(context);
        this.listItem=listItem;
    }

    @Override
    public int getCount() {
        return listItem.size();//返回数组的长度
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

        final ContactsViewHolder holder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.list_contacts_item, null);

            holder = new ContactsViewHolder();

            /**得到各个控件的对象*/
            holder.header=(TextView)convertView.findViewById(R.id.header);
            holder.name= (TextView)convertView.findViewById(R.id.name);
            holder.tel = (TextView) convertView.findViewById(R.id.tel);

            holder.type = (TextView) convertView.findViewById(R.id.type);





            convertView.setTag(holder);//绑定ViewHolder对象

        } else {

            holder = (ContactsViewHolder) convertView.getTag();//取出ViewHolder对象

        }

        /**设置TextView显示的内容，即我们存放在动态数组中的数据*/

        holder.name.setText(listItem.get(position).get("name").toString());
        holder.type.setText(listItem.get(position).get("type").toString());
        holder.tel.setText(listItem.get(position).get("tel").toString());
       holder.header.setText(listItem.get(position).get("header").toString());


        return convertView;

    }

}
