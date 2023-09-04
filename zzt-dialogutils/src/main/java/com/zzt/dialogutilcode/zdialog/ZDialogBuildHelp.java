package com.zzt.dialogutilcode.zdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.zzt.dialogutilcode.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeting
 * @date: 2023/2/10
 * 已经简化的默认弹框设置工具，不需要复杂的自定义
 */
public class ZDialogBuildHelp {
    // 弹框跟视图
    private ZDialogRootView zDialogRootView;
    // 所有内容的信息
    private List<ZDialogTextView> mTextViews = new ArrayList<>();
    // 左右间距
    private int lpLeftMargin;
    private int lpRightMargin;
    // 是否显示删除按钮
    private boolean hasRightDel = false;

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public ZDialogBuildHelp(ZDialogRootView rootView) {
        this.zDialogRootView = rootView;
        zDialogRootView.setActionContainerOrientation(ZDialogRootView.VERTICAL)
                .setActionSpace(dp2px(getBaseContext(), 8))
                .setNightMode(true);
        lpLeftMargin = dp2px(getBaseContext(), 16);
        lpRightMargin = dp2px(getBaseContext(), 16);
    }

    private Context getBaseContext() {
        return zDialogRootView.getContext();
    }

    public ZDialogRootView getDialogRootView() {
        return zDialogRootView;
    }

    public List<ZDialogTextView> getTextViews() {
        return mTextViews;
    }

    public boolean isHasRightDel() {
        return hasRightDel;
    }

    /**
     * 设置顶部图片
     */
    public ZDialogBuildHelp setTopImage(@DrawableRes int iconRes) {
        getDialogRootView().setTopImage(new ZDialogImageView(iconRes)
                .setLpHeight(dp2px(getBaseContext(), 80))
                .setLpWidth(dp2px(getBaseContext(), 80))
        );
        return this;
    }

    /**
     * 设置顶部图片
     */
    public ZDialogBuildHelp setTopImage(Drawable iconRes) {
        getDialogRootView().setTopImage(new ZDialogImageView(0)
                .setIconDrawable(iconRes)
                .setLpHeight(dp2px(getBaseContext(), 80))
                .setLpWidth(dp2px(getBaseContext(), 80))
        );
        return this;
    }

    /**
     * 添加对话框底部的操作按钮
     */
    public ZDialogBuildHelp addTextView(@Nullable ZDialogTextView dialogTV) {
        if (dialogTV != null) {
            mTextViews.add(dialogTV);
        }
        return this;
    }

    /**
     * 设置右侧删除图片
     */
    public ZDialogBuildHelp setRightDel(ZDialogImageView.DialogImageViewListener listener) {
        return setRightDel(true, 0, listener);
    }

    /**
     * 设置右侧删除图片
     */
    public ZDialogBuildHelp setRightDel(boolean show, @DrawableRes int iconRes, ZDialogImageView.DialogImageViewListener listener) {
        this.hasRightDel = show;
        if (show) {
            if (iconRes == 0) {
                getDialogRootView().setRightDelete(new ZDialogImageView(com.zzt.toolslib.R.drawable.ic_close)
                        .setLpHeight(dp2px(getBaseContext(), 24))
                        .setLpWidth(dp2px(getBaseContext(), 24))
                        .setLpMargin(0, dp2px(getBaseContext(), 12),
                                dp2px(getBaseContext(), 12), 0)
                        .onClick(listener)
                );
            } else {
                getDialogRootView().setRightDelete(new ZDialogImageView(iconRes)
                        .setLpHeight(dp2px(getBaseContext(), 24))
                        .setLpWidth(dp2px(getBaseContext(), 24))
                        .setLpMargin(0, dp2px(getBaseContext(), 12),
                                dp2px(getBaseContext(), 12), 0)
                        .onClick(listener)
                );
            }
        } else {
            getDialogRootView().setRightDelete(null);
        }
        return this;
    }

    /**
     * 设置只有单独一个蓝色按钮
     */
    public ZDialogBuildHelp addActionSingleBlue(@Nullable ZDialogAction action) {
        if (action != null) {
            action.setTextSize(16)
                    .setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white))
                    .setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.round_3d56ff_3dp))
                    .setBtnHeight(dp2px(getBaseContext(), 40))
                    .setLpMargin(lpLeftMargin, 0, lpRightMargin, dp2px(getBaseContext(), 32));
            getDialogRootView().addAction(action);
        }
        return this;
    }

    /**
     * 设置上面蓝色按钮
     */
    public ZDialogBuildHelp addActionTopBlue(@Nullable ZDialogAction action) {
        if (action != null) {
            action.setTextSize(16)
                    .setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white))
                    .setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.round_3d56ff_3dp))
                    .setBtnHeight(dp2px(getBaseContext(), 40))
                    .setLpMargin(lpLeftMargin, 0, lpRightMargin, 0);
            getDialogRootView().addAction(action);
        }
        return this;
    }

    /**
     * 设置下面灰色按钮
     */
    public ZDialogBuildHelp addActionBottomGray(@Nullable ZDialogAction action) {
        if (action != null) {
            action.setTextSize(16)
                    .setTextColor(ContextCompat.getColor(getBaseContext(), R.color.color_999999))
                    .setBackground(null)
                    .setBtnHeight(dp2px(getBaseContext(), 40))
                    .setLpMargin(lpLeftMargin, 0, lpRightMargin, dp2px(getBaseContext(), 8));
            getDialogRootView().addAction(action);
        }
        return this;
    }


    public ZDialogBuildHelp setBottomDel(ZDialogImageView.DialogImageViewListener listener) {
        return setBottomDel(true, 0, listener);
    }

    /**
     * 设置底部删除图片
     */
    public ZDialogBuildHelp setBottomDel(boolean show, @DrawableRes int iconRes, ZDialogImageView.DialogImageViewListener listener) {
        if (show) {
            if (iconRes == 0) {
                getDialogRootView().setBottomDelete(new ZDialogImageView(com.zzt.toolslib.R.drawable.ic_close)
                        .setLpHeight(dp2px(getBaseContext(), 24))
                        .setLpWidth(dp2px(getBaseContext(), 24))
                        .setLpMargin(0, dp2px(getBaseContext(), 40), 0, 0)
                        .onClick(listener)
                );
            } else {
                getDialogRootView().setBottomDelete(new ZDialogImageView(iconRes)
                        .setLpHeight(dp2px(getBaseContext(), 24))
                        .setLpWidth(dp2px(getBaseContext(), 24))
                        .setLpMargin(0, dp2px(getBaseContext(), 40), 0, 0)
                        .onClick(listener)
                );
            }
        } else {
            getDialogRootView().setBottomDelete(null);
        }
        return this;
    }

    /**
     * 设置对话框的消息文本
     */
    public ZDialogBuildHelp setMessage(CharSequence message) {
        if (!TextUtils.isEmpty(message)) {
            mTextViews.add(new ZDialogTextView(message)
                    .setTextColor(Color.parseColor("#252C58"))
                    .setTextSize(18)
                    .setLpMargin(lpLeftMargin, dp2px(getBaseContext(), 32), lpRightMargin, dp2px(getBaseContext(), 40))
                    .setGravity(Gravity.CENTER)
            );
        }
        return this;
    }

    /**
     * 设置对话框的消息文本，只有一个黑的的普通文本
     */
    public ZDialogBuildHelp setSingleMessage(CharSequence message) {
        if (!TextUtils.isEmpty(message)) {
            mTextViews.add(new ZDialogTextView(message)
                    .setTextColor(Color.parseColor("#252C58"))
                    .setTextSize(18)
                    .setLpMargin(lpLeftMargin, dp2px(getBaseContext(), 48), lpRightMargin, dp2px(getBaseContext(), 32))
                    .setGravity(Gravity.CENTER)
            );
        }
        return this;
    }

    /**
     * 设置对话框的消息文本
     */
    public ZDialogBuildHelp setTitleMessage(CharSequence title, CharSequence message) {
        if (!TextUtils.isEmpty(title)) {
            mTextViews.add(new ZDialogTextView(title)
                    .setTextColor(Color.parseColor("#252C58"))
                    .setTextSize(18)
                    .setLpMargin(lpLeftMargin, dp2px(getBaseContext(), 32), lpRightMargin, 0)
                    .setGravity(Gravity.CENTER)
                    .setTypefaceStyle(Typeface.BOLD)
            );
        }
        if (!TextUtils.isEmpty(message)) {
            mTextViews.add(new ZDialogTextView(message)
                    .setTextColor(Color.parseColor("#252C58"))
                    .setTextSize(16)
                    .setLpMargin(lpLeftMargin, dp2px(getBaseContext(), 16), lpRightMargin, dp2px(getBaseContext(), 24))
                    .setGravity(Gravity.CENTER)
            );
        }
        return this;
    }


}
