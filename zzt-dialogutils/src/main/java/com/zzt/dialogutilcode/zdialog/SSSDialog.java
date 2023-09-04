package com.zzt.dialogutilcode.zdialog;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialog;

import com.zzt.dialogutilcode.R;

/**
 * @author: zeting
 * @date: 2022/11/10
 */
public class SSSDialog extends AppCompatDialog {

    public SSSDialog(Context context) {
        super(context , R.style.Style_Base_Dialog);
    }

    public SSSDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ssss_dddd);
    }
}
