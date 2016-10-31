package com.haocean.dinninghall.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.publicMethod.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridAdapter extends BaseAdapter {
	private Context mContext;
public List<String>  _text=new ArrayList<String>();
	public String[] img_text = { "rygl", "gcjl", "glrz", "yhbg"};
	public int[] imgs = { R.mipmap.man_manger, R.mipmap.record,
			R.mipmap.app_phonecharge, R.mipmap.app_creditcard,
			R.mipmap.app_movie, R.mipmap.app_lottery,
			R.mipmap.app_facepay, R.mipmap.app_close, R.mipmap.app_plane };

	public MyGridAdapter(Context mContext) {
		super();
		try {
			for(int i=0;i<img_text.length;i++){
				if(UserData.getJurisdiction().contains(img_text[i])){
					int 	strid=  R.string.class.getField(img_text[i]).getInt(null);
					_text.add(mContext.getString(strid));
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
//		<string name="rygl">人员管理</string>
//		<string name="gcjl">过程记录</string>
//		<string name="glrz">自查日志</string>
//		<string name="yhbg">隐患报告</string>
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return _text.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_item, parent, false);
		}
		TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(imgs[position]);

		tv.setText(_text.get(position));
		return convertView;
	}

}
