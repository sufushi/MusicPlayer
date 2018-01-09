package com.rdc.musicplayer.musicplayer.app;

import android.app.Application;

import com.tencent.bugly.Bugly;

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashReport.initCrashReport(getApplicationContext(), "0cdc653082", true);
        Bugly.init(getApplicationContext(),"0cdc653082",false);
    }


}
