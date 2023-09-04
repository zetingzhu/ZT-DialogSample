package com.zzt.dialogutilcode.util;

import android.graphics.drawable.Drawable;

/**
 * @author: zeting
 * @date: 2022/11/7
 */
public class AppInfo {
    private Drawable icon;
    private String appName;
    private String packageName;

    @Override
    public String toString() {
        return "AppInfo{" +
                "icon=" + icon +
                ", appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }

    public AppInfo(Drawable icon, String appName, String packageName) {
        this.icon = icon;
        this.appName = appName;
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
