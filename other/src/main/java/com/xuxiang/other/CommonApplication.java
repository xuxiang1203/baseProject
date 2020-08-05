package com.xuxiang.other;

import android.app.Application;

public class CommonApplication extends Application {
    private static CommonApplication instance;

    public static CommonApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
