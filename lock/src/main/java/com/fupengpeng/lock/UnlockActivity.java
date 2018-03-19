package com.fupengpeng.lock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fupengpeng.lock.util.PreferenceUtil;
import com.fupengpeng.lock.view.LockPatternView;


public class UnlockActivity extends AppCompatActivity {
    public static final String TAG = "UnlockActivity";

    private LockPatternView mLockPatternView;
    private String mPasswordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        Log.e(TAG, "onCreate: " + "002");

        mLockPatternView = (LockPatternView) findViewById(R.id.lockView);

        mLockPatternView.setLockListener(new LockPatternView.OnLockListener() {
            String password = PreferenceUtil.getGesturePassword(UnlockActivity.this);

            @Override
            public void getStringPassword(String password) {
                mPasswordStr = password;
            }

            @Override
            public boolean isPassword() {
                if (mPasswordStr.equals(password)) {
                    Log.e(TAG, "isPassword: " + "密码正确");
                    Toast.makeText(UnlockActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UnlockActivity.this, MainActivity.class);
                    startActivity(intent);
                    UnlockActivity.this.finish();
//                    return true;
                } else {
                    Log.e(TAG, "isPassword: " + "密码不正确");
                    Toast.makeText(UnlockActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }

}
