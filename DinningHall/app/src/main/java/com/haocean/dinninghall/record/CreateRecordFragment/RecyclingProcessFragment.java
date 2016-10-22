package com.haocean.dinninghall.record.CreateRecordFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.ListRunnable;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.contexts.RecordList;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.DataList;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.safety.CreateSafetyActivity;

/**
 * Created by haocean on 2016/10/8.
 */
public class RecyclingProcessFragment extends Fragment implements View.OnClickListener{
    private CreateRecordActivity activity;
    private  Button recovery_company,recovery_address,contacts,contactsphone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycling, container, false);//初始化fragment

        activity= (CreateRecordActivity)getActivity();
        Run();
        recovery_company=(Button)view.findViewById(R.id.recovery_company);
        recovery_address=(Button)view.findViewById(R.id.recovery_address);
        contacts=(Button)view.findViewById(R.id.contacts);
        contactsphone=(Button)view.findViewById(R.id.contactsphone);

        recovery_company.setOnClickListener(this);
        recovery_address.setOnClickListener(this);
        contacts.setOnClickListener(this);
        contactsphone.setOnClickListener(this);

        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("RecyclingProcess", view, tempString);
        }
        return view;
    }

    public void Run(){
        ListRunnable listRunnable=new ListRunnable(activity);
        listRunnable.setTypeMan(activity.typeRecord);
        Thread thread=new Thread(listRunnable);
        thread.start();
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.recovery_company: {

                String list= DataList.list;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(recovery_company,arr,activity);
            }
            break;
            case R.id.recovery_address: {

                String list= DataList.recovery_address;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(recovery_address,arr,activity);
            }
            break;
            case R.id.contacts: {

                String list= DataList.contacts;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(contacts,arr,activity);
            }
            break;
            case R.id.contactsphone: {

                String list= DataList.contactsphone;
                String[] arr=list.split(",");
                MyDialogs.cereateDialog(contactsphone,arr,activity);
            }
            break;

        }

    }
}
