/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zzt.dialogutilcode.widget.dialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.zzt.dialogutilcode.R;
import com.zzt.dialogutilcode.layout.QMUIConstraintLayout;
import com.zzt.dialogutilcode.layout.QMUIFrameLayout;
import com.zzt.dialogutilcode.util.QMUIResHelper;
import com.zzt.dialogutilcode.widget.textview.QMUISpanTouchFixTextView;

public class QMUIBottomSheetListItemView extends QMUIConstraintLayout {

    private AppCompatImageView mIconView;
    private QMUISpanTouchFixTextView mTextView;
    private QMUIFrameLayout mRedPointView;
    private AppCompatImageView mMarkView = null;
    private int mItemHeight;

    public QMUIBottomSheetListItemView(Context context, boolean markStyle, boolean gravityCenter) {
        super(context);
        setBackground(QMUIResHelper.getAttrDrawable(
                context, R.attr.qmui_skin_support_bottom_sheet_list_item_bg));
        int paddingHor = QMUIResHelper.getAttrDimen(context, R.attr.qmui_bottom_sheet_padding_hor);
        setPadding(paddingHor, 0, paddingHor, 0);

        mIconView = new AppCompatImageView(context);
        mIconView.setId(View.generateViewId());
        mIconView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        mTextView = new QMUISpanTouchFixTextView(context);
        mTextView.setId(View.generateViewId());

//        QMUIResHelper.assignTextViewWithAttr(mTextView, R.attr.qmui_bottom_sheet_list_item_text_style);

        mRedPointView = new QMUIFrameLayout(context);
        mRedPointView.setId(View.generateViewId());
        mRedPointView.setBackgroundColor(QMUIResHelper.getAttrColor(
                context, R.attr.qmui_skin_support_bottom_sheet_list_red_point_color));


        if (markStyle) {
            mMarkView = new AppCompatImageView(context);
            mMarkView.setId(View.generateViewId());
            mMarkView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            mMarkView.setImageDrawable(QMUIResHelper.getAttrDrawable(
                    context, R.attr.qmui_skin_support_bottom_sheet_list_mark));

        }

        int iconSize = QMUIResHelper.getAttrDimen(
                context, R.attr.qmui_bottom_sheet_list_item_icon_size);
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(iconSize, iconSize);
        lp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.rightToLeft = mTextView.getId();
        lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.horizontalChainStyle = ConstraintLayout.LayoutParams.CHAIN_PACKED;
        lp.horizontalBias = gravityCenter ? 0.5f : 0f;
        addView(mIconView, lp);

        lp = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftToRight = mIconView.getId();
        lp.rightToLeft = mRedPointView.getId();
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.horizontalChainStyle = ConstraintLayout.LayoutParams.CHAIN_PACKED;
        lp.horizontalBias = gravityCenter ? 0.5f : 0f;
        lp.leftMargin = QMUIResHelper.getAttrDimen(
                context, R.attr.qmui_bottom_sheet_list_item_icon_margin_right);
        lp.goneLeftMargin = 0;
        addView(mTextView, lp);

        int redPointSize = QMUIResHelper.getAttrDimen(
                context, R.attr.qmui_bottom_sheet_list_item_red_point_size);
        lp = new ConstraintLayout.LayoutParams(redPointSize, redPointSize);
        lp.leftToRight = mTextView.getId();
        if (markStyle) {
            lp.rightToLeft = mMarkView.getId();
            lp.rightMargin = QMUIResHelper.getAttrDimen(
                    context, R.attr.qmui_bottom_sheet_list_item_mark_margin_left);
        } else {
            lp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        }
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.horizontalChainStyle = ConstraintLayout.LayoutParams.CHAIN_PACKED;
        lp.horizontalBias = gravityCenter ? 0.5f : 0f;
        lp.leftMargin = QMUIResHelper.getAttrDimen(
                context, R.attr.qmui_bottom_sheet_list_item_tip_point_margin_left);
        addView(mRedPointView, lp);

        if (markStyle) {
            lp = new ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
            lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            addView(mMarkView, lp);
        }

        mItemHeight = QMUIResHelper.getAttrDimen(context, R.attr.qmui_bottom_sheet_list_item_height);
    }

    public void render(@NonNull QMUIBottomSheetListItemModel itemModel, boolean isChecked) {
        if (itemModel.imageSkinSrcAttr != 0) {

            mIconView.setVisibility(View.VISIBLE);
        } else {
            Drawable drawable = itemModel.image;
            if (drawable == null && itemModel.imageRes != 0) {
                drawable = ContextCompat.getDrawable(getContext(), itemModel.imageRes);
            }
            if (drawable != null) {
                drawable.mutate();
                mIconView.setImageDrawable(drawable);

            } else {
                mIconView.setVisibility(View.GONE);
            }
        }

        mTextView.setText(itemModel.text);
        if (itemModel.typeface != null) {
            mTextView.setTypeface(itemModel.typeface);
        }

        mRedPointView.setVisibility(itemModel.hasRedPoint ? View.VISIBLE : View.GONE);

        if (mMarkView != null) {
            mMarkView.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(mItemHeight, View.MeasureSpec.EXACTLY));
    }
}
