package com.haocean.dinninghall.review.leftOrRightFragment;

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
import com.haocean.dinninghall.review.ReviewListActivity;


import java.util.HashMap;
import java.util.Map;

public class RightFragment extends Fragment implements View.OnClickListener{
	View _RootView;
	ReviewListActivity activity;
	private Map<String,String> searchMap=new HashMap<String, String>();
	Button end_date,first_date,lastime_fucha,firstime_fucha,firstime_sign,lastime_sign;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity=(ReviewListActivity) getActivity();


		_RootView = inflater.inflate(RecordList.RightLayout.get(activity.getTypeRecord()), null);

		Button searchBack= (Button) _RootView.findViewById(R.id.searchBack);
		Button searchOk= (Button)	_RootView.findViewById(R.id.searchOk);
		end_date= (Button)	_RootView.findViewById(R.id.end_date);
		first_date= (Button)	_RootView.findViewById(R.id.first_date);
		end_date.setOnClickListener(this);
		first_date.setOnClickListener(this);

		lastime_fucha= (Button)	_RootView.findViewById(R.id.lastime_fucha);
		firstime_fucha= (Button)	_RootView.findViewById(R.id.firstime_fucha);
		lastime_fucha.setOnClickListener(this);
		firstime_fucha.setOnClickListener(this);

		firstime_sign= (Button)	_RootView.findViewById(R.id.firstime_sign);
		lastime_sign= (Button)	_RootView.findViewById(R.id.lastime_sign);
		firstime_sign.setOnClickListener(this);
		lastime_sign.setOnClickListener(this);
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
				buttonListener.onBackListener(_RootView,searchMap);
			}
			break;
			case R.id.searchOk: {
				activity.RightSlidingMenuIsShow();
				addMap();
				buttonListener.onOKListener(searchMap);
			}
			break;
			case R.id.lastime_fucha: {
				MyDialogs.cereateDateDialog(lastime_fucha,activity);
			}
			break;
			case R.id.firstime_fucha: {
				MyDialogs.cereateDateDialog(firstime_fucha,activity);
			}
			break;

			case R.id.firstime_sign: {
				MyDialogs.cereateDateDialog(firstime_sign,activity);
			}
			break;
			case R.id.lastime_sign: {
				MyDialogs.cereateDateDialog(lastime_sign,activity);
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
		}
	}
	private void addMap(){
		View  viewall = getActivity().getWindow().getDecorView();
		searchMap= ValueUtils.getAllChildViews(viewall);
		System.out.println("------searchMap--------"+searchMap);
	}
	public interface onButtonListener {
		void onBackListener(View view,Map<String, String> data);
		void onOKListener(Map<String, String> data);
	}
}
