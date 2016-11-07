package com.haocean.dinninghall.manManagement.CreateManFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.PeopleRunnable;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.manManagement.CreateManActivity;
import com.haocean.dinninghall.record.CreateRecordActivity;
import com.haocean.dinninghall.record.utils.ValueUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by haocean on 2016/10/8.
 */
public class MorningInspectionFragment extends Fragment implements View.OnClickListener{
    private  CreateManActivity activity;
    Button jobtype,department,xuanze;
    RadioButton abnormal,normal;
    LinearLayout hide,people,result;
    TextView name;//隐藏的
    String[] job={"食堂负责人","食品安全管理员","食品添加剂专职管理人员","厨师","洗碗工","洗菜工"};
    String[] value={"校领导","食堂负责人","厨房","后勤"};

    String jobs,d;
String nameList="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_morning, container, false);//初始化fragment

        activity= (CreateManActivity)getActivity();
        jobtype=(Button)view.findViewById(R.id.jobtype);
        department=(Button)view.findViewById(R.id.department);
        xuanze=(Button) view.findViewById(R.id.xuanze);
        abnormal=(RadioButton)view.findViewById(R.id.abnormal);
        name=(TextView)view.findViewById(R.id.name);
        normal=(RadioButton)view.findViewById(R.id.normal);
        hide=(LinearLayout)view.findViewById(R.id.hide);
        result=(LinearLayout)view.findViewById(R.id.result);
        people=(LinearLayout)view.findViewById(R.id.people);
        jobtype.setOnClickListener(this);
        department.setOnClickListener(this);
        hide.setOnClickListener(this);
        abnormal.setOnClickListener(this);
        normal.setOnClickListener(this);
        xuanze.setOnClickListener(this);
        hide.setVisibility(View.GONE);
        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("MorningInspection", view, tempString);
        }

        return view;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    getData();
                    System.out.println("--nameLlist--"+nameList);
                    break;
                case 1:
                    Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
    public void Run(String job,String d,String type){
        PeopleRunnable peopleRunnable=new PeopleRunnable(job,d,type,handler);
        Thread thread=new Thread(peopleRunnable);
        thread.start();
    }
    public void getData(){


        String data=PeopleRunnable.getContacts().toString();
        try {
            JSONArray jsonArray=new JSONArray(data);
            JSONObject jsonObject;
            people.removeAllViews();
            for(int i=0;i<jsonArray.length();i++){
                View peopleView=LayoutInflater.from(AppController.getInstance()).inflate(R.layout.people_choose,null);
                CheckBox c = (CheckBox)peopleView.findViewById(R.id.check);
                final TextView n=(TextView)peopleView.findViewById(R.id.names);
                TextView job=(TextView)peopleView.findViewById(R.id.jobtype);
                TextView department=(TextView)peopleView.findViewById(R.id.department);
              /*  if(c.isChecked()){
                    nameList+=n.getText().toString().trim()+",";
                }
                else{
                    int postion =nameList.indexOf(n.getText().toString()+",");
                    int length = n.getText().toString().length()+1;
                    int Length = nameList.length();
                    nameList = nameList.substring(0,postion) + nameList.substring(postion + length, Length);
                }*/
               c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        if(isChecked){
                            nameList+=n.getText().toString().trim()+",";

                            name.setText(nameList.substring(0,nameList.length()-1));
                            System.out.println("-----Name------"+name.getText().toString());
                        }
                        else{
                                if(name.getText().toString().contains(",")){
                                    System.out.println("--NameList-----"+nameList);
                                    int postion =nameList.indexOf(n.getText().toString()+",");
                                    int length = n.getText().toString().length()+1;
                                    int Length = nameList.length();

                                    nameList = nameList.substring(0,postion) + nameList.substring(postion + length, Length);
                                    name.setText(nameList.substring(0,nameList.length()-1));
                                    System.out.println("-----Name------"+name.getText().toString());
                                }
                            else{
                                    nameList="";
                                    name.setText(nameList);
                                    System.out.println("--elesss-"+name.getText().toString());
                                }



                        }
                    }
                });

                jsonObject=jsonArray.getJSONObject(i);
                n.setText(jsonObject.get("name").toString().trim());
                job.setText(jsonObject.getString("jobtype").toString().trim());
                department.setText(jsonObject.getString("department").toString().trim());
                people.addView(peopleView);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.jobtype:

                MyDialogs.cereateDialog(jobtype, job, activity);
                break;
            case R.id.department:

                MyDialogs.cereateDialog(department, value, activity);
                break;
            case R.id.abnormal:

                hide.setVisibility(View.VISIBLE);
               // result.setVisibility(View.VISIBLE);

                break;
            case R.id.normal:
                hide.setVisibility(View.GONE);
                //result.setVisibility(View.VISIBLE);

                break;
            case R.id.xuanze:
                jobs=jobtype.getText().toString();
                d=department.getText().toString();
                nameList="";
                System.out.println("---jobs----"+jobs);
                System.out.println("---d----"+d);
                Run(jobs,d,activity.getTypeMan());

                break;
        }

    }
}
