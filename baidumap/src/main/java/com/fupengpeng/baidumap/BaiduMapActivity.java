package com.fupengpeng.baidumap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaiduMapActivity extends AppCompatActivity {

    /*
    1.将百度地图引入到我们的app
    2.引入定位功能、集合方向传感器实现方向定位
    3.添加覆盖物、覆盖物的点击事件
    4.地图模式切换
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map);
    }
}
