package com.umeng.soexample;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //配置平台信息
        PlatformConfig.setQQZone("1105804663", "OeRspAQmaeqhtq38");
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
    }


}
