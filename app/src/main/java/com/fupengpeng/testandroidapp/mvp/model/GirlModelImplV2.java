package com.fupengpeng.testandroidapp.mvp.model;

import android.util.Log;

import com.fupengpeng.testandroidapp.R;
import com.fupengpeng.testandroidapp.mvp.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fp on 2017/5/21.
 */

public class GirlModelImplV2 implements IGirlModel {
    public static final String TAG = "GirlModelImplV2";
    @Override
    public void loadGirl(GirlOnLoadListener listener) {
        //模拟从网络加载数据

        Log.e(TAG, "loadGirl: "+"模拟从网络获取数据" );
        //加载数据
        List<Girl> data = new ArrayList<Girl>();
        data.add(new Girl(R.drawable.meinv11,"五颗星","阡陌媚儿初夏11"));
        data.add(new Girl(R.drawable.meinv12,"三颗星","阡陌媚儿初夏12"));
        data.add(new Girl(R.drawable.meinv13,"五颗星","阡陌媚儿初夏13"));
        data.add(new Girl(R.drawable.meinv14,"四颗星","阡陌媚儿初夏14"));
        data.add(new Girl(R.drawable.meinv15,"五颗星","阡陌媚儿初夏15"));
        data.add(new Girl(R.drawable.meinv16,"二颗星","阡陌媚儿初夏16"));
        data.add(new Girl(R.drawable.meinv17,"五颗星","阡陌媚儿初夏17"));
        data.add(new Girl(R.drawable.meinv18,"四颗星","阡陌媚儿初夏18"));
        data.add(new Girl(R.drawable.meinv19,"四颗星","阡陌媚儿初夏19"));
        data.add(new Girl(R.drawable.meinv20,"三颗星","阡陌媚儿初夏20"));


        //回调监听
        listener.onComplete(data);
    }
}
