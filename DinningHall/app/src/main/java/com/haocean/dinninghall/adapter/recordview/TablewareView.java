package com.haocean.dinninghall.adapter.recordview;

/**
 * Created by haocean on 2016/9/27.
 */

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;

public class  TablewareView{


    //只需要在每个ID上设置对应的注解即可
    @FindIdAnno(R.id.create_date)
    public TextView create_date;
//    @FindIdAnno(R.id.tablewarename)
//    public TextView tablewarename;
//    @FindIdAnno(R.id.tablewarecount)
//    public TextView tablewarecount;
    @FindIdAnno(R.id.cleantype)
    public TextView cleantype;
    @FindIdAnno(R.id.disinfection_type)
    public TextView disinfection_type;
    @FindIdAnno(R.id.id)
    public TextView id;
    @FindIdAnno(R.id.start_date)
    public TextView start_date;
    @FindIdAnno(R.id.end_date)
    public TextView end_date;
    @FindIdAnno(R.id.clean_condition)
    public TextView clean_condition;
    @FindIdAnno(R.id.liable_person)
    public TextView liable_person;
    @FindIdAnno(R.id.canteenName)
    public TextView canteenName;
    @FindIdAnno(R.id.tableandroidnum)
    public TextView tableandroidnum;
    @FindIdAnno(R.id.bianji)
    public ImageView bianji;
    @FindIdAnno(R.id.shanchu)
    public ImageView shanchu;

}