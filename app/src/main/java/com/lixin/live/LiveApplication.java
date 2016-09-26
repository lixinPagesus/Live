package com.lixin.live;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lixin.utils.ConstantLive;
import com.mob.mobapi.MobAPI;

/**
 * Created by lixin on 16/8/11.
 */
public class LiveApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

     // 要在任何操作之前至少要调用一次initSDK来完成SDK的初始化
        MobAPI.initSDK(this, ConstantLive.APPKEY);

        Fresco.initialize(this);
    }
}
