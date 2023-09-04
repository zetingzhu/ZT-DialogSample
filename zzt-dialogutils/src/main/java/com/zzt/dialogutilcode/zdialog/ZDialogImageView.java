package com.zzt.dialogutilcode.zdialog;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author: zeting
 * @date: 2022/11/4
 * 删除按钮操作
 */
public class ZDialogImageView {
    private int mIconRes = 0; // 图片
    private Drawable mIconDrawable;
    private DialogImageViewListener mOnClickListener;
    private AppCompatImageView mImageView;
    private boolean mIsEnabled = true;
    private int lpWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int lpHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

    private int lpLeftMargin = 0,
            lpTopMargin = 0,
            lpRightMargin = 0,
            lpBottomMargin = 0;

    public ZDialogImageView(int iconRes) {
        this(iconRes, null);
    }

    public ZDialogImageView(int iconRes, @Nullable DialogImageViewListener onClickListener) {
        mIconRes = iconRes;
        mOnClickListener = onClickListener;
    }


    public ZDialogImageView onClick(DialogImageViewListener onClickListener) {
        mOnClickListener = onClickListener;
        return this;
    }

    public ZDialogImageView setIconDrawable(Drawable mIconDrawable) {
        this.mIconDrawable = mIconDrawable;
        return this;
    }

    public ZDialogImageView setEnabled(boolean enabled) {
        mIsEnabled = enabled;
        if (mImageView != null) {
            mImageView.setEnabled(enabled);
        }
        return this;
    }

    public AppCompatImageView buildView( Context mContext , final BaseShowDismissAppCompatDialog dialog) {
        mImageView = getDeleteButton(mContext, mIconRes);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null && mImageView.isEnabled()) {
                    mOnClickListener.onClick(dialog);
                }
            }
        });
        return mImageView;
    }

    public ConstraintLayout.LayoutParams buildLayoutParams() {
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(lpWidth, lpHeight);
        layoutParams.leftMargin = lpLeftMargin;
        layoutParams.topMargin = lpTopMargin;
        layoutParams.rightMargin = lpRightMargin;
        layoutParams.bottomMargin = lpBottomMargin;
        return layoutParams;
    }

    /**
     * 生成适用于删除的按钮
     */
    private AppCompatImageView getDeleteButton(Context context, int iconRes) {
        AppCompatImageView button = new AppCompatImageView(context);
        if (mIconDrawable != null) {
            button.setImageDrawable(mIconDrawable);
        } else {
            button.setImageResource(iconRes);
        }
        button.setClickable(true);
        button.setEnabled(mIsEnabled);
        return button;
    }

    public interface DialogImageViewListener {
        void onClick(BaseShowDismissAppCompatDialog dialog);
    }

    public int getLpWidth() {
        return lpWidth;
    }

    public ZDialogImageView setLpWidth(int lpWidth) {
        this.lpWidth = lpWidth;
        return this;
    }

    public int getLpHeight() {
        return lpHeight;
    }

    public ZDialogImageView setLpHeight(int lpHeight) {
        this.lpHeight = lpHeight;
        return this;
    }

    public ZDialogImageView setLpTopMargin(int lpTopMargin) {
        this.lpTopMargin = lpTopMargin;
        return this;
    }

    public ZDialogImageView setLpBottomMargin(int lpBottomMargin) {
        this.lpBottomMargin = lpBottomMargin;
        return this;
    }

    public ZDialogImageView setLpLeftMargin(int lpLeftMargin) {
        this.lpLeftMargin = lpLeftMargin;
        return this;
    }

    public ZDialogImageView setLpRightMargin(int lpRightMargin) {
        this.lpRightMargin = lpRightMargin;
        return this;
    }

    public ZDialogImageView setLpMargin(int lpLeftMargin, int lpTopMargin, int lpRightMargin, int lpBottomMargin) {
        this.lpTopMargin = lpTopMargin;
        this.lpBottomMargin = lpBottomMargin;
        this.lpLeftMargin = lpLeftMargin;
        this.lpRightMargin = lpRightMargin;
        return this;
    }
}
