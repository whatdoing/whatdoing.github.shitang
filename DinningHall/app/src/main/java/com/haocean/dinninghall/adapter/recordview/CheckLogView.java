package com.haocean.dinninghall.adapter.recordview;

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class CheckLogView {
    //只需要在每个ID上设置对应的注解即可


    @FindIdAnno(R.id.inspect_company)
    public TextView inspect_company;

    @FindIdAnno(R.id.inspect_date)
    public TextView inspect_date;
    @FindIdAnno(R.id.inspect_people)
    public TextView inspect_people;
    @FindIdAnno(R.id.create_date)
    public TextView create_date;
    @FindIdAnno(R.id.log_result)
    public TextView log_result;
    @FindIdAnno(R.id.bianji)
    public ImageView bianji;
    @FindIdAnno(R.id.shanchu)
    public ImageView shanchu;
}
