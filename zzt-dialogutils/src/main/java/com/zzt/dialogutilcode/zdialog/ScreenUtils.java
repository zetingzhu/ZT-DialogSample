package com.zzt.dialogutilcode.zdialog;

import static android.Manifest.permission.WRITE_SETTINGS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import com.zzt.dialogutilcode.MyApplication;


public final class ScreenUtils {

    public static int getAppScreenWidth() {
        WindowManager wm = (WindowManager) MyApplication.getApplication().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return -1;
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point.x;
    }

    public static int getAppScreenHeight() {
        WindowManager wm = (WindowManager) MyApplication.getApplication().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return -1;
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point.y;
    }

}
