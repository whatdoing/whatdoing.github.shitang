package com.haocean.dinninghall.safety.CreateSafetyFragment;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CheckInfoRunnable;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.entity.record.CheckLog;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.safety.CreateSafetyActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class CheckLogFragment extends Fragment implements View.OnClickListener,RadioGroup.OnCheckedChangeListener , OnItemClickListener {
    private CreateSafetyActivity activity;
    private Button inspect_date;
    private RadioButton yes,no;
    RadioButton radioButtons;
    View view;
    private TextView address,legal,inspect_company,legalphone;
    String contacts="";
    String bujige="";
    Map<String,Map<Boolean,String>> selectmap=new HashMap<String,Map<Boolean, String>>();
    private AlertView mAlertViewExt;//窗口拓展例子
    public void getBujige(){
        bujige=CheckInfoRunnable.getBujige();
        if(bujige!="") {
            try {
                JSONObject jsonObject = new JSONObject(bujige);
                String yb = jsonObject.getString("yb");
                String zd = jsonObject.getString("zd");
                String bjg = yb + zd;
                String[] bujiges = bjg.split(";");
                alertShow(bujiges);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void alertShow(String[] arr) {
        mAlertViewExt=   new AlertView("以下为本次必查", null, null,null ,
                arr,
                activity, AlertView.Style.Alert,this ).setCancelable(true);
        mAlertViewExt.show();
    }
    public void getInfo(){
        contacts= CheckInfoRunnable.getContacts();
        System.out.println("-********-"+contacts);
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


    private void findall_yes_no(){
        try {
        for(int i=0;i<10;i++){

            RadioGroup  all_yes_no = (RadioGroup) view.findViewById( R.id.class.getField("all_yes_no"+i).getInt(null));

            all_yes_no.setOnCheckedChangeListener(this);// 当然也可以使用匿名内部类实现
        }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_check, container, false);//初始化fragment

        activity= (CreateSafetyActivity)getActivity();
        inspect_date=(Button)view.findViewById(R.id.inspect_date);
        yes= (RadioButton) view.findViewById(R.id.yes0);
        no= (RadioButton) view.findViewById(R.id.no0);

        legalphone=(TextView)view.findViewById(R.id.legalphone);
        address=(TextView)view.findViewById(R.id.address);
        legal=(TextView)view.findViewById(R.id.legal);
        inspect_company=(TextView)view.findViewById(R.id.inspect_company);


        inspect_date.setOnClickListener(this);// 当然也可以使用匿名内部类实现

        findall_yes_no();
        findRadioButton();

        String tempString=activity.getTempString();
        if(tempString!=null) {
            //编辑
            Object object = ValueUtils.getValue("CheckLog", view, tempString);

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx-"+((CheckLog)object).getYb()+ ((CheckLog)object).getZd());
        }
        else{
            getBujige();
        }
getInfo();
        return view;
    }

private void  findRadioButton() {
    try {
    String[] strSHArea ;
    for(int j=1;j<10;j++){

        int   arrid = R.array.class.getField("all_yes_no"+j).getInt(null);

        strSHArea =  AppController.getInstance().getResources().getStringArray(arrid);

        for (int i = 0; i < strSHArea.length; i++) {
            final String  strSHAreap=strSHArea[i];
            String  strSHAreaI = strSHArea[i];
            boolean iszd=false;
            if(strSHArea[i].contains("zd_")){
                strSHAreaI=strSHAreaI.replace("zd_","");
                iszd=true;
            }

            final int id = R.id.class.getField(strSHAreaI).getInt(null);
            if (view.findViewById(id) instanceof RadioGroup) {
                 final RadioButton  radioButtonyes= (RadioButton)  view.findViewById( R.id.class.getField(strSHAreaI+"_yes").getInt(null));
                RadioButton  radioButtonno= (RadioButton)  view.findViewById( R.id.class.getField(strSHAreaI+"_no").getInt(null));
                final TextView   textView= (TextView)  view.findViewById( R.id.class.getField(strSHAreaI+"_text").getInt(null));
                final int finalJ = j;
                radioButtonyes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override

                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(!buttonView.isPressed())return;
                        textView.setTextColor(Color.BLACK);
                        Map<Boolean,String> map = new HashMap<Boolean, String>();
                        map.put(true,textView.getText().toString());
                        selectmap.put(strSHAreap,map);
                        TextPaint tp = textView.getPaint();
                        tp.setFakeBoldText(false);
                        try {
                            ((RadioGroup) view.findViewById(R.id.class.getField("all_yes_no"+ finalJ).getInt(null))).clearCheck();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        ((RadioGroup) view.findViewById(R.id.all_yes_no0)).clearCheck();
                    }

                });

                final boolean finalIszd = iszd;
                radioButtonno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override

                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(!buttonView.isPressed())return;
                        Map<Boolean,String> map = new HashMap<Boolean, String>();
                        map.put(false,textView.getText().toString());
                        selectmap.put(strSHAreap,map);
                        textView.setTextColor(Color.RED);
                        if(finalIszd){
                            TextPaint tp = textView.getPaint();
                            tp.setFakeBoldText(true);
                        }
                        try {
                            ((RadioGroup) view.findViewById(R.id.class.getField("all_yes_no"+ finalJ).getInt(null))).clearCheck();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        ((RadioGroup) view.findViewById(R.id.all_yes_no0)).clearCheck();

                    }

                });

            }
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
    private void clearGroup(int x){
        if(x!=0){
            RadioGroup radioGroup=  ((RadioGroup) view.findViewById(R.id.all_yes_no0));
            radioGroup.clearCheck();
        }else{
            for(int i=1;i<10;i++){
                try {
                    RadioGroup radioGroup =((RadioGroup) view.findViewById(R.id.class.getField("all_yes_no"+i).getInt(null)));
                    radioGroup.clearCheck();

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }

    }
public Map<String,Map<Boolean,String>> getSelcetMap(){

    return selectmap;
}

private void selectGroup(int checkedId,int x){
    try {
        RadioButton  radioButton= (RadioButton)  view.findViewById(checkedId);
        int arrid= R.array.class.getField("all_yes_no"+x).getInt(null);//arrays.xml(中类型的id)
        String[] strSHArea =  AppController.getInstance().getResources().getStringArray(arrid);

        for(int i=0;i<strSHArea.length;i++){
            String  strSHAreap=strSHArea[i];
            String  strSHAreaI = strSHArea[i];
            boolean iszd=false;
            if(strSHArea[i].contains("zd_")){
                strSHAreaI=strSHAreaI.replace("zd_","");
                iszd=true;
            }

                int butY=R.id.class.getField("yes"+x).getInt(null);
                int butN=R.id.class.getField("no"+x).getInt(null);
                int butC=R.id.class.getField("cancel"+x).getInt(null);
                if(checkedId==butY&&((RadioButton)view.findViewById(butY)).isChecked()){
                    radioButton.setChecked(true);
                    TextView    textView=  (TextView)  view.findViewById( R.id.class.getField(strSHAreaI+"_text").getInt(null));
                    Map<Boolean,String> map = new HashMap<Boolean, String>();
                    map.put(true,textView.getText().toString());
                    selectmap.put(strSHAreap,map);
                    textView.setTextColor(Color.BLACK);
                    TextPaint tp = textView.getPaint();
                    tp.setFakeBoldText(false);
                    radioButtons= (RadioButton)  view.findViewById( R.id.class.getField(strSHAreaI+"_yes").getInt(null));
                    radioButtons.setChecked(true);
                    clearGroup(x);

                }else  if(checkedId==butN&&((RadioButton)view.findViewById(butN)).isChecked()){
                    radioButton.setChecked(true);
                    TextView    textView=  (TextView)  view.findViewById( R.id.class.getField(strSHAreaI+"_text").getInt(null));
                    Map<Boolean,String> map = new HashMap<Boolean, String>();
                    map.put(false,textView.getText().toString());
                    selectmap.put(strSHAreap,map);
                    textView.setTextColor(Color.RED);
                    if(iszd){
                        TextPaint tp = textView.getPaint();
                        tp.setFakeBoldText(true);
                    }


                    radioButtons= (RadioButton)  view.findViewById( R.id.class.getField(strSHAreaI+"_no").getInt(null));
                    radioButtons.setChecked(true);
                    clearGroup(x);
                }
                else  if(checkedId==butC&&((RadioButton)view.findViewById(butC)).isChecked()){
                    radioButton.setChecked(true);
                    TextView    textView=  (TextView)  view.findViewById( R.id.class.getField(strSHAreaI+"_text").getInt(null));
                    selectmap.remove(strSHAreap);
                    textView.setTextColor(Color.BLACK);
                    TextPaint tp = textView.getPaint();
                    tp.setFakeBoldText(false);
                    RadioGroup radioGroup=  ((RadioGroup) view.findViewById(R.id.class.getField(strSHAreaI).getInt(null)));
                    radioGroup.clearCheck();
                    clearGroup(x);
                }


        }
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (NoSuchFieldException e) {
        e.printStackTrace();
    }
}


    @Override
    public void onCheckedChanged(RadioGroup group, final int checkedId) {
        switch (group.getId()){
            case R.id.all_yes_no0:
                System.out.println(0);
                selectGroup(checkedId,0);
                break;
            case R.id.all_yes_no1:
                System.out.println(1);
                selectGroup(checkedId,1);
                break;
            case R.id.all_yes_no2:
                System.out.println(2);
                selectGroup(checkedId,2);
                break;
            case R.id.all_yes_no3:
                selectGroup(checkedId,3);
                break;
            case R.id.all_yes_no4:
                selectGroup(checkedId,4);
                break;
            case R.id.all_yes_no5:
                selectGroup(checkedId,5);
                break;
            case R.id.all_yes_no6:
                selectGroup(checkedId,6);
                break;
            case R.id.all_yes_no7:
                selectGroup(checkedId,7);
                break;
            case R.id.all_yes_no8:
                selectGroup(checkedId,8);
                break;
            case R.id.all_yes_no9:
                selectGroup(checkedId,9);
                break;
        }

        }

    @Override
    public void onItemClick(Object o, int position) {

    }
}
