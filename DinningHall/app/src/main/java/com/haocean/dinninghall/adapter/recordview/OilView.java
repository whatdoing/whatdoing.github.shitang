package com.haocean.dinninghall.adapter.recordview;

/**
 * Created by haocean on 2016/9/27.
 */

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;

public class  OilView{

    //只需要在每个ID上设置对应的注解即可

    @FindIdAnno(R.id.create_date)
    public TextView create_date;
    @FindIdAnno(R.id.oilnum)
    public TextView oilnum;
    @FindIdAnno(R.id.recyclinguse)
    public TextView recyclinguse;
    @FindIdAnno(R.id.catering_signature)
    public TextView catering_signature;
    @FindIdAnno(R.id.recovery_signature)
    public TextView recovery_signature;
    @FindIdAnno(R.id.recovery_company)
    public TextView recovery_company;
    @FindIdAnno(R.id.recovery_address)
    public TextView recovery_address;
	 @FindIdAnno(R.id.contacts)
    public TextView contacts;
	 @FindIdAnno(R.id.contactsphone)
    public TextView contactsphone;

    @FindIdAnno(R.id.id)
    public TextView id;
    @FindIdAnno(R.id.bianji)
    public ImageView bianji;
    @FindIdAnno(R.id.shanchu)
    public ImageView shanchu;

}
