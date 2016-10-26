package com.haocean.dinninghall.safety;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haocean.dinninghall.R;
import com.haocean.dinninghall.Runnable.DetailRunnable;
import com.haocean.dinninghall.adapter.ListViewAdapter;
import com.haocean.dinninghall.contexts.RecordList;

public class DetailActivity extends Activity implements View.OnClickListener{
    public   String typeRecord;
    private Fragment fragment;
    private String id;
    private  String tempString;
    ImageView back;
    DetailRunnable detailRunnable;
    public  void init() {
        back=(ImageView)findViewById(R.id.back);
        Intent intent = getIntent();
        typeRecord = intent.getStringExtra("typeRecord");
        //tempString=intent.getStringExtra("tempString");
        id=intent.getStringExtra("id");


    }
    public void Run(){
        detailRunnable=new DetailRunnable(typeRecord,id,handlist);
        Thread thread=new Thread(detailRunnable);
        thread.start();
    }
    Handler handlist = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            switch(msg.what){
                case 0:
                   tempString=detailRunnable.getContacts();
                    FragmentManager fragmentManager= getFragmentManager();
                    //解析tempString
                    fragment= RecordList.DetailFragment.get(typeRecord);
                    fragmentManager.beginTransaction().replace(R.id.fragment_context, fragment,"detailFragment").commit();
                    break;
                case 1:
                    Toast.makeText(DetailActivity.this, "数据无法读取，请重试！", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    public String getTempString(){
        return tempString;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        Run();
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back: {
                finish();
            }
            break;
        }
    }
}
