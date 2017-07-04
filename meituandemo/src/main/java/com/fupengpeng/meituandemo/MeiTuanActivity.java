package com.fupengpeng.meituandemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class MeiTuanActivity extends Activity implements MyScrollView.OnScrollListener {

    public static final String TAG = "MeiTuanActivity";
    /**
     * 自定义的MyScrollView
     */
    private MyScrollView myScrollView;
    /**
     * 在MyScrollView里面的购买布局
     */
    private LinearLayout mBuyLayout;
    /**
     * 位于顶部的购买布局
     */
    private LinearLayout mTopBuyLayout;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_tuan);

        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        mBuyLayout = (LinearLayout) findViewById(R.id.buy);
        mTopBuyLayout = (LinearLayout) findViewById(R.id.top_buy_layout);

        myScrollView.setOnScrollListener(this);

        Log.e(TAG, "onCreate: "+"001" );
        //当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                Log.e(TAG, "onGlobalLayout: "+"002" );
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(myScrollView.getScrollY());

            }
        });
    }




    @Override
    public void onScroll(int scrollY) {

        Log.e(TAG, "onScroll: "+"004" );
        int mBuyLayout2ParentTop = Math.max(scrollY, mBuyLayout.getTop());
        mTopBuyLayout.layout(0, mBuyLayout2ParentTop, mTopBuyLayout.getWidth(), mBuyLayout2ParentTop + mTopBuyLayout.getHeight());
    }


}
