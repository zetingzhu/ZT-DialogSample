package com.zzt.dialogutilcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author: zeting
 * @date: 2023/2/9
 */
public class DialogRootAct extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, DialogRootAct.class);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_root_act);
    }
}
