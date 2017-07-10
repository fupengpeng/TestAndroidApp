package com.fupengpeng.appservice;

import android.app.Application;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by fupengpeng on 2017/7/10 0010.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化NoHttp
        NoHttp.initialize(this);

        // 开始NoHttp的调试模式, 这样就能看到请求过程和日志（可选）
        Logger.setTag("NoHttpSample");
        Logger.setDebug(true);
    }


}
