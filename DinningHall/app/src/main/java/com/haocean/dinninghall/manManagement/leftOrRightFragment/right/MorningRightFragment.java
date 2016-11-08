package com.haocean.dinninghall.manManagement.leftOrRightFragment.right;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.manManagement.ManManagementIndexActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;

import java.util.HashMap;
import java.util.Map;

public class MorningRightFragment extends Fragment implements View.OnClickListener{
	View _RootView;
	ManManagementIndexActivity activity;
	Map<String,String> searchMap=new HashMap<String,String>();
	String[] morning ={"正常","未保洁"};
	Button end_date,first_date,morningcheck;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		activity=(ManManagementIndexActivity) getActivity();

		System.out.println("----Man  type------"+activity.getTypeMan());
		_RootView = inflater.inflate(R.layout.right_morning_fragment, null);
		end_date= (Button)	_RootView.findViewById(R.id.end_date);
		first_date= (Button)	_RootView.findViewById(R.id.first_date);
		morningcheck= (Button) _RootView.findViewById(R.id.morningcheck);
		Button searchBack= (Button) _RootView.findViewById(R.id.searchBack);
		Button searchOk= (Button)	_RootView.findViewById(R.id.searchOk);
		searchBack.setOnClickListener(this);
		searchOk.setOnClickListener(this);
		end_date.setOnClickListener(this);
		first_date.setOnClickListener(this);
		morningcheck.setOnClickListener(this);
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
			case R.id.first_date: {
				MyDialogs.cereateDateDialog(first_date,activity);
			}
			break;
			case R.id.end_date: {
				MyDialogs.cereateDateDialog(end_date,activity);
			}
			break;
			case R.id.morningcheck:
				MyDialogs.cereateDialog(morningcheck, morning, activity);
				break;
		}
	}


}
