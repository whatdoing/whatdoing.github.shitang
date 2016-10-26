package com.haocean.dinninghall.review.leftOrRightFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.safety.SafetyListActivity;

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
		for (int i = 0; i < RecordList.ReviewArrStr.size(); i++) {
		String key=	RecordList.ReviewArrStr.get(i);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemId", i);
			map.put("itemText", RecordList.ReviewLists.get(key));
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
				if(getActivity() instanceof SafetyListActivity) {
					HashMap<String, Object> map = (HashMap<String, Object>) parent.getItemAtPosition(position);
					int i = (Integer) map.get("itemId");
					onItemListener mListener=(onItemListener)getActivity();
					mListener.onOKItem(RecordList.ReviewArrStr.get(i));


				}
			}
		});
	}

}
