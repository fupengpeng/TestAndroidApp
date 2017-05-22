package com.fupengpeng.testandroidapp.mvp.utils;

import com.fupengpeng.testandroidapp.R;
import com.fupengpeng.testandroidapp.mvp.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fp on 2017/5/21.
 */

public class DataUtils {
    public static final List<Girl> DATA = new ArrayList<Girl>();

    public static Girl get(int i){
        return DATA.get(i);
    }
    public static int size(){
        return DATA.size();
    }

    static{
        DATA.add(new Girl(R.drawable.meinv01,"五颗星","阡陌媚儿初夏01"));
        DATA.add(new Girl(R.drawable.meinv02,"三颗星","阡陌媚儿初夏02"));
        DATA.add(new Girl(R.drawable.meinv03,"五颗星","阡陌媚儿初夏03"));
        DATA.add(new Girl(R.drawable.meinv04,"四颗星","阡陌媚儿初夏04"));
        DATA.add(new Girl(R.drawable.meinv05,"五颗星","阡陌媚儿初夏05"));
        DATA.add(new Girl(R.drawable.meinv06,"二颗星","阡陌媚儿初夏06"));
        DATA.add(new Girl(R.drawable.meinv07,"五颗星","阡陌媚儿初夏07"));
        DATA.add(new Girl(R.drawable.meinv08,"四颗星","阡陌媚儿初夏08"));
        DATA.add(new Girl(R.drawable.meinv09,"四颗星","阡陌媚儿初夏09"));
        DATA.add(new Girl(R.drawable.meinv10,"三颗星","阡陌媚儿初夏10"));

    }
}
