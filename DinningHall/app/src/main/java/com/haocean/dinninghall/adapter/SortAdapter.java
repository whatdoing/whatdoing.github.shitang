package com.haocean.dinninghall.adapter;

/**
 * Created by Administrator on 2016/9/30 0030.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.setup.contacts.PersonBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SortAdapter extends BaseAdapter {
    private Context context;
    private List<PersonBean> persons;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listItem;

    public SortAdapter(Context context, List<PersonBean> persons, ArrayList<HashMap<String, Object>> listItem) {
        this.context = context;
        this.persons = persons;
        this.inflater = LayoutInflater.from(context);
        this.listItem=listItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        PersonBean person = persons.get(position);
        if (convertView == null) {
            viewholder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_contacts_item, null);
            viewholder.tv_tag = (TextView) convertView
                    .findViewById(R.id.tv_lv_item_tag);
            viewholder.header = (TextView) convertView
                    .findViewById(R.id.header);
            viewholder.tv_name = (TextView) convertView
                    .findViewById(R.id.name);
            viewholder.tel = (TextView) convertView.findViewById(R.id.tel);

            viewholder.type = (TextView) convertView.findViewById(R.id.type);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        // 获取首字母的assii值
        int selection = person.getFirstPinYin().charAt(0);
        // 通过首字母的assii值来判断是否显示字母
        int positionForSelection = getPositionForSelection(selection);
        if (position == positionForSelection) {// 相等说明需要显示字母
            viewholder.tv_tag.setVisibility(View.VISIBLE);
            viewholder.tv_tag.setText(person.getFirstPinYin());

        } else {
            viewholder.tv_tag.setVisibility(View.GONE);

        }
        viewholder.header.setText(person.getFirstPinYin());
        viewholder.tv_name.setText(person.getName());
        viewholder.type.setText(listItem.get(position).get("email").toString());
        viewholder.tel.setText(listItem.get(position).get("phone").toString());

        return convertView;
    }

    public int getPositionForSelection(int selection) {
        for (int i = 0; i < persons.size(); i++) {
            String Fpinyin = persons.get(i).getFirstPinYin();
            char first = Fpinyin.toUpperCase().charAt(0);
            if (first == selection) {
                return i;
            }
        }
        return -1;

    }

    class ViewHolder {
        TextView tv_tag;
        TextView tv_name;
        TextView header;
        TextView tel;
        TextView type;
    }

}

