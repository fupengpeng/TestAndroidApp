package com.fupengpeng.testandroidapp.mvp.view;

import com.fupengpeng.testandroidapp.mvp.bean.Girl;

import java.util.List;

/**
 * Created by fp on 2017/5/21.
 */

public interface IGirlView {
    /**
     * 加载进度条
     */
    void showLoading();
    /**
     * 显示girls（数据）
     * @param girls
     */
    void showGirls(List<Girl> girls);

}
