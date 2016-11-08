package com.haocean.dinninghall.contexts;

import android.app.TimePickerDialog;
import android.content.Context;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class mtimePicket extends TimePickerDialog {
    public mtimePicket(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }

    public mtimePicket(Context context, int themeResId, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, themeResId, listener, hourOfDay, minute, is24HourView);
    }

    @Override
    protected void onStop() {
//          注释这里，onstop就不会调用回调方法
//          super.onStop();
    }


}
