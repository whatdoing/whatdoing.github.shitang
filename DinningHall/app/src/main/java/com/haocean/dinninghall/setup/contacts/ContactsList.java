package com.haocean.dinninghall.setup.contacts;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.ContactsRunnable;
import com.haocean.dinninghall.adapter.ContactsAdapter;
import com.haocean.dinninghall.adapter.SortAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ContactsList extends Activity {

    private ContactsAdapter mAdapter;
    private ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String,Object>>();
    private ListView listView;
    private SortAdapter sortadapter;
    private List<PersonBean> data;
    private SideBar sidebar;
    private TextView dialog;
private String[] d;


    public void init(){


        sidebar = (SideBar) findViewById(R.id.sidebar);
        listView = (ListView) findViewById(R.id.listview1);
        dialog = (TextView) findViewById(R.id.dialog);
        sidebar.setTextView(dialog);

    }
    private List<PersonBean> getData(String[] data) {
        List<PersonBean> listarray = new ArrayList<PersonBean>();
        for (int i = 0; i < data.length; i++) {
            String pinyin = PinyinUtils.getPingYin(data[i]);

            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            PersonBean person = new PersonBean();

            person.setName(data[i]);
            person.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                person.setFirstPinYin(Fpinyin);
            } else {
                person.setFirstPinYin("#");
            }

            listarray.add(person);
        }
        return listarray;

    }
    //跑数据的线程
    private void Runables(){
        ContactsRunnable contactsRunnable=new ContactsRunnable(ContactsList.this);
        contactsRunnable.createHand(handContacts);
        Thread dataThread=new Thread(contactsRunnable);
        dataThread.start();
    }
    //hand消息 巡查列表显示
    Handler handContacts = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String strList= ContactsRunnable.getContacts();
                    // 解析json，
                    try {
                        JSONArray jsonAry = new JSONArray(strList);
                        listItem.clear();

                        d=new String[jsonAry.length()];

                        for(int i = 0;i<jsonAry.length();i++){
                            JSONObject temp = (JSONObject) jsonAry.get(i);
                            HashMap<String, Object> map = new HashMap<String, Object>();
                            map.put("username",temp.getString("username").toString());
                            map.put("email",temp.getString("email"));
                            map.put("phone",temp.getString("phone") );

                            listItem.add(map);
                            d[i]=temp.get("username").toString();
                        }
                        System.out.println("***---"+d);
                        data=getData(d);
                        // 数据在放在adapter之前需要排序
                        Collections.sort(data, new PinyinComparator());
                        sortadapter = new SortAdapter(ContactsList.this, data,listItem);
                        listView.setAdapter(sortadapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    Toast.makeText(ContactsList.this, "系统维护数据无法查询到，请稍后重试！", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    listItem.clear();
                    mAdapter = new ContactsAdapter(ContactsList.this,listItem);
                    listView.setAdapter(mAdapter);//为ListView绑定Adapter
                    Toast.makeText(ContactsList.this, "无数据", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        init();
        Runables();

        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {

                // 该字母首次出现的位置
                int position = sortadapter.getPositionForSelection(s.charAt(0));

                if (position != -1) {
                    listView.setSelection(position);
                }
            }
        });



    }

}
