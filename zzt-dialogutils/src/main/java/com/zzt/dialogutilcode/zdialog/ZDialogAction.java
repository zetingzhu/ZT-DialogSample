package com.zzt.dialogutilcode.zdialog;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.zzt.dialogutilcode.R;

/**
 * @author: zeting
 * @date: 2022/11/4
 * 弹框下方的操作按钮
 */
public class ZDialogAction {

    private CharSequence mStr;
    private ActionListener mOnClickListener;
    private AppCompatButton mButton;
    private boolean mIsEnabled = true;
    private ColorStateList mTextColor;
    private int mTextSize;
    private Drawable background;
    // 按钮高度
    private int btnHeight = 0;
    private int lpLeftMargin = 0,
            lpTopMargin = 0,
            lpRightMargin = 0,
            lpBottomMargin = 0;
    // 设置字体
    private int typefaceStyle = Typeface.NORMAL;

    public ZDialogAction(Context context, int strRes) {
        this(context.getResources().getString(strRes));
    }

    public ZDialogAction(CharSequence str) {
        this(str, null);
    }

    public ZDialogAction(Context context, int strRes, @Nullable ActionListener onClickListener) {
        this(context.getResources().getString(strRes), onClickListener);
    }

    public ZDialogAction(CharSequence str, @Nullable ActionListener onClickListener) {
        mStr = str;
        mOnClickListener = onClickListener;
    }

    public AppCompatButton buildActionView(Context mContext,final BaseShowDismissAppCompatDialog dialog, final int index) {
        mButton = generateActionButton(mContext, mStr);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null && mButton.isEnabled()) {
                    mOnClickListener.onClick(dialog, index);
                }
            }
        });
        if (mTextColor != null) {
            mButton.setTextColor(mTextColor);
        }
        if (mTextSize > 0) {
            mButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
        }
        if (background != null) {
            mButton.setBackgroundDrawable(background);
        }

        mButton.setTypeface(null, typefaceStyle);
        return mButton;
    }

    private AppCompatButton generateActionButton(Context context, CharSequence text) {
        AppCompatButton button = new AppCompatButton(context);
        button.setStateListAnimator(null);// 去掉点击阴影效果
        button.setBackground(null);
        button.setMinHeight(0);
        button.setMinimumHeight(0);
        button.setText(text);
        button.setClickable(true);
        button.setEnabled(mIsEnabled);
        return button;
    }

    public LinearLayout.LayoutParams buildLayoutParams() {
        LinearLayout.LayoutParams mTVLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getBtnHeight() > 0 ? getBtnHeight() : ViewGroup.LayoutParams.WRAP_CONTENT);
        mTVLp.leftMargin = lpLeftMargin;
        mTVLp.topMargin = lpTopMargin;
        mTVLp.rightMargin = lpRightMargin;
        mTVLp.bottomMargin = lpBottomMargin;
        return mTVLp;
    }

    public ZDialogAction onClick(ActionListener onClickListener) {
        mOnClickListener = onClickListener;
        return this;
    }

    public ZDialogAction setTypefaceStyle(int typefaceStyle) {
        this.typefaceStyle = typefaceStyle;
        return this;
    }

    public ZDialogAction setEnabled(boolean enabled) {
        mIsEnabled = enabled;
        if (mButton != null) {
            mButton.setEnabled(enabled);
        }
        return this;
    }

    public ZDialogAction setBackground(Drawable background) {
        this.background = background;
        return this;
    }

    public ZDialogAction setTextColor(@ColorInt int color) {
        mTextColor = ColorStateList.valueOf(color);
        return this;
    }

    public ZDialogAction setTextSize(int size) {
        mTextSize = size;
        return this;
    }

    public int getBtnHeight() {
        return btnHeight;
    }

    public ZDialogAction setBtnHeight(int btnHeight) {
        this.btnHeight = btnHeight;
        return this;
    }

    public ZDialogAction setLpTopMargin(int lpTopMargin) {
        this.lpTopMargin = lpTopMargin;
        return this;
    }

    public ZDialogAction setLpBottomMargin(int lpBottomMargin) {
        this.lpBottomMargin = lpBottomMargin;
        return this;
    }

    public ZDialogAction setLpLeftMargin(int lpLeftMargin) {
        this.lpLeftMargin = lpLeftMargin;
        return this;
    }

    public ZDialogAction setLpRightMargin(int lpRightMargin) {
        this.lpRightMargin = lpRightMargin;
        return this;
    }

    public ZDialogAction setLpMargin(int lpLeftMargin, int lpTopMargin, int lpRightMargin, int lpBottomMargin) {
        this.lpTopMargin = lpTopMargin;
        this.lpBottomMargin = lpBottomMargin;
        this.lpLeftMargin = lpLeftMargin;
        this.lpRightMargin = lpRightMargin;
        return this;
    }

    public interface ActionListener {
        void onClick(BaseShowDismissAppCompatDialog dialog, int index);
    }
}
