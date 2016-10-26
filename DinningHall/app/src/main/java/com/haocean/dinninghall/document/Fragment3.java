package com.haocean.dinninghall.document;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnDismissListener;
import com.bigkoo.alertview.OnItemClickListener;
import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.setup.about.AboutActivity;
import com.haocean.dinninghall.setup.contacts.ContactsList;
import com.haocean.dinninghall.setup.revise.ReviseActivity;
import com.haocean.dinninghall.setup.user.UserActivity;

import cn.hugeterry.updatefun.UpdateFunGO;
import cn.hugeterry.updatefun.config.DownloadKey;
import cn.hugeterry.updatefun.module.Download;
import cn.hugeterry.updatefun.utils.GetAppInfo;


/**
 * Created by haocean on 2016/7/7.
 */
public class Fragment3 extends Fragment implements View.OnClickListener,OnItemClickListener {
    private DocumentActivity activity;
    private LinearLayout geren,about,revise,contacts,update;
    private TextView updatetext;
    private Float version;
    private AlertView mAlertViewExt;
    private InputMethodManager imm;
    private EditText etName;
//设置
private void closeKeyboard() {
    //关闭软键盘
    imm.hideSoftInputFromWindow(etName.getWindowToken(),0);
    //恢复位置
    mAlertViewExt.setMarginBottom(0);
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_setup, null);
        activity = (DocumentActivity)getActivity();
        geren= (LinearLayout) view.findViewById(R.id.geren);
        updatetext= (TextView) view.findViewById(R.id.updatetext);
        about= (LinearLayout) view.findViewById(R.id.about);
        revise= (LinearLayout) view.findViewById(R.id.revise);
        contacts= (LinearLayout) view.findViewById(R.id.contacts);
        update= (LinearLayout) view.findViewById(R.id.update);
        version=Float.valueOf( GetAppInfo.getAppVersionName(activity));

        ViewGroup extView = (ViewGroup) LayoutInflater.from(AppController.getInstance()).inflate(R.layout.alertext_form,null);
        etName = (EditText) extView.findViewById(R.id.etName);

        mAlertViewExt = new AlertView("提示", "请填入旧密码！", "取消", null, new String[]{"完成"},getActivity(), AlertView.Style.Alert, this);
        imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (Float.valueOf(DownloadKey.version)>version){
            updatetext.setText("存在新版本："+ DownloadKey.version);
        }
         else{
            updatetext.setText("当前为最新版本！");
        }

        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                //输入框出来则往上移动
                boolean isOpen=imm.isActive();
                mAlertViewExt.setMarginBottom(isOpen&&focus ? 120 :0);

            }
        });
        mAlertViewExt.addExtView(extView);

        geren.setOnClickListener(this);
        about.setOnClickListener(this);
        revise.setOnClickListener(this);
        contacts.setOnClickListener(this);
        update.setOnClickListener(this);

        return view;


    }



    public void onItemClick(Object o,int position) {
        closeKeyboard();
        //判断是否是拓展窗口View，而且点击的是非取消按钮
        if(o == mAlertViewExt && position != AlertView.CANCELPOSITION){
            String name = etName.getText().toString();
            if(name.isEmpty()){

            }
            else{
                Toast.makeText(AppController.getInstance(), "成功了", Toast.LENGTH_SHORT).show();
                System.out.println("----"+UserData.getPassword());
                if(name.equals(UserData.getPassword())){
                    Intent revise=new Intent(activity,ReviseActivity.class);
                    startActivity(revise);
                }
            }

            return;
        }

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.geren:
                Intent geren=new Intent(getActivity(), UserActivity.class);

                startActivityForResult(geren, 2);
                break;
            case R.id.about:
                Intent about=new Intent(activity, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.contacts:
                Intent intent=new Intent(activity,ContactsList.class);

                startActivity(intent);
                break;
            case R.id.update:
                if (Float.valueOf(DownloadKey.version)>version){
                    UpdateFunGO.manualStart(activity);
                }
                break;
            case R.id.revise:

                mAlertViewExt.show();
                break;

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        UpdateFunGO.onResume(activity);
    }

    @Override
    public void onStop() {
        super.onStop();
        UpdateFunGO.onStop(activity);
    }

}
