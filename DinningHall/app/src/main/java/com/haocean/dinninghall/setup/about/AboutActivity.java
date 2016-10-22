package com.haocean.dinninghall.setup.about;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.manManagement.CreateTitleFragment;


/**
 * Created by haocean on 2016/10/17.
 */
public class AboutActivity extends Activity implements View.OnClickListener{

    public  void init() {
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_context, new AboutFragment(),"aboutFragment").commit();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);


        init();

    }

    @Override
    public void onClick(View view) {

    }
}
