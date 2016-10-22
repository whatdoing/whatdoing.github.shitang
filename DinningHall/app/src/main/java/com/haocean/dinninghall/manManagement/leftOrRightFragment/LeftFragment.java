package com.haocean.dinninghall.manManagement.leftOrRightFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.ManManagementList;

import com.haocean.dinninghall.manManagement.ManManagementIndexActivity;


import java.util.ArrayList;
import java.util.HashMap;

public class LeftFragment extends Fragment {
	View _RootView;
	private ListView list;
	ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

	public interface onItemListener {
		void onOKItem(String data);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			_RootView = inflater.inflate(R.layout.left_fragment_menu, null);
		initView();
		return _RootView;
	}
	private void initView() {
		//简单的构建菜单实例
		for (int i = 0; i < ManManagementList.ManManagementStr.size(); i++) {
		String key=	ManManagementList.ManManagementStr.get(i);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemtype", key);
			map.put("itemText", ManManagementList.ManManagementLists.get(key));
			map.put("itemImage", R.mipmap.add_text);
			data.add(map);
		}
		list = (ListView) _RootView.findViewById(R.id.menu_list);



		SimpleAdapter simperAdapter = new SimpleAdapter(getActivity(), data,
				R.layout.item_menu, new String[] { "itemImage", "itemText" },
				new int[] { R.id.menuitem_image, R.id.menuitem_text });
		list.setAdapter(simperAdapter);


		list.setSelector(R.drawable.menu_selector);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				if(getActivity() instanceof ManManagementIndexActivity) {
					HashMap<String, Object> map = (HashMap<String, Object>) parent.getItemAtPosition(position);
					String itemtype = (String) map.get("itemtype");
					onItemListener mListener=(onItemListener)getActivity();
					mListener.onOKItem(itemtype);

				//	Fragment frag = getFragment(i);//获取对应页面的Fragment
				//	((RecordListActivity) getActivity()).switchContent(frag);
				}
			}
		});
	}

}
