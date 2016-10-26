package com.haocean.dinninghall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.publicMethod.UserData;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class SafetyAdapter extends BaseAdapter {
    String keys;
    private class ViewHolder{
        TextView name;
        TextView Mnumber;
        TextView Ynumber;
    }


    /** 新建一个类继承BaseAdapter，实现视图与数据的绑定
     */
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private JSONObject listItem;
    String strType;
    int num;
    /**构造函数*/
    public SafetyAdapter(String Jsondata,int num) {
        this.mInflater = LayoutInflater.from(AppController.getInstance());
        this.num=num;
        UserData.setCount(Jsondata);
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

            convertView = mInflater.inflate(R.layout.list_record_item,null);

            holder = new ViewHolder();

            /**得到各个控件的对象*/

            holder.name = (TextView) convertView.findViewById(R.id.recordname);

            holder.Mnumber = (TextView) convertView.findViewById(R.id.Mnumber);
            holder.Ynumber = (TextView) convertView.findViewById(R.id.Ynumber);
            try {
                if(num==1){
                    keys= RecordList.ReviewArrStr.get(position);
                    holder.name.setText(RecordList.ReviewLists.get(keys));
                }
                else if(num==2){
                    keys=RecordList.ArrStr.get(position);
                    holder.name.setText(RecordList.Lists.get(keys));
                }
                else{
                    keys= RecordList.SafetyArrStr.get(position);
                    holder.name.setText(RecordList.SafetyLists.get(keys));
                }
                System.out.println("--safe ada---"+keys);
                /**设置TextView显示的内容，即我们存放在动态数组中的数据*/

                strType=listItem.getString(keys);

                JSONObject jsonObject = new JSONObject(strType);

                holder.Mnumber.setText(jsonObject.getString("Mnumber"));
                holder.Ynumber.setText(jsonObject.getString("Ynumber"));
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
