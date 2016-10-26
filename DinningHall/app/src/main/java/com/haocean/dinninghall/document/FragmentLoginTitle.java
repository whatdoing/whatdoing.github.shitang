package com.haocean.dinninghall.document;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haocean.dinninghall.AppController;
import com.haocean.dinninghall.R;
import com.haocean.dinninghall.adapter.MyGridAdapter;
import com.haocean.dinninghall.contexts.Application;
import com.haocean.dinninghall.publicMethod.UserData;
import com.haocean.dinninghall.setup.user.UserActivity;
import com.haocean.dinninghall.view.MyGridView;

import me.nereo.multi_image_selector.MultiImageSelector;


/**
 * Created by haocean on 2016/7/7.
 */
public class FragmentLoginTitle extends Fragment{
    private ImageView backDocment;
    RelativeLayout header;
    private TextView realname,school;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);//初始化fragment
         realname=(TextView) view.findViewById(R.id.realname);
         school=(TextView) view.findViewById(R.id.school);
        setData();
        backDocment= (ImageView) view.findViewById(R.id.backDocment);
        header=(RelativeLayout)view.findViewById(R.id.header);
        header.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent geren=new Intent(getActivity(), UserActivity.class);
                System.out.println("进入个人信息");
                startActivityForResult(geren, 2);
            }
        });
        backDocment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=  getActivity().getSupportFragmentManager();
                Fragment fragment=  fragmentManager.findFragmentByTag("twoFragment");
                if(fragment!=null){
                    fragmentManager.beginTransaction().remove(fragment).commit();
                    backDocment.setVisibility(View.GONE);
                }

            }
        });

        return view;
    }
    public void setData(){
        System.out.println("UserData.getRealname()"+UserData.getRealname());
        realname.setText(UserData.getRealname());
        System.out.println("UserData.getSchoolName()"+UserData.getSchoolName());
        school.setText(UserData.getSchoolName());
    }
    public void showBack(){
            backDocment.setVisibility(View.VISIBLE);
    }
    public void hideBack(){
        backDocment.setVisibility(View.GONE);
    }


}
