package com.zzt.dialogutilcode.zdialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.zzt.dialogutilcode.R;
import com.zzt.dialogutilcode.util.QMUIDrawableHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public abstract class ZDialogBuilder<T extends ZDialogBuilder> {
    private static final String TAG = ZDialogBuilder.class.getSimpleName();

    private Context mContext;
    protected BaseShowDismissAppCompatDialog mDialog;
    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;

    // dialog 样式
    private int mStyle;

    // 弹框根布局
    private ZDialogRootView mDialogRootView;

    public ZDialogBuilder(Context context) {
        this.mContext = context;
    }

    public ZDialogBuilder(Context context, int style) {
        this.mContext = context;
        this.mStyle = style;

        // 创建布局
        mDialogRootView = new ZDialogRootView(mContext);
    }

    public Context getBaseContext() {
        return mContext;
    }

    public ZDialogRootView getDialogRootView() {
        return mDialogRootView;
    }

    /**
     * 设置弹框 style
     */
    @SuppressWarnings("unchecked")
    public T setStyle(int mStyle) {
        this.mStyle = mStyle;
        return (T) this;
    }

    /**
     * 添加对话框是否可以取消
     */
    @SuppressWarnings("unchecked")
    public T setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return (T) this;
    }

    /**
     * 添加对话框外部是否可以点击
     */
    @SuppressWarnings("unchecked")
    public T setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return (T) this;
    }

    /**
     * 产生一个 Dialog 并显示出来
     */
    public BaseShowDismissAppCompatDialog show() {
        final BaseShowDismissAppCompatDialog dialog = create();
        dialog.show();
        return dialog;
    }

    /**
     * 只产生一个 Dialog, 不显示出来
     *
     * @see #create(int)
     */
    public BaseShowDismissAppCompatDialog create() {
        return create(mStyle);
    }

    /**
     * 产生一个Dialog，但不显示出来。
     *
     * @param style Dialog 的样式
     * @see #create()
     */
    @SuppressLint("InflateParams")
    public BaseShowDismissAppCompatDialog create(@StyleRes int style) {
        mDialog = new BaseShowDismissAppCompatDialog(mContext, style);
        // 设置对话框
        mDialogRootView.setShowDialog(mDialog);
        // 添加自定义内容信息
        View contentLayout = onCreateContent(mDialog, mContext);
        mDialogRootView.addView(contentLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mDialog.addContentView(mDialogRootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mDialog.setCancelable(mCancelable);
        mDialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
        return mDialog;
    }

    /**
     * 创建中间内容
     *
     * @param dialog
     * @param context
     * @return
     */
    @Nullable
    protected abstract View onCreateContent(@NonNull BaseShowDismissAppCompatDialog dialog, @NonNull Context context);

}
