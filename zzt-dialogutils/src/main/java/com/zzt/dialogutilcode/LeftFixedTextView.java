package com.zzt.dialogutilcode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;


/**
 * @author: zeting
 * @date: 2022/2/16
 * 设置左边固定展示文本
 */
public class LeftFixedTextView extends androidx.appcompat.widget.AppCompatTextView {
    private static final String TAG = LeftFixedTextView.class.getSimpleName();
    private String fixedText = "CCCCCC";
    private int leftPadding;
    private float fixedWidth = 0f;

    public LeftFixedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LeftFixedTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    public LeftFixedTextView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        fixedWidth = getPaint().measureText(fixedText);
        int newLeftPadding = (int) (fixedWidth + leftPadding);
        setPadding(newLeftPadding, getPaddingTop(), getPaddingBottom(), getPaddingRight());
        invalidate();
    }

    public void setFixedText(String text) {
        fixedText = text;
        leftPadding = getPaddingLeft();
//        fixedPaint = getPaint();
//        fixedPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
//        fixedPaint.setColor(Color.BLACK);
//        fixedPaint.setTextSize(paint.getTextSize());
//        fixedPaint.setAntiAlias(paint.isAntiAlias());
//        fixedPaint.setTextAlign(paint.getTextAlign());

        fixedWidth = getPaint().measureText(fixedText);
        int newLeftPadding = (int) (fixedWidth + leftPadding);
        setPadding(newLeftPadding, getPaddingTop(), getPaddingBottom(), getPaddingRight());
        invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "getHeight:" + getHeight());
        Log.d(TAG, "getWidth:" + getWidth());
        Log.d(TAG, "getBaseline:" + getBaseline());
        Log.d(TAG, "leftPadding:" + leftPadding);

        Log.d(TAG, "getScrollX:" + getScrollX());
        Log.d(TAG, "w:" + canvas.getWidth() + "  H:" + canvas.getHeight());

        if (!TextUtils.isEmpty(fixedText)) {
//            int mGravity = getGravity();
//            switch (mGravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
//                case Gravity.END:
//                case Gravity.RIGHT:
//                    CharSequence text = getText();
//                    if (text.length() == 0) {
//                        text = getHint();
//                    }
//                    float textW = getPaint().measureText(String.valueOf(text));
//                    float drawLeft = Math.max(leftPadding, getWidth() - textW - getPaddingEnd() - fixedWidth);
//                    Log.d(TAG, "ssss:" + getBaseline());
////                    Log.d(TAG, "aaa:" + getFirstBaselineToTopHeight());
////                    Log.d(TAG, "bbb:" + getLastBaselineToBottomHeight());
//                    canvas.drawText(fixedText, drawLeft, getBaseline(), fixedPaint);
//                    break;
//                case Gravity.CENTER_HORIZONTAL:
//                    break;
//                default:
//                    canvas.drawText(fixedText, leftPadding, getBaseline(), fixedPaint);
//                    break;
//            }
            //为了适应gravity而设置translate
            //因为当设置gravity后画布会平移
            canvas.translate(getScrollX(), 0);

            canvas.drawText(fixedText, leftPadding, getBaseline(), getPaint());
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect(1, 1, 250, 350, paint);

            //取两图层交集部分，颜色叠加
            PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
            Paint paint1 = new Paint();
            paint1.setColor(Color.YELLOW);
            paint1.setStyle(Paint.Style.STROKE);
            paint1.setStrokeWidth(30);
//            paint.setXfermode(porterDuffXfermode);
            canvas.drawRect(500, 10, 1400, 300, paint1);
//            Editable text = getText();
//            float textW = getPaint().measureText(String.valueOf(text));
//            Log.d(TAG, "文字宽度:" + textW);
//            Log.d(TAG, "绘制的:" + leftPadding);
//            canvas.drawText(fixedText, leftPadding, getBaseline(), getPaint());
        }
    }

}