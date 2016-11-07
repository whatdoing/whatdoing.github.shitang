package com.haocean.dinninghall.manManagement.CreateManFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.manManagement.CreateManActivity;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;

/**
 * Created by haocean on 2016/10/8.
 */
public class RosterFragment extends Fragment implements View.OnClickListener{
    private  CreateManActivity activity;
Button sex,takework_date,jobtype,department,healthunit,iscanteen,maturity_date;
    String[] sexs={"男","女"};
    String[] iscanteens={"是","否"};
    String[] jobtypes={"食堂负责人","食品安全管理员","食品添加剂专职管理人员","厨师","洗碗工","洗菜工"};
    String[] departments={"校领导","食堂负责人","厨房","后勤"};
    String[] healthunits={"玉环县疾病预防控制中心","玉环县大麦屿社区卫生服务中心","玉环县坎门社区卫生服务中心","玉环县第二人民医院"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_roster, container, false);//初始化fragment

        activity= (CreateManActivity)getActivity();

        String tempString=activity.getTempString();

        if(tempString!=null) {
            Object object = ValueUtils.getValue("Roster", view, tempString);
        }

        sex= (Button) view.findViewById(R.id.sex);
        sex.setOnClickListener(this);
        jobtype= (Button) view.findViewById(R.id.jobtype);
        jobtype.setOnClickListener(this);
        department= (Button) view.findViewById(R.id.department);
        department.setOnClickListener(this);
        takework_date= (Button) view.findViewById(R.id.takework_date);
        takework_date.setOnClickListener(this);
        healthunit= (Button) view.findViewById(R.id.healthunit);
        healthunit.setOnClickListener(this);
        iscanteen= (Button) view.findViewById(R.id.iscanteen);
        iscanteen.setOnClickListener(this);
        maturity_date= (Button) view.findViewById(R.id.maturity_date);
        maturity_date.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.sex:
                MyDialogs.cereateDialog(sex,sexs,activity);
                break;
            case R.id.jobtype:
                MyDialogs.cereateDialog(jobtype,jobtypes,activity);
                break;
            case R.id.department:
                MyDialogs.cereateDialog(department,departments,activity);
                break;
            case R.id.takework_date:
                MyDialogs.cereateDateDialog(takework_date,activity);
                break;
            case R.id.maturity_date:
                MyDialogs.cereateDateDialog(maturity_date,activity);
                break;
            case R.id.healthunit:
                MyDialogs.cereateDialog(healthunit,healthunits,activity);
                break;
            case R.id.iscanteen:
                MyDialogs.cereateDialog(iscanteen,iscanteens,activity);
                break;
        }

    }
}
