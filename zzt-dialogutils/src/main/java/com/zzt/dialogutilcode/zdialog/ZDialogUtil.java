package com.zzt.dialogutilcode.zdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.zzt.dialogutilcode.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeting
 * @date: 2022/11/8
 */
public class ZDialogUtil {
    public static class MessageDialogBuilder extends ZDialogBuilder<ZDialogUtil.MessageDialogBuilder> {
        // 弹框视图简化工具
        private ZDialogBuildHelp buildHelp;

        public MessageDialogBuilder(Context context) {
            super(context, R.style.Style_Base_Dialog);
            initView();
        }

        public MessageDialogBuilder(Context context, int style) {
            super(context, style);
            initView();
        }

        private void initView() {
            buildHelp = new ZDialogBuildHelp(getDialogRootView());
        }

        /**
         * 获取已经绑定的 视图工具
         *
         * @param listener
         * @return
         */
        public ZDialogUtil.MessageDialogBuilder getBuildHelp(DialogBuildHelpListener listener) {
            if (listener != null) {
                listener.getBuildHelp(buildHelp);
            }
            return this;
        }

        /**
         * 设置顶部图片
         */
        public MessageDialogBuilder setTopImage(@DrawableRes int iconRes) {
            if (buildHelp != null) {
                buildHelp.setTopImage(iconRes);
            }
            return this;
        }

        /**
         * 设置顶部图片
         */
        public MessageDialogBuilder setTopImage(Drawable iconRes) {
            if (buildHelp != null) {
                buildHelp.setTopImage(iconRes);
            }
            return this;
        }

        /**
         * 添加对话框底部的操作按钮
         */
        public MessageDialogBuilder addTextView(@Nullable ZDialogTextView dialogTV) {
            if (buildHelp != null) {
                buildHelp.addTextView(dialogTV);
            }
            return this;
        }


        /**
         * 设置右侧删除图片
         */
        public MessageDialogBuilder setRightDel(ZDialogImageView.DialogImageViewListener listener) {
            return setRightDel(true, 0, listener);
        }

        /**
         * 设置右侧删除图片
         */
        public MessageDialogBuilder setRightDel(boolean show, @DrawableRes int iconRes, ZDialogImageView.DialogImageViewListener listener) {
            if (buildHelp != null) {
                buildHelp.setRightDel(show, iconRes, listener);
            }
            return this;
        }


        public MessageDialogBuilder setBottomDel(ZDialogImageView.DialogImageViewListener listener) {
            return setBottomDel(true, 0, listener);
        }

        /**
         * 设置底部删除图片
         */
        public MessageDialogBuilder setBottomDel(boolean show, @DrawableRes int iconRes, ZDialogImageView.DialogImageViewListener listener) {
            if (buildHelp != null) {
                buildHelp.setBottomDel(show, iconRes, listener);
            }
            return this;
        }

        /**
         * 设置只有单独一个蓝色按钮
         */
        public MessageDialogBuilder addActionSingleBlue(@Nullable ZDialogAction action) {
            if (buildHelp != null) {
                buildHelp.addActionSingleBlue(action);
            }
            return this;
        }

        /**
         * 设置上面蓝色按钮
         */
        public MessageDialogBuilder addActionTopBlue(@Nullable ZDialogAction action) {
            if (buildHelp != null) {
                buildHelp.addActionTopBlue(action);
            }
            return this;
        }

        /**
         * 设置下面灰色按钮
         */
        public MessageDialogBuilder addActionBottomGray(@Nullable ZDialogAction action) {
            if (buildHelp != null) {
                buildHelp.addActionBottomGray(action);
            }
            return this;
        }

        /**
         * 设置对话框的消息文本
         */
        public MessageDialogBuilder setMessage(CharSequence message) {
            if (buildHelp != null) {
                buildHelp.setMessage(message);
            }
            return this;
        }

        /**
         * 设置对话框的消息文本，只有一个黑的的普通文本
         */
        public MessageDialogBuilder setSingleMessage(CharSequence message) {
            if (buildHelp != null) {
                buildHelp.setSingleMessage(message);
            }
            return this;
        }

        /**
         * 设置对话框的消息文本，一个粗的标题，一个细的内容文本
         */
        public MessageDialogBuilder setTitleMessage(CharSequence title, CharSequence message) {
            if (buildHelp != null) {
                buildHelp.setTitleMessage(title, message);
            }
            return this;
        }


        @Nullable
        @Override
        protected View onCreateContent(@NonNull BaseShowDismissAppCompatDialog dialog, @NonNull Context context) {
            if (buildHelp != null && !buildHelp.getTextViews().isEmpty()) {
                int size = buildHelp.getTextViews().size();
                if (size > 0) {
                    LinearLayout layout = new LinearLayout(context);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    for (int i = 0; i < size; i++) {
                        ZDialogTextView mdtv = buildHelp.getTextViews().get(i);
                        LinearLayout.LayoutParams mTVLp = mdtv.buildLayoutParams();
                        if (i == 0 && buildHelp.isHasRightDel()) {
                            mTVLp.topMargin = ZDialogBuildHelp.dp2px(getBaseContext(), 12);
                        }
                        AppCompatTextView textView = mdtv.buildTextView(mDialog, i);
                        layout.addView(textView, mTVLp);
                    }
                    return layout;
                }
            }
            return null;
        }
    }

    public interface DialogBuildHelpListener {
        void getBuildHelp(ZDialogBuildHelp help);
    }
}
