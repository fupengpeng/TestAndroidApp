package com.fupengpeng.testandroidapp.mvp.model;

import com.fupengpeng.testandroidapp.mvp.bean.Girl;

import java.util.List;

/**
 * Created by fp on 2017/5/21.
 *     单一职责
 */

public interface IGirlModel {
    /**
     * 加载数据
     */
    void loadGirl(GirlOnLoadListener listener);

    /**
     * 加载完成的监听
     */
    interface GirlOnLoadListener{
        void onComplete(List<Girl> girls);
    }
}
