package com.fupengpeng.testandroidapp.mvp.model;

import com.fupengpeng.testandroidapp.R;
import com.fupengpeng.testandroidapp.mvp.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fp on 2017/5/21.
 */

public class GirlModelImplV1 implements IGirlModel {
    /**
     * 加载数据的实现
     * @param listener
     */
    @Override
    public void loadGirl(GirlOnLoadListener listener) {
        //加载数据
        List<Girl> data = new ArrayList<Girl>();
        data.add(new Girl(R.drawable.meinv01,"五颗星","阡陌媚儿初夏01"));
        data.add(new Girl(R.drawable.meinv02,"三颗星","阡陌媚儿初夏02"));
        data.add(new Girl(R.drawable.meinv03,"五颗星","阡陌媚儿初夏03"));
        data.add(new Girl(R.drawable.meinv04,"四颗星","阡陌媚儿初夏04"));
        data.add(new Girl(R.drawable.meinv05,"五颗星","阡陌媚儿初夏05"));
        data.add(new Girl(R.drawable.meinv06,"二颗星","阡陌媚儿初夏06"));
        data.add(new Girl(R.drawable.meinv07,"五颗星","阡陌媚儿初夏07"));
        data.add(new Girl(R.drawable.meinv08,"四颗星","阡陌媚儿初夏08"));
        data.add(new Girl(R.drawable.meinv09,"四颗星","阡陌媚儿初夏09"));
        data.add(new Girl(R.drawable.meinv10,"三颗星","阡陌媚儿初夏10"));


        //回调监听
        listener.onComplete(data);
    }
}
