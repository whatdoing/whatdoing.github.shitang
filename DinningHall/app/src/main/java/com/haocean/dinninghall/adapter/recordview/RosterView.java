package com.haocean.dinninghall.adapter.recordview;

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
public class RosterView {
    //只需要在每个ID上设置对应的注解即可

    @FindIdAnno(R.id.name)
    public TextView name;
    @FindIdAnno(R.id.sex)
    public TextView sex;
    @FindIdAnno(R.id.age)
    public TextView age;
    @FindIdAnno(R.id.jobtype)
    public TextView jobtype;
    @FindIdAnno(R.id.department)
    public TextView department;
    @FindIdAnno(R.id.takework_date)
    public TextView takework_date;
    @FindIdAnno(R.id.phonenumber)
    public TextView phonenumber;
    @FindIdAnno(R.id.healthcode)
    public TextView healthcode;

    @FindIdAnno(R.id.maturity_date)
    public TextView maturity_date;

    @FindIdAnno(R.id.healthunit)
    public TextView healthunit;
    @FindIdAnno(R.id.iscanteen)
    public TextView iscanteen;
    @FindIdAnno(R.id.safetymanager)
    public TextView safetymanager;

    @FindIdAnno(R.id.bianji)
    public ImageView bianji;
    @FindIdAnno(R.id.shanchu)
    public ImageView shanchu;
}
