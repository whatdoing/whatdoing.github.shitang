package com.haocean.dinninghall.adapter.recordview;

/**
 * Created by haocean on 2016/9/27.
 */

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;

public class  FoodView{

    //只需要在每个ID上设置对应的注解即可
    @FindIdAnno(R.id.create_date)
    public TextView create_date;
    @FindIdAnno(R.id.number)
    public TextView number;
    @FindIdAnno(R.id.foodname)
    public TextView foodname;
    @FindIdAnno(R.id.foodcount)
    public TextView foodcount;
    @FindIdAnno(R.id.start_date)
    public TextView start_date;
    @FindIdAnno(R.id.end_date)
    public TextView end_date;
    @FindIdAnno(R.id.peoplenum)
    public TextView peoplenum;
    @FindIdAnno(R.id.liable_person)
    public TextView liable_person;
	
    @FindIdAnno(R.id.bianji)
    public ImageView bianji;
    @FindIdAnno(R.id.shanchu)
    public ImageView shanchu;

}
