package com.fupengpeng.testandroidapp.mvp.presenter;

import com.fupengpeng.testandroidapp.mvp.bean.Girl;
import com.fupengpeng.testandroidapp.mvp.model.GirlModelImplV1;
import com.fupengpeng.testandroidapp.mvp.model.IGirlModel;
import com.fupengpeng.testandroidapp.mvp.view.IGirlView;

import java.util.List;

/**
 * Created by fp on 2017/5/21.
 */

public class GirlPresenterV1 {
    //view interface
    IGirlView mGirlView;

    //model interface
    IGirlModel mGirlModel = new GirlModelImplV1();

    //通过构造方法实例化mIGirlView
    public GirlPresenterV1(IGirlView mIGirlView) {
        this.mGirlView = mIGirlView;
    }

    /**
     * bind view and model
     *   view调用GirlPresenterVl的fetch方法就可以从model中获取到数据
     */
    public void fetch(){
        //显示进度条
        mGirlView.showLoading();

        //让model load data
        if (mGirlModel != null){
            mGirlModel.loadGirl(new IGirlModel.GirlOnLoadListener() {
                @Override
                public void onComplete(List<Girl> girls) {
                    //给view显示
                    mGirlView.showGirls(girls);
                }
            });
        }
    }

    /*public void setmGirlModel(IGirlModel mGirlModel) {
        this.mGirlModel = mGirlModel;
    }*/
    //策略模式
    public void setmGirlModel(int i){
        switch (i){
            case 1:
                mGirlModel = new GirlModelImplV1();//实例化model接口001  本地获取数据
                break;
            case 2:
                mGirlModel = new GirlModelImplV1();//实例化model接口002  获取网络数据
                break;
            default:

                break;
        }
    }
}
