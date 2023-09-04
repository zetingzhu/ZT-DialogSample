package com.zzt.dialogutilcode.zdialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.zzt.dialogutilcode.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeting
 * @date: 2023/2/9
 * 带有一些固定样式的对话框跟布局 ，可以直接 ZDialogBuilder 简单创建对话框，可以放在xml中作为对话框外面布局
 */
public class ZDialogRootView extends FrameLayout {
    private static final String TAG = ZDialogRootView.class.getSimpleName();

    @IntDef({HORIZONTAL, VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    // 设置底部按钮横竖样式
    @Orientation
    private int mActionContainerOrientation = HORIZONTAL;

    private Context mContext;
    // 需要展示Dialog 对象
    private BaseShowDismissAppCompatDialog mDialog;
    // 顶部图片
    private ZDialogImageView topImage;
    // 删除按钮
    private ZDialogImageView rightDelete;
    // 底部删除按钮
    private ZDialogImageView bottomDelete;
    // 底部按钮间距
    private int actionSpace = 50;
    // 底部操作按钮
    protected List<ZDialogAction> mActions = new ArrayList<>();
    // 是否深色模式
    private boolean nightMode = false;
    // 保留子布局
    private View childView;
    // 设备弹框内容背景
    private Drawable parentContextDrawable;

    public ZDialogRootView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ZDialogRootView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ZDialogRootView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
    }

    @Override
    public void addView(View child) {
        if (child != null) {
            this.childView = child;
        }
        ConstraintLayout rootView = createRootView(mContext, child);
        super.addView(rootView);
        Log.d(TAG, ">> addView 1");
    }

    @Override
    public void addView(View child, int index) {
        if (child != null) {
            this.childView = child;
        }
        ConstraintLayout rootView = createRootView(mContext, child);
        super.addView(rootView);
        Log.d(TAG, ">> addView 2");
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (child != null) {
            this.childView = child;
        }
        ConstraintLayout rootView = createRootView(mContext, child);
        super.addView(rootView, params);
        Log.d(TAG, ">> addView 3");
    }

    @Override
    public void addView(View child, int width, int height) {
        if (child != null) {
            this.childView = child;
        }
        ConstraintLayout rootView = createRootView(mContext, child);
        super.addView(rootView);
        Log.d(TAG, ">> addView 4");
    }

    public void setShowDialog(BaseShowDismissAppCompatDialog mDialog) {
        this.mDialog = mDialog;
    }

    /**
     * 刷新布局重新创建
     *
     * @return
     */
    public ZDialogRootView postCreateView() {
        ViewGroup parent = (ViewGroup) childView.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        removeAllViews();
        ConstraintLayout rootView = createRootView(mContext, childView);
        super.addView(rootView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return this;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, ">> onSizeChanged 1");
    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        Log.d(TAG, ">> removeAllViews 1");
    }

    @Override
    protected void removeDetachedView(View child, boolean animate) {
        super.removeDetachedView(child, animate);
        Log.d(TAG, ">> removeDetachedView 1");
    }

    @Override
    protected void attachViewToParent(View child, int index, ViewGroup.LayoutParams params) {
        super.attachViewToParent(child, index, params);
        Log.d(TAG, ">> attachViewToParent 1");
    }

    @Override
    protected void detachViewFromParent(View child) {
        super.detachViewFromParent(child);
        Log.d(TAG, ">> detachViewFromParent 1");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, ">> onLayout 1");
    }

    /**
     * 是否深色模式
     */
    @SuppressWarnings("unchecked")
    public ZDialogRootView setNightMode(boolean night) {
        this.nightMode = night;
        return (ZDialogRootView) this;
    }

    public boolean isNightMode() {
        return nightMode;
    }


    /**
     * 设置弹框内容背景
     *
     * @param parentDrawable
     * @return
     */
    public ZDialogRootView setParentDrawable(Drawable parentDrawable) {
        this.parentContextDrawable = parentDrawable;
        return this;
    }


    /**
     * 添加顶部大图片
     */
    @SuppressWarnings("unchecked")
    public ZDialogRootView setTopImage(ZDialogImageView topImage) {
        this.topImage = topImage;
        return (ZDialogRootView) this;
    }

    /**
     * 右上角删除按钮
     */
    @SuppressWarnings("unchecked")
    public ZDialogRootView setRightDelete(ZDialogImageView rightDelete) {
        this.rightDelete = rightDelete;
        return (ZDialogRootView) this;
    }

    /**
     * 添加底部删除图片
     */
    @SuppressWarnings("unchecked")
    public ZDialogRootView setBottomDelete(ZDialogImageView bottomDelete) {
        this.bottomDelete = bottomDelete;
        return (ZDialogRootView) this;
    }


    /**
     * 添加底部按钮排列方式
     */
    @SuppressWarnings("unchecked")
    public ZDialogRootView setActionContainerOrientation(int actionContainerOrientation) {
        mActionContainerOrientation = actionContainerOrientation;
        return (ZDialogRootView) this;
    }

    /**
     * 添加对话框底部的操作按钮
     */
    @SuppressWarnings("unchecked")
    public ZDialogRootView addAction(@Nullable ZDialogAction action) {
        if (action != null) {
            mActions.add(action);
        }
        return (ZDialogRootView) this;
    }

    /**
     * 设置底部操作按钮间距
     */
    @SuppressWarnings("unchecked")
    public ZDialogRootView setActionSpace(int actionSpace) {
        this.actionSpace = actionSpace;
        return (ZDialogRootView) this;
    }

    /**
     * 添加操作按钮
     *
     * @param str      文案
     * @param listener 点击回调事件
     */
    @SuppressWarnings("unchecked")
    public ZDialogRootView addAction(CharSequence str, ZDialogAction.ActionListener listener) {
        ZDialogAction action = new ZDialogAction(str)
                .onClick(listener);
        mActions.add(action);
        return (ZDialogRootView) this;
    }

    /**
     * @param dialogContext
     * @param contentLayout 添加自定义内容信息
     */
    private ConstraintLayout createRootView(Context dialogContext, View contentLayout) {
        // 最外面不带背景视图，用来放顶部头像和外部删除按钮
        ConstraintLayout mDialogRootView = onCreateDialogRootView(dialogContext);
        // 带背景dialog 内容视图
        ConstraintLayout mDialogParentView = onCreateDialogContentView(dialogContext);
        // 顶部图片
        AppCompatImageView topImg = onCreateDialogTopView(mDialog, topImage);
        // 右上角删除
        View rightDeleteView = onCreateDeleteBtn(mDialog, rightDelete);
        // 底部删除删除
        View bottomDeleteView = onCreateDeleteBtn(mDialog, bottomDelete);
        // 下方操作按钮
        View operatorLayout = onCreateOperatorLayout(mDialog, mDialogParentView, dialogContext);
        // 添加自定义内容信息

        checkAndSetId(mDialogRootView, R.id.z_dialog_root_layout);
        checkAndSetId(mDialogParentView, R.id.z_dialog_parent_layout);
        checkAndSetId(rightDeleteView, R.id.z_dialog_right_delete);
        checkAndSetId(bottomDeleteView, R.id.z_dialog_bottom_delete);
        checkAndSetId(contentLayout, R.id.z_dialog_content_layout);
        checkAndSetId(operatorLayout, R.id.z_dialog_operator_layout);
        checkAndSetId(topImg, R.id.z_dialog_top_image);

        // 右上角删除
        if (rightDeleteView != null) {
            ConstraintLayout.LayoutParams lp = onCreateRightDeleteLayoutParams(dialogContext, rightDelete);
            if (contentLayout != null) {
                lp.topToBottom = contentLayout.getId();
            }
            mDialogParentView.addView(rightDeleteView, lp);
        }

        // 中间填充内容
        if (contentLayout != null) {
            ConstraintLayout.LayoutParams lp = onCreateContentLayoutParams(dialogContext);
            if (topImg != null && topImage != null) {
                lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                lp.topMargin = topImage.getLpHeight() / 2;
            } else if (rightDeleteView != null) {
                lp.topToBottom = rightDeleteView.getId();
            } else {
                lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            }
            if (operatorLayout != null) {
                lp.bottomToTop = operatorLayout.getId();
            } else {
                lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            }
            mDialogParentView.addView(contentLayout, lp);
        }

        // 下方操作按钮
        if (operatorLayout != null) {
            ConstraintLayout.LayoutParams lp = onCreateOperatorLayoutLayoutParams(dialogContext);
            if (contentLayout != null) {
                lp.topToBottom = contentLayout.getId();
            } else {
                lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            }
            mDialogParentView.addView(operatorLayout, lp);
        }


        ConstraintLayout.LayoutParams contentLp = onCreateParentLayoutParams(dialogContext);
        if (contentLp != null) {
            if (topImg != null && topImage != null) {
                contentLp.topMargin = topImage.getLpHeight() / 2;
            }
            if (bottomDeleteView != null && bottomDelete != null) {
                contentLp.bottomToTop = bottomDeleteView.getId();
            }
            mDialogRootView.addView(mDialogParentView, contentLp);
            // 顶部按钮
            if (topImg != null && topImage != null) {
                ConstraintLayout.LayoutParams lp = onCreateTopImgLayoutParams(dialogContext, topImage);
                mDialogRootView.addView(topImg, lp);
            }

            // 底部删除按钮
            if (bottomDeleteView != null && bottomDelete != null) {
                ConstraintLayout.LayoutParams lp = onCreateBottomRightLayoutParams(dialogContext, bottomDelete);
                lp.topToBottom = mDialogParentView.getId();
                mDialogRootView.addView(bottomDeleteView, lp);
            }
        }

        return mDialogRootView;
    }

    private void checkAndSetId(@Nullable View view, int id) {
        if (view != null && view.getId() == View.NO_ID) {
            view.setId(id);
        }
    }


    @NonNull
    protected ConstraintLayout onCreateDialogRootView(@NonNull Context context) {
        ConstraintLayout dialogView = new ConstraintLayout(context);
        return dialogView;
    }

    @NonNull
    protected ConstraintLayout onCreateDialogContentView(@NonNull Context context) {
        ConstraintLayout dialogView = new ConstraintLayout(context);
        if (parentContextDrawable != null) {
            dialogView.setBackground(parentContextDrawable);
        } else {
            dialogView.setBackgroundResource(R.drawable.white_round_6dp);
        }
        return dialogView;
    }

    @NonNull
    protected AppCompatImageView onCreateDialogTopView(@NonNull BaseShowDismissAppCompatDialog dialog, ZDialogImageView imgView) {
        if (imgView != null) {
            return imgView.buildView(mContext, dialog);
        }
        return null;
    }


    @Nullable
    protected View onCreateDeleteBtn(@NonNull BaseShowDismissAppCompatDialog dialog, ZDialogImageView imgView) {
        if (imgView != null) {
            return imgView.buildView(mContext, dialog);
        }
        return null;
    }

    @NonNull
    protected ConstraintLayout.LayoutParams onCreateTopImgLayoutParams(@NonNull Context context, ZDialogImageView imgView) {
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (imgView != null) {
            lp = imgView.buildLayoutParams();
        }
        lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        return lp;
    }

    @NonNull
    protected ConstraintLayout.LayoutParams onCreateBottomRightLayoutParams(@NonNull Context context, ZDialogImageView imgView) {
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (imgView != null) {
            lp = imgView.buildLayoutParams();
        }
        lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.verticalChainStyle = ConstraintLayout.LayoutParams.CHAIN_PACKED;
        return lp;
    }

    @NonNull
    protected ConstraintLayout.LayoutParams onCreateRightDeleteLayoutParams(@NonNull Context context, ZDialogImageView imgView) {
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (imgView != null) {
            lp = imgView.buildLayoutParams();
        }
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.verticalChainStyle = ConstraintLayout.LayoutParams.CHAIN_PACKED;
        return lp;
    }

    protected ConstraintLayout.LayoutParams onCreateContentLayoutParams(@NonNull Context context) {
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.constrainedHeight = true;
        lp.verticalChainStyle = ConstraintLayout.LayoutParams.CHAIN_PACKED;
        return lp;
    }

    protected ConstraintLayout.LayoutParams onCreateParentLayoutParams(@NonNull Context context) {
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.constrainedHeight = true;
        return lp;
    }

    @NonNull
    protected ConstraintLayout.LayoutParams onCreateOperatorLayoutLayoutParams(@NonNull Context context) {
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.verticalChainStyle = ConstraintLayout.LayoutParams.CHAIN_PACKED;
        return lp;
    }

    /**
     * 创建底部操作按钮
     *
     * @param dialog
     * @param parent
     * @param context
     * @return
     */
    @Nullable
    protected View onCreateOperatorLayout(@NonNull final BaseShowDismissAppCompatDialog dialog, @NonNull ConstraintLayout parent, @NonNull Context context) {
        int size = mActions.size();
        if (size > 0) {
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(mActionContainerOrientation == VERTICAL ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    // 不是第一个时候添加间距
                    layout.addView(createActionContainerSpace(context));
                }
                ZDialogAction action = mActions.get(i);
                LinearLayout.LayoutParams actionLp = action.buildLayoutParams();
                AppCompatButton actionView = action.buildActionView(mContext, mDialog, i);
                layout.addView(actionView, actionLp);
            }
            return layout;
        }
        return null;
    }

    private View createActionContainerSpace(Context context) {
        Space space = new Space(context);
        LinearLayout.LayoutParams spaceLp = new LinearLayout.LayoutParams(actionSpace, actionSpace);
        spaceLp.weight = 0;
        space.setLayoutParams(spaceLp);
        return space;
    }


}
