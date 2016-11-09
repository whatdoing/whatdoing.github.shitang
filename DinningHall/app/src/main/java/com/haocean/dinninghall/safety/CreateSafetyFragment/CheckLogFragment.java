package com.haocean.dinninghall.safety.CreateSafetyFragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haocean.dinninghall.AppController;
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
public class CheckLogFragment extends Fragment implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    private CreateSafetyActivity activity;
    private Button inspect_date;
    private RadioGroup all_yes_no;
    private RadioButton yes,no;
    RadioButton radioButtons;
    View view;
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
            legalphone.setText(jsonObject.getString("contactPhone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_check, container, false);//初始化fragment

        activity= (CreateSafetyActivity)getActivity();
        inspect_date=(Button)view.findViewById(R.id.inspect_date);
        all_yes_no= (RadioGroup) view.findViewById(R.id.all_yes_no);
        yes= (RadioButton) view.findViewById(R.id.yes);
        no= (RadioButton) view.findViewById(R.id.no);

        legalphone=(TextView)view.findViewById(R.id.legalphone);
        address=(TextView)view.findViewById(R.id.address);
        legal=(TextView)view.findViewById(R.id.legal);
        inspect_company=(TextView)view.findViewById(R.id.inspect_company);
      //  getInfo();

        all_yes_no.setOnCheckedChangeListener(this);// 当然也可以使用匿名内部类实现
        inspect_date.setOnClickListener(this);// 当然也可以使用匿名内部类实现

        findRadioButton();


//        yes.setOnCheckedChangeListener(this);
//        no.setOnCheckedChangeListener(this);
        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("CheckLog", view, tempString);

        }

        return view;
    }

private void  findRadioButton() {
    String[] strSHArea = AppController.getInstance().getResources().getStringArray(R.array.CheckLog);

        try {
            for (int i = 0; i < strSHArea.length; i++) {
            final int id = R.id.class.getField(strSHArea[i]).getInt(null);
            if (view.findViewById(id) instanceof RadioGroup) {
                RadioGroup radioGroup= (RadioGroup) view.findViewById(id);
                 RadioButton  radioButtonyes= (RadioButton)  view.findViewById( R.id.class.getField(strSHArea[i]+"_yes").getInt(null));
                RadioButton  radioButtonno= (RadioButton)  view.findViewById( R.id.class.getField(strSHArea[i]+"_no").getInt(null));
                final TextView  textView= (TextView)  view.findViewById( R.id.class.getField(strSHArea[i]+"_text").getInt(null));

//                radioButtonyes.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        if(radioButtons.isChecked()){
//                            Toast.makeText(getActivity(), radioButtons.isChecked()+"?", Toast.LENGTH_SHORT).show();
//                            // radioButtons.setChecked(false);
//                        }
//                    }
//                });
//
             //   radioGroup.setOnCheckedChangeListener(this);
                radioButtonyes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override

                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      //  all_yes_no.clearCheck();
                        if(!buttonView.isPressed())return;
                        textView.setTextColor(Color.BLACK);
                       // all_yes_no.clearCheck();
                        all_yes_no.clearCheck();

                    }

                });
                radioButtonno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override

                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      //  all_yes_no.clearCheck();
                        if(!buttonView.isPressed())return;
                        textView.setTextColor(Color.RED);
                       // all_yes_no.clearCheck();

                            all_yes_no.clearCheck();

                    }

                });
            }
        }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


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




//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        int checkedId = buttonView.getId();
//        if(!buttonView.isPressed())return;
//        switch (checkedId) {
//            case R.id.yes: {
//
//                System.out.println("看看有没有执行到什么1");
//            }
//            case R.id.no: {
//                System.out.println("看看有没有执行到什么2");
//            }
//            break;
//        }
//    }

    @Override
    public void onCheckedChanged(RadioGroup group, final int checkedId) {

        switch (group.getId()){
            case R.id.all_yes_no:
                try {
                    RadioButton  radioButton= (RadioButton)  view.findViewById(checkedId);
                    String[] strSHArea =  AppController.getInstance().getResources().getStringArray(R.array.CheckLog);
                    for(int i=0;i<strSHArea.length;i++){
                        final int id=  R.id.class.getField(strSHArea[i]).getInt(null);
                        if(view.findViewById(id) instanceof RadioGroup){
                           // radioButton.setChecked(true);
                            TextView textView=  (TextView)  view.findViewById( R.id.class.getField(strSHArea[i]+"_text").getInt(null));
                            if(checkedId==R.id.yes&&yes.isChecked()){

                                radioButton.setChecked(true);

                                textView.setTextColor(Color.BLACK);
                                radioButtons= (RadioButton)  view.findViewById( R.id.class.getField(strSHArea[i]+"_yes").getInt(null));


                            }else if(checkedId==R.id.no&&no.isChecked()) {
                                radioButton.setChecked(true);
                                System.out.println("123123-----------"+ strSHArea[i]+"_text");

                                textView.setTextColor(Color.RED);
                                radioButtons= (RadioButton)  view.findViewById( R.id.class.getField(strSHArea[i]+"_no").getInt(null));

                            }

                            radioButtons.setChecked(true);



                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("不知道可以不可以？");
                break;
        }
       // if(!group.isPressed())return;
      //  final RadioButton radioButton =   (RadioButton)  view.findViewById(checkedId);
      //  radioButton.setChecked(true);


        }
}
