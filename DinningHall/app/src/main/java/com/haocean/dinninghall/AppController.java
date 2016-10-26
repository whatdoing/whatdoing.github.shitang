package com.haocean.dinninghall;

import android.app.Application;

import im.fir.sdk.FIR;

public class AppController extends Application{
	
	private static AppController instance;
	
	@Override
    public void onCreate() {
        super.onCreate();
        FIR.init(this);
        instance = this;
    }

    public static AppController getInstance() {
        if (null == instance) {
            instance = new AppController();
        }
        return instance;
    }
}
