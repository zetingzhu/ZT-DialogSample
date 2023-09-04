package com.zzt.dialogutilcode.zdialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author: zeting
 * @date: 2022/11/4
 * 弹框的文本操作内容
 */
public class ZDialogTextView {
    private CharSequence mStr;
    private AppCompatTextView mTextView;
    // 设置字体颜色
    private ColorStateList mTextColor;
    // 设置字体大小 SP
    private int mTextSize;
    // 设置添加位置索引
    private int addIndex;

    // 设置间距
    private int lpLeftMargin = 0,
            lpTopMargin = 0,
            lpRightMargin = 0,
            lpBottomMargin = 0;
    // 设置字体
    private int typefaceStyle = Typeface.NORMAL;

    private int mGravity = Gravity.TOP | Gravity.START;

    public ZDialogTextView(Context context, int strRes) {
        this(context.getResources().getString(strRes));
    }

    public ZDialogTextView(CharSequence str) {
        mStr = str;
    }

    public AppCompatTextView buildTextView(final BaseShowDismissAppCompatDialog dialog, final int index) {
        this.addIndex = index;
        mTextView = generateTextView(dialog.getContext(), mStr);
        if (mTextColor != null) {
            mTextView.setTextColor(mTextColor);
        }
        if (mTextSize > 0) {
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
        }
        mTextView.setGravity(mGravity);

        mTextView.setTypeface(null, typefaceStyle);
        return mTextView;
    }

    public LinearLayout.LayoutParams buildLayoutParams() {
        LinearLayout.LayoutParams mTVLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTVLp.leftMargin = lpLeftMargin;
        mTVLp.topMargin = lpTopMargin;
        mTVLp.rightMargin = lpRightMargin;
        mTVLp.bottomMargin = lpBottomMargin;
        return mTVLp;
    }

    private AppCompatTextView generateTextView(Context context, CharSequence text) {
        AppCompatTextView button = new AppCompatTextView(context);
        button.setBackground(null);
        button.setMinHeight(0);
        button.setMinimumHeight(0);
        button.setText(text);
        return button;
    }

    public int getTypefaceStyle() {
        return typefaceStyle;
    }

    public ZDialogTextView setTypefaceStyle(int typefaceStyle) {
        this.typefaceStyle = typefaceStyle;
        return this;
    }

    public ZDialogTextView setTextColor(@ColorInt int color) {
        mTextColor = ColorStateList.valueOf(color);
        return this;
    }

    public ZDialogTextView setTextSize(int size) {
        mTextSize = mTextSize;
        return this;
    }

    public int getGravity() {
        return mGravity;
    }

    public ZDialogTextView setGravity(int mGravity) {
        this.mGravity = mGravity;
        return this;
    }

    public int getAddIndex() {
        return addIndex;
    }

    public ZDialogTextView setLpTopMargin(int lpTopMargin) {
        this.lpTopMargin = lpTopMargin;
        return this;
    }

    public ZDialogTextView setLpBottomMargin(int lpBottomMargin) {
        this.lpBottomMargin = lpBottomMargin;
        return this;
    }

    public ZDialogTextView setLpLeftMargin(int lpLeftMargin) {
        this.lpLeftMargin = lpLeftMargin;
        return this;
    }

    public ZDialogTextView setLpRightMargin(int lpRightMargin) {
        this.lpRightMargin = lpRightMargin;
        return this;
    }

    public ZDialogTextView setLpMargin(int lpLeftMargin, int lpTopMargin, int lpRightMargin, int lpBottomMargin) {
        this.lpTopMargin = lpTopMargin;
        this.lpBottomMargin = lpBottomMargin;
        this.lpLeftMargin = lpLeftMargin;
        this.lpRightMargin = lpRightMargin;
        return this;
    }

    public int getLpLeftMargin() {
        return lpLeftMargin;
    }

    public int getLpTopMargin() {
        return lpTopMargin;
    }

    public int getLpRightMargin() {
        return lpRightMargin;
    }

    public int getLpBottomMargin() {
        return lpBottomMargin;
    }
}
