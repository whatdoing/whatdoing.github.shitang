package com.haocean.dinninghall.adapter.recordview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by haocean on 2016/9/27.
 */
//作用域为属性
@Target(ElementType.FIELD)
//运行时的有效
@Retention(RetentionPolicy.RUNTIME)
public @interface FindIdAnno {
    //属性上的值为int类型
    int value();
}