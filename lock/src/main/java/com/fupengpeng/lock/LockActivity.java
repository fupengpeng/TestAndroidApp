package com.fupengpeng.lock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LockActivity extends AppCompatActivity {

    /*
    九宫格解锁
        状态：
            1.按下
            2.正确
            3.错误
            4.解锁状态

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
    }
}
