package com.haocean.dinninghall.safety.CreateSafetyFragment;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.CheckInfoRunnable;
import com.haocean.dinninghall.contexts.MyDialogs;
import com.haocean.dinninghall.entity.record.RatingCriteria;
import com.haocean.dinninghall.record.utils.ValueUtils;
import com.haocean.dinninghall.safety.CreateSafetyActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;


/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class RatingCriteriaFragment extends Fragment implements View.OnClickListener {
    private CreateSafetyActivity activity;
    private Button inspect_date,assessment_level;
    private TextView      earned_score,realization_score,standardized_score;
    String[] level={"优秀","良好","一般","整改"};
    RatingCriteria ratingCriteria=new RatingCriteria();
    float[] fenshufloat={4,10,10,10,10,12,10,12,10,10,2,3};
     String[] strNew;
    View view;
   TextView newText;
    private TextView address,legal,school_name,contactPhone;
    String contacts="";
    public void getInfo(){
        contacts= CheckInfoRunnable.getContacts();

        try {
            JSONArray jsonArray=new JSONArray(contacts);
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            school_name.setText(jsonObject.getString("school_name"));
            legal.setText(jsonObject.getString("legal"));
            address.setText(jsonObject.getString("address"));
            contactPhone.setText(jsonObject.getString("contactPhone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    // handler类接收数据
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                // 动态更新UI界面
                String str = msg.getData().getString("num");
                newText.setText(str);
                float sum=0;
                for(int i=0;i<strNew.length;i++){

                    try {
                        int id=  R.id.class.getField(strNew[i]).getInt(null);
                        TextView newView=(TextView)view.findViewById(id);
                        String strnew=newView.getText().toString().trim();
                        if(!strnew.equals("")){
                            sum+=Float.parseFloat(strnew);
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }

                }
                realization_score.setText(String.valueOf(sum));
                standardized_score.setText(String.valueOf(sum/Float.parseFloat(earned_score.getText().toString().trim())*100));
            }
        };
    };


    public void initArray(){
        try {
            Resources res = AppController.getInstance().getResources();
             String field="RC";
            final Class clazz= ratingCriteria.getClass();
            final int  Newid  = R.array.class.getField("New").getInt(null);
          strNew = res.getStringArray(Newid);
        for(int i = 0;i<12;i++){
    int  allid  = R.array.class.getField(field+i).getInt(null);
    final String[] strSHArea = res.getStringArray(allid);
    for(int j=0;j<strSHArea.length;j++){
        System.out.println("---我走了几次--"+strSHArea[j]);
        int id= R.id.class.getField(strSHArea[j]).getInt(null);
        final EditText a=(EditText)view.findViewById(id);



        final int finalI = i;
        final int finalJ = j;

        a.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count,int after) {
            }
            @Override
            public void afterTextChanged(Editable edit) {
                try {
                    Field field = clazz.getDeclaredField(strSHArea[finalJ]);
                    field.setAccessible(true);
                    field.set(ratingCriteria, edit.toString());
                    float sum = 0;
                    for(int k=0;k<strSHArea.length;k++){
                        Field fields = clazz.getDeclaredField(strSHArea[k]);
                        fields.setAccessible(true);
                        System.out.println("value+"+fields.getName());
                        String strField= (String) fields.get(ratingCriteria);
                        System.out.println("strField++++"+strField);
                        if(strField!=null){
                            if(!strField.trim().equals("")){
                                System.out.println("上面的+"+strField);
                                sum+=Float.parseFloat(strField);
                            }
                        }
                    }
                    if(fenshufloat[finalI]<sum){
                        Toast.makeText(activity,"输入的值有误，请重输！",Toast.LENGTH_SHORT);
                        a.setText("");
                    }else{
                        int id= R.id.class.getField(strNew[finalI]).getInt(null);
                        newText=(TextView)view.findViewById(id);

                        Message msg = new Message();
                        msg.what = 1;
                        // handler传递参数
                        // handler.sendMessage(msg);
                        Bundle bundle = new Bundle();
                        bundle.putString("num", String.valueOf(fenshufloat[finalI]-sum));
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_rating, container, false);//初始化fragment
        activity= (CreateSafetyActivity)getActivity();

        inspect_date=(Button)view.findViewById(R.id.inspect_date);
        assessment_level=(Button)view.findViewById(R.id.assessment_level);
        earned_score=(TextView)view.findViewById(R.id.earned_score);
        realization_score=(TextView)view.findViewById(R.id.realization_score);
        standardized_score=(TextView)view.findViewById(R.id.standardized_score);

        contactPhone=(TextView)view.findViewById(R.id.contactPhone);
        address=(TextView)view.findViewById(R.id.address);
        legal=(TextView)view.findViewById(R.id.legal);
        school_name=(TextView)view.findViewById(R.id.school_name);
        getInfo();

        inspect_date.setOnClickListener(this);
        assessment_level.setOnClickListener(this);
        initArray();


        String tempString=activity.getTempString();
        if(tempString!=null) {
            Object object = ValueUtils.getValue("RatingCriteria", view, tempString);
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
            case R.id.assessment_level: {
                MyDialogs.cereateDialog(assessment_level,level, activity);
            }
            break;
        }
    }
}
