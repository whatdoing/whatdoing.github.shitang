package com.haocean.dinninghall.adapter.recordview;

import android.widget.ImageView;
import android.widget.TextView;

import com.haocean.dinninghall.R;


/**
 * Created by haocean on 2016/9/27.
 */
public class RecyclingView{
 //只需要在每个ID上设置对应的注解即可
        @FindIdAnno(R.id.create_date)
        public TextView create_date;
         @FindIdAnno(R.id.swillnum)
        public TextView swillnum;
        @FindIdAnno(R.id.recyclinguse)
        public TextView recyclinguse;
        @FindIdAnno(R.id.catering_signature)
        public TextView catering_signature;
        @FindIdAnno(R.id.recovery_signature)
        public TextView recovery_signature;
       @FindIdAnno(R.id.recovery_company)
       public TextView recovery_company;
        @FindIdAnno(R.id.bianji)
        public ImageView bianji;
        @FindIdAnno(R.id.shanchu)
        public ImageView shanchu;

}
