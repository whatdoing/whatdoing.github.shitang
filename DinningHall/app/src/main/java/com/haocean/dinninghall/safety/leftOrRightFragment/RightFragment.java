package com.haocean.dinninghall.safety.leftOrRightFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.RecordListActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.review.ReviewListActivity;
import com.haocean.dinninghall.safety.SafetyListActivity;

import java.util.HashMap;
import java.util.Map;

public class RightFragment extends Fragment implements View.OnClickListener{
	View _RootView;
	SafetyListActivity activity;
	private Map<String,String> searchMap=new HashMap<String, String>();


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity=(SafetyListActivity) getActivity();
		System.out.println("---activity类型----"+activity.getTypeRecord());

		_RootView = inflater.inflate(RecordList.RightLayout.get(activity.getTypeRecord()), null);

		Button searchBack= (Button) _RootView.findViewById(R.id.searchBack);
		Button searchOk= (Button)	_RootView.findViewById(R.id.searchOk);
		searchBack.setOnClickListener(this);
		searchOk.setOnClickListener(this);

		return _RootView;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		onButtonListener buttonListener=(onButtonListener)activity;
		switch (id) {
			case R.id.searchBack: {
				buttonListener.onBackListener("数据重置");
			}
			break;
			case R.id.searchOk: {
				activity.RightSlidingMenuIsShow();
				addMap();
				buttonListener.onOKListener(searchMap);
			}
			break;
		}
	}
	private void addMap(){
		View  viewall = getActivity().getWindow().getDecorView();
		searchMap= ValueUtils.getAllChildViews(viewall);
		System.out.println("------searchMap--------"+searchMap);
	}
	public interface onButtonListener {
		void onBackListener(String data);
		void onOKListener(Map<String, String> data);
	}
}
