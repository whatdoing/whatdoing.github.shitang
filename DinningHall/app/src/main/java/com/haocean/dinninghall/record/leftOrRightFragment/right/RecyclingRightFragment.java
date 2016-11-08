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
import com.haocean.dinninghall.record.DataList;
import com.haocean.dinninghall.record.RecordListActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;

import java.util.HashMap;
import java.util.Map;

public class RecyclingRightFragment extends Fragment implements View.OnClickListener{
	View _RootView;
	RecordListActivity activity;
	private Map<String,String> searchMap=new HashMap<String, String>();
	Button end_date,first_date,recovery_company;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity=(RecordListActivity) getActivity();
		System.out.println("---activity类型----"+activity.getTypeRecord());

		_RootView = inflater.inflate(R.layout.right_fragment_menu1, null);

		Button searchBack= (Button) _RootView.findViewById(R.id.searchBack);
		Button searchOk= (Button)	_RootView.findViewById(R.id.searchOk);
		end_date= (Button)	_RootView.findViewById(R.id.end_date);

		first_date= (Button)	_RootView.findViewById(R.id.first_date);
		recovery_company=(Button) _RootView.findViewById(R.id.recovery_company);

		searchBack.setOnClickListener(this);
		searchOk.setOnClickListener(this);
		end_date.setOnClickListener(this);
		first_date.setOnClickListener(this);
		recovery_company.setOnClickListener(this);
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
			case R.id.recovery_company: {
				String list= DataList.list;
				String[] arr=list.split(",");
				MyDialogs.cereateDialog(recovery_company,arr,activity);
			}
			break;
		}
	}

}
