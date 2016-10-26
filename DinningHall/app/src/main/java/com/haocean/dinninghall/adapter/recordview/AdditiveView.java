package com.haocean.dinninghall.adapter.recordview;

/**
 * Created by haocean on 2016/9/27.
 */

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;


public class  AdditiveView{

    //只需要在每个ID上设置对应的注解即可
    @FindIdAnno(R.id.create_date)
    public TextView create_date;
    @FindIdAnno(R.id.name)
    public TextView name;
    @FindIdAnno(R.id.manufacturer)
    public TextView manufacturer;
    @FindIdAnno(R.id.manufacture_date)
    public TextView manufacture_date;
    @FindIdAnno(R.id.quality)
    public TextView quality;
    @FindIdAnno(R.id.supplyunit)
    public TextView supplyunit;
    @FindIdAnno(R.id.purchase_date)
    public TextView purchase_date;
    @FindIdAnno(R.id.purchasenum)
    public TextView purchasenum;
	 @FindIdAnno(R.id.userecord)
    public TextView userecord;
	 @FindIdAnno(R.id.destruction_record)
    public TextView destruction_record;
	 @FindIdAnno(R.id.liable_person)
    public TextView liable_person;
    @FindIdAnno(R.id.id)
    public TextView id;
	
    @FindIdAnno(R.id.bianji)
    public ImageView bianji;
    @FindIdAnno(R.id.shanchu)
    public ImageView shanchu;

}

