package com.haocean.dinninghall.record.leftOrRightFragment.right;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.RecordListActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;

import java.util.HashMap;
import java.util.Map;

public class TablewareRightFragment extends Fragment implements View.OnClickListener{
	View _RootView;
	RecordListActivity activity;

	Button end_date,first_date,clean_condition;
	String[] con={"正常","未保洁"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity=(RecordListActivity) getActivity();

		_RootView = inflater.inflate(R.layout.right_fragment_menu0, null);

		Button searchBack= (Button) _RootView.findViewById(R.id.searchBack);
		Button searchOk= (Button)	_RootView.findViewById(R.id.searchOk);
		 end_date= (Button)	_RootView.findViewById(R.id.end_date);
		 first_date= (Button)	_RootView.findViewById(R.id.first_date);
		clean_condition=(Button)_RootView.findViewById(R.id.clean_condition);
		searchBack.setOnClickListener(this);
		searchOk.setOnClickListener(this);
		end_date.setOnClickListener(this);
		first_date.setOnClickListener(this);
		clean_condition.setOnClickListener(this);
		return _RootView;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.first_date: {
				MyDialogs.cereateDateDialog(first_date,activity);
			}
			break;
			case R.id.end_date: {
				MyDialogs.cereateDateDialog(end_date,activity);
			}
			break;
			case R.id.searchBack: {
				activity.onBackListener(_RootView);
			}
			break;
			case R.id.searchOk: {
				activity.onOKListener();
			}
			break;
			case R.id.clean_condition: {
				MyDialogs.cereateDialog(clean_condition,con,activity);
			}
			break;
		}
	}


}
