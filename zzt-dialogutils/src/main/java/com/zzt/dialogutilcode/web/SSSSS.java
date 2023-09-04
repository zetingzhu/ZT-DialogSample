package com.zzt.dialogutilcode.web;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.WebView;

/**
 * @author: zeting
 * @date: 2022/11/10
 */
public class SSSSS {
    // 运行在 UI 线程的 Handler, 用于运行监听器回调
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    public SSSSS() {
    }


    public static Bitmap shotWebView(WebView webView) {
        // Android5.0以上
        float scale = webView.getScale();
        int width = webView.getWidth();
        int height = (int) (webView.getContentHeight() * scale + 0.5);
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        webView.draw(canvas);
        return bitmap;
    }


}
