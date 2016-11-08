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

public class RosterRightFragment extends Fragment implements View.OnClickListener{
	View _RootView;
	Button sex,jobtype,department,healthunit,iscanteen,startdate1,enddate1,safetymanager,healthdeal;
	ManManagementIndexActivity activity;
	Map<String,String> searchMap=new HashMap<String,String>();
	String[] sexs={"男","女"};
	String[] iscanteens={"是","否"};

	String[] jobtypes={"食堂负责人","食品安全管理员","食品添加剂专职管理人员","厨师","洗碗工","洗菜工"};
	String[] departments={"校领导","食堂负责人","厨房","后勤"};
	String[] healthunits={"玉环县疾病预防控制中心","玉环县大麦屿社区卫生服务中心","玉环县坎门社区卫生服务中心","玉环县第二人民医院"};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		activity=(ManManagementIndexActivity) getActivity();


		_RootView = inflater.inflate(R.layout.right_man_fragment, null);

		Button searchBack= (Button) _RootView.findViewById(R.id.searchBack);
		Button searchOk= (Button)	_RootView.findViewById(R.id.searchOk);
		sex= (Button)	_RootView.findViewById(R.id.sex);
		jobtype= (Button)	_RootView.findViewById(R.id.jobtype);
		department= (Button)	_RootView.findViewById(R.id.department);
		healthunit= (Button)	_RootView.findViewById(R.id.healthunit);
		iscanteen= (Button)	_RootView.findViewById(R.id.iscanteen);
		startdate1= (Button)	_RootView.findViewById(R.id.startdate1);
		enddate1= (Button)	_RootView.findViewById(R.id.enddate1);
		healthdeal=(Button)_RootView.findViewById(R.id.healthdeal);
		healthdeal.setOnClickListener(this);
		safetymanager= (Button) _RootView.findViewById(R.id.safetymanager);
		safetymanager.setOnClickListener(this);
		sex.setOnClickListener(this);
		jobtype.setOnClickListener(this);
		department.setOnClickListener(this);
		healthunit.setOnClickListener(this);
		iscanteen.setOnClickListener(this);
		startdate1.setOnClickListener(this);
		enddate1.setOnClickListener(this);

		searchBack.setOnClickListener(this);
		searchOk.setOnClickListener(this);

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
			case R.id.sex:
				MyDialogs.cereateDialog(sex,sexs,activity);
				break;
			case R.id.jobtype:
				MyDialogs.cereateDialog(jobtype,jobtypes,activity);
				break;
			case R.id.department:
				MyDialogs.cereateDialog(department,departments,activity);
				break;
			case R.id.healthunit:
				MyDialogs.cereateDialog(healthunit,healthunits,activity);
				break;
			case R.id.iscanteen:
				MyDialogs.cereateDialog(iscanteen,iscanteens,activity);
				break;
			case R.id.startdate1:
				MyDialogs.cereateDateDialog(startdate1,activity);
				break;
			case R.id.enddate1:
				MyDialogs.cereateDateDialog(enddate1,activity);
				break;
			case R.id.safetymanager:
				MyDialogs.cereateDialog(safetymanager,iscanteens,activity);
				break;
			case R.id.healthdeal:
				MyDialogs.cereateDialog(healthdeal,iscanteens,activity);
				break;
		}
	}


}
