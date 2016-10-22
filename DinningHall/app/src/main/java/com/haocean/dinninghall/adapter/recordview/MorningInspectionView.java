package com.haocean.dinninghall.adapter.recordview;

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
public class MorningInspectionView {
    //只需要在每个ID上设置对应的注解即可

    @FindIdAnno(R.id.create_date)
    public TextView create_date;
    @FindIdAnno(R.id.name)
    public TextView name;
    @FindIdAnno(R.id.healthycode)
    public TextView healthycode;
    @FindIdAnno(R.id.liable_person)
    public TextView liable_person;
    @FindIdAnno(R.id.isdiarrhea)
    public TextView isdiarrhea;
    @FindIdAnno(R.id.istrauma)
    public TextView istrauma;
    @FindIdAnno(R.id.iscold)
    public TextView iscold;
    @FindIdAnno(R.id.ispurulent)
    public TextView ispurulent;
    @FindIdAnno(R.id.isotherhealth)
    public TextView isotherhealth;
    @FindIdAnno(R.id.morningcheck)
    public TextView morningcheck;





    @FindIdAnno(R.id.bianji)
    public ImageView bianji;
    @FindIdAnno(R.id.shanchu)
    public ImageView shanchu;

}
