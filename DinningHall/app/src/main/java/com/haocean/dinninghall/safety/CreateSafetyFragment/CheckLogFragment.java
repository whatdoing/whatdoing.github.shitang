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
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.safety.CreateSafetyActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class CheckLogFragment extends Fragment implements View.OnClickListener{
    private CreateSafetyActivity activity;
    private Button inspect_date;
    private TextView address,legal,inspect_company,legalphone;
    String contacts="";
    public void getInfo(){
        contacts= CheckInfoRunnable.getContacts();

        try {
            JSONArray jsonArray=new JSONArray(contacts);
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            inspect_company.setText(jsonObject.getString("school_name"));
            legal.setText(jsonObject.getString("legal"));
            address.setText(jsonObject.getString("address"));
            legalphone.setText(jsonObject.getString("legalphone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check, container, false);//初始化fragment

        activity= (CreateSafetyActivity)getActivity();
        inspect_date=(Button)view.findViewById(R.id.inspect_date);
        legalphone=(TextView)view.findViewById(R.id.legalphone);
        address=(TextView)view.findViewById(R.id.address);
        legal=(TextView)view.findViewById(R.id.legal);
        inspect_company=(TextView)view.findViewById(R.id.inspect_company);
        getInfo();

        inspect_date.setOnClickListener(this);
        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("CheckLog", view, tempString);

        }

        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.inspect_date: {
                MyDialogs.cereateDateDialog(inspect_date, activity);
            }
            break;
        }
    }
}
