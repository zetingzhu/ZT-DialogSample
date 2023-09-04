package com.zzt.dialogutilcode;

import android.app.Application;

/**
 * @author: zeting
 * @date: 2022/11/9
 */
public class MyApplication extends Application {
    private static MyApplication myApplication = null;

    public static MyApplication getApplication() {

        return myApplication;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (myApplication == null) {
            myApplication = this;
        }
    }
}
