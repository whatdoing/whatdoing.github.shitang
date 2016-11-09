package com.haocean.dinninghall.safety.CreateSafetyFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CheckInfoRunnable;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.safety.CreateSafetyActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class SafetyReportFragment extends Fragment implements View.OnClickListener{
    private CreateSafetyActivity activity;
private Button report_date,report_month;
private String[] m={"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
    private TextView address,legal,inspect_company,contactsphone;
    String contacts="";
    public void getInfo(){
        contacts= CheckInfoRunnable.getContacts();

        try {
            JSONArray jsonArray=new JSONArray(contacts);
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            inspect_company.setText(jsonObject.getString("school_name"));
            legal.setText(jsonObject.getString("legal"));

            contactsphone.setText(jsonObject.getString("contactPhone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_safety, container, false);//初始化fragment

        activity= (CreateSafetyActivity)getActivity();
        report_date=(Button)view.findViewById(R.id.report_date);
        report_month=(Button)view.findViewById(R.id.report_month);

        legal=(TextView)view.findViewById(R.id.legal);
        inspect_company=(TextView)view.findViewById(R.id.inspect_company);
        contactsphone=(TextView)view.findViewById(R.id.contactsphone);
        report_date.setOnClickListener(this);
        report_month.setOnClickListener(this);
        getInfo();
        String tempString=activity.getTempString();
        System.out.println("-----safety-----"+tempString);
        if(tempString!=null) {
            Object object = ValueUtils.getValue("SafetyReport", view, tempString);

        }

        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.report_date: {
                MyDialogs.cereateDateDialog(report_date, activity);
            }
            break;
            case R.id.report_month: {
                MyDialogs.cereateDialog(report_month,m,activity);
            }
            break;
        }
    }
}
