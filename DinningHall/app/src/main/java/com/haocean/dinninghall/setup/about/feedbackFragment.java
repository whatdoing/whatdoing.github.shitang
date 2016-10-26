package com.haocean.dinninghall.setup.about;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CreateRecordRunnable;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;

import java.util.ArrayList;


/**
 * Created by haocean on 2016/7/7.
 */
public class feedbackFragment extends Fragment implements View.OnClickListener{


    EditText suggestion,phone;
    Button images;
    Object object;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);//初始化fragment
        Button submit= (Button) view.findViewById(R.id.submit);
        suggestion=(EditText)view.findViewById(R.id.suggestion);
        images=(Button)view.findViewById(R.id.images);
        phone=(EditText)view.findViewById(R.id.phone);
        object=  ValueUtils.setValue("FeedBack",getActivity());

        submit.setOnClickListener(this);
        return view;
    }



public void Run(){
    CreateRecordRunnable createRecordRunnable=new CreateRecordRunnable(hand,object);
    createRecordRunnable.setTypeRecord("FeedBack");
    Thread dataThread=new Thread(createRecordRunnable);
    dataThread.start();

}
    Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    Toast.makeText(getActivity(), "新建成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(AppController.getInstance(), "新建失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.submit: {
              // Run();
                FragmentManager fragmentManager=  getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_context, new feedbackFragmentOk(),"feedbackFragmentOK").commit();
            }
            break;
            case R.id.images:{

            }
            break;
        }

    }
}
