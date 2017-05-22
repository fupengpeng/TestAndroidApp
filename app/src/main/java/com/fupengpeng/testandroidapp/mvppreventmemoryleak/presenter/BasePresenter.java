package com.fupengpeng.testandroidapp.mvppreventmemoryleak.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by fp on 2017/5/22.
 */

public abstract class BasePresenter<T> {
    /**
     * 当内存不足可释放内存
     */
    protected WeakReference<T> mViewRef;

    /**
     * bind p with v
     * @param view
     */
    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);//弱引用
    }
    //解除管理
    public void detachView(){
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
    protected T getView(){
        return mViewRef.get();
    }
}
