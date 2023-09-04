package com.zzt.dialogutilcode.zdialog;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.zzt.dialogutilcode.MainActivity;
import com.zzt.dialogutilcode.R;

/**
 * @author: zeting
 * @date: 2023/2/10
 */
public class MDialogA extends BaseShowDismissAppCompatDialog {
    ZDialogRootView dialog_root_view;

    public MDialogA(@NonNull Context context) {
        super(context, R.style.Style_Base_Dialog);
    }

    public MDialogA(@NonNull Context context, int themeResId) {
        super(context, R.style.Style_Base_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_dialog_a);
        dialog_root_view = findViewById(R.id.dialog_root_view);
        if (dialog_root_view != null) {
            dialog_root_view.setShowDialog(this);
            dialog_root_view.setParentDrawable(ContextCompat.getDrawable(getContext(), R.drawable.white_round_7_n));
            ZDialogBuildHelp buildHelp = new ZDialogBuildHelp(dialog_root_view);
            buildHelp
                    .setTopImage(com.zzt.toolslib.R.drawable.ic_compare)
                    .setRightDel(new ZDialogImageView.DialogImageViewListener() {
                        @Override
                        public void onClick(BaseShowDismissAppCompatDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setTitleMessage("ttttt", "MMMMMM")
                    .addActionTopBlue(new ZDialogAction("AAA", new ZDialogAction.ActionListener() {
                        @Override
                        public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                            dialog.dismiss();
                        }
                    }))
                    .addActionBottomGray(new ZDialogAction("BBB", new ZDialogAction.ActionListener() {
                        @Override
                        public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                            dialog.dismiss();
                        }
                    }))
                    .setBottomDel(new ZDialogImageView.DialogImageViewListener() {
                        @Override
                        public void onClick(BaseShowDismissAppCompatDialog dialog) {
                            dialog.dismiss();
                        }
                    })
            ;
            dialog_root_view.postCreateView();
        }
    }
}
