package com.zzt.dialogutilcode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.text.Editable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;


/**
 * @author: zeting
 * @date: 2022/2/16
 * 设置左边固定展示文本
 */
public class LeftFixedEditText extends AppCompatEditText {
    private static final String TAG = LeftFixedEditText.class.getSimpleName();
    // 固定字符
    private String fixedText = "$";
    // 距离左边位置
    private int leftPadding;
    // 字符距离文字间距
    private int fixedTextPadding = 20;
    // 固定字符宽度
    private float fixedWidth = 0f;

    public LeftFixedEditText(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public LeftFixedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LeftFixedEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        leftPadding = getPaddingLeft();
        fixedWidth = getPaint().measureText(fixedText);
    }

    public void setFixedTextPadding(int fixedTextPadding) {
        this.fixedTextPadding = fixedTextPadding;
    }

    public void setFixedText(String text) {
        fixedText = text;
        leftPadding = getPaddingLeft();
        fixedWidth = getPaint().measureText(fixedText);
        int newLeftPadding = (int) (fixedWidth + leftPadding + fixedTextPadding);
        setPadding(newLeftPadding, getPaddingTop(), getPaddingBottom(), getPaddingRight());
        invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//
//        Log.d(TAG, "getHeight:" + getHeight());
//        Log.d(TAG, "getWidth:" + getWidth());
//        Log.d(TAG, "getBaseline:" + getBaseline());
//        Log.d(TAG, "leftPadding:" + leftPadding + "E:" + getPaddingEnd() + " R:" + getPaddingRight() + "T:" + getPaddingTop() + "B:" + getPaddingBottom());
//        Log.d(TAG, "getScrollX:" + getScrollX());
//        Log.d(TAG, "w:" + canvas.getWidth() + "  H:" + canvas.getHeight());
        //为了适应gravity而设置translate
        //因为当设置gravity后画布会平移
        canvas.translate(getScrollX(), 0);
        if (!TextUtils.isEmpty(fixedText)) {
            int mGravity = getGravity();
            CharSequence text = getText();
            if (text.length() == 0) {
                text = getHint();
            }
            float textW = 0f;
            if (!TextUtils.isEmpty(text)) {
                textW = getPaint().measureText(String.valueOf(text));
            }
            switch (mGravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
                case Gravity.END:
                case Gravity.RIGHT:
                    float drawEndLeft = Math.max(leftPadding, getWidth() - textW - getPaddingEnd() - fixedWidth - fixedTextPadding);
                    canvas.drawText(fixedText, drawEndLeft, getBaseline(), getPaint());
                    break;
                case Gravity.CENTER_HORIZONTAL:
                case Gravity.CENTER:
                    float drawCenterLeft = Math.max(leftPadding, getWidth() / 2 - textW / 2 - getPaddingEnd() - fixedWidth / 2 - fixedTextPadding);
                    canvas.drawText(fixedText, drawCenterLeft, getBaseline(), getPaint());
                    break;
                default:
                    canvas.drawText(fixedText, leftPadding, getBaseline(), getPaint());
                    break;
            }
        }
    }

}