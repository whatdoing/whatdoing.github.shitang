package com.haocean.dinninghall.adapter.recordview;

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class SafetyView {
    //只需要在每个ID上设置对应的注解即可


    @FindIdAnno(R.id.rectification)
    public TextView rectification;

    @FindIdAnno(R.id.reportname)
    public TextView reportname;
    @FindIdAnno(R.id.report_date)
    public TextView report_date;
    @FindIdAnno(R.id.create_date)
    public TextView create_date;
    @FindIdAnno(R.id.id)
    public TextView id;
    @FindIdAnno(R.id.bianji)
    public ImageView bianji;
    @FindIdAnno(R.id.shanchu)
    public ImageView shanchu;

}
