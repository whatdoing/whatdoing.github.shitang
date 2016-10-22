package com.haocean.dinninghall.adapter.recordview;

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class ReviewRecordView {
    //只需要在每个ID上设置对应的注解即可
    @FindIdAnno(R.id.create_date)
    public TextView create_date;
    @FindIdAnno(R.id.rectification_content)
    public TextView rectification_content;
    @FindIdAnno(R.id.rectification_opinions)
    public TextView rectification_opinions;
    @FindIdAnno(R.id.find_date)
    public TextView find_date;
    @FindIdAnno(R.id.period_time)
    public TextView period_time;
    @FindIdAnno(R.id.review_time)
    public TextView review_time;

    @FindIdAnno(R.id.review_results)
    public TextView review_results;
    @FindIdAnno(R.id.liable_person)
    public TextView liable_person;
    @FindIdAnno(R.id.liable_date)
    public TextView liable_date;


    @FindIdAnno(R.id.bianji)
    public ImageView bianji;
    @FindIdAnno(R.id.shanchu)
    public ImageView shanchu;
}
