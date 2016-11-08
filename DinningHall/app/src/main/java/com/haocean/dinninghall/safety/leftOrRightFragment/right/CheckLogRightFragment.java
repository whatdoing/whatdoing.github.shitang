package com.haocean.dinninghall.safety.leftOrRightFragment.right;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.safety.SafetyListActivity;

import java.util.HashMap;
import java.util.Map;

public class CheckLogRightFragment extends Fragment implements View.OnClickListener{
	View _RootView;
	SafetyListActivity activity;
	private Map<String,String> searchMap=new HashMap<String, String>();
Button firstime,lastime,log_result;
	String[] log_resultstr={"全部","符合","基本符合","不符合"};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity=(SafetyListActivity) getActivity();
		System.out.println("---activity类型----"+activity.getTypeRecord());

		_RootView = inflater.inflate(R.layout.check_log_menu0, null);

		Button searchBack= (Button) _RootView.findViewById(R.id.searchBack);
		Button searchOk= (Button)	_RootView.findViewById(R.id.searchOk);
		firstime= (Button)	_RootView.findViewById(R.id.firstime);
		lastime= (Button)	_RootView.findViewById(R.id.lastime);
		log_result= (Button)	_RootView.findViewById(R.id.log_result);
		searchBack.setOnClickListener(this);
		searchOk.setOnClickListener(this);
		firstime.setOnClickListener(this);
		lastime.setOnClickListener(this);
		log_result.setOnClickListener(this);

		return _RootView;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.searchBack: {
				activity.onBackListener(_RootView);
			}
			break;
			case R.id.searchOk: {
				activity.onOKListener();
			}
			break;
			case R.id.firstime: {
				MyDialogs.cereateDateDialog(firstime,activity);
			}
			break;
			case R.id.lastime: {
				MyDialogs.cereateDateDialog(lastime,activity);
			}
			break;
			case R.id.log_result: {
				MyDialogs.cereateDialog(log_result,log_resultstr,activity);
			}
			break;
		}
	}

}
