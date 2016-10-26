package com.haocean.dinninghall.contexts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.DatePicker;

import com.haocean.dinninghall.AppController;

import java.util.Calendar;

/**
 * Created by haocean on 2016/9/25.
 */
public class MyDialogs {
    //删除提示框
    public static void  isAlertDialog(String strMessage, Context mcontext, final Thread myThread){

        android.app.Dialog dialog=new AlertDialog.Builder((Activity)mcontext)
                .setTitle("温馨提示")
                .setMessage(strMessage)
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        myThread.start();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
        dialog.show();
    }
    //提示框
    public static void isAlertDialog(String strMessage,Context mcontext){
        Dialog dialog=new AlertDialog.Builder(mcontext)
                .setTitle("温馨提示")
                .setMessage(strMessage)
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).create();
        dialog.show();
    }

    //日期提示框
    public static void cereateDateDialog(final Button button,Activity activity){
        Calendar c = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity,
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        button.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    }
                }
                // 设置初始日期
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH)).show();
    }

    static Dialog dialog;
    //下拉框
    public static Dialog cereateDialog(final Button mview, final String[] hasLicenseString,Context mcontext){
        dialog = new AlertDialog.Builder(mcontext)
                .setTitle("请选择...").setSingleChoiceItems(hasLicenseString, 0,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0,int arg1) {
                        mview.setText(hasLicenseString[arg1].toString());
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
        return dialog;
    }
}
