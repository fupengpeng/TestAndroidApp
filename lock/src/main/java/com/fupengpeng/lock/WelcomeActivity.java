package com.fupengpeng.lock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fupengpeng.lock.util.PreferenceUtil;


/**
 * @author fupengpeng
 * @description 九宫格解锁
 * @date 2018/3/19 0019 14:09
 */
public class WelcomeActivity extends AppCompatActivity {
    public static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.e(TAG, "run: " + "001");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String passwordStr = PreferenceUtil.getGesturePassword(WelcomeActivity.this);

                Intent intent;
                if (passwordStr == "") {
                    Log.d("TAG", "true");
                    Log.e(TAG, "run: " + "true");
                    intent = new Intent(WelcomeActivity.this, SetLockActivity.class);
                } else {
                    Log.e(TAG, "run: " + "false");
                    intent = new Intent(WelcomeActivity.this, UnlockActivity.class);
                }
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }, 2000);
    }
}
