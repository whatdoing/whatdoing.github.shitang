package com.haocean.dinninghall.contexts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

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
    public static void cereateDateDialog(final Button button, final Activity activity){
        final Calendar c = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity,
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int month, int day) {

                      final int  mYear = year;
                        final int   mMonth = month;
                        final int  mDay = day;
                        new TimePickerDialog(activity,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hour, int minute) {
                                     int   mHour = hour;
                                        int   mMinute = minute;
                                        //更新EditText控件时间 小于10加0
                                        button.setText(new StringBuilder().append(mYear).append("-")
                                                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                                                .append("-").append((mDay < 10) ? "0" + mDay : mDay).append(" ") .append(mHour < 10 ? "0" + mHour : mHour).append(":")
                                                .append(mMinute < 10 ? "0" + mMinute : mMinute).append(":00"));

                                    }
                                }, c.get(Calendar.HOUR_OF_DAY),
                                c.get(Calendar.MINUTE), true).show();
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
