package com.zzt.dialogutilcode.zdialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;

import com.zzt.dialogutilcode.R;


/**
 * @author: zeting
 * @date: 2022/6/20
 * Unable to add window -- token android.os.BinderProxy@803d3d8 is not valid; is your activity running?
 * 继承这个，各种判断 show()和 dismiss() 方法报错的
 */
public class BaseShowDismissAppCompatDialog extends AppCompatDialog {


    public BaseShowDismissAppCompatDialog(@NonNull Context context) {
        super(context);
    }

    public BaseShowDismissAppCompatDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseShowDismissAppCompatDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        WindowManager.LayoutParams attributes = getWindow().getAttributes();
//        getWindow().getDecorView().setPadding(0, 0, 0, 0);
//        getWindow().setAttributes(attributes);
        calcWindowWidth(getWindow());
    }


    public void calcWindowWidth(Window window) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams params = window.getAttributes();
        int screenWidth = ScreenUtils.getAppScreenWidth();
        int width = (int) getContext().getResources().getDimension(R.dimen.margin_365dp);
        if (width >= screenWidth * 0.9) {
            params.width = (int) (screenWidth * 0.75);
        } else {
            params.width = width;
        }
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public void setContentView(View view) {
//        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(view);
    }

    @Override
    public void show() {
        View title_view = getWindow().findViewById(android.R.id.title);
        if (title_view != null) {
            title_view.setVisibility(View.GONE);
        }
        if (ActivityUtil.isActivityAlive(getContext())) {
            super.show();
        }
    }

    @Override
    public void dismiss() {
        if (ActivityUtil.isActivityAlive(getContext())) {
            super.dismiss();
        }
    }
}
