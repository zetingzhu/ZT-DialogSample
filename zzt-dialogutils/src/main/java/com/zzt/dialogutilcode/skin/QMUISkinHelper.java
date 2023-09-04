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

package com.zzt.dialogutilcode.skin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zzt.dialogutilcode.util.QMUIResHelper;

public class QMUISkinHelper {


    public static int getSkinColor(@NonNull View view, int colorAttr) {
        return QMUIResHelper.getAttrColor(view.getContext(), colorAttr);
    }

    public static ColorStateList getSkinColorStateList(@NonNull View view, int colorAttr) {
        return QMUIResHelper.getAttrColorStateList(view.getContext(), view.getContext().getTheme(), colorAttr);
    }

    @Nullable
    public static Drawable getSkinDrawable(@NonNull View view, int drawableAttr) {
        return QMUIResHelper.getAttrDrawable(view.getContext(), view.getContext().getTheme(), drawableAttr);
    }


    public static void setSkinValue(@NonNull View view, String value) {

    }


    public static int getCurrentSkinIndex(@NonNull View view) {
        return 0;
    }

    public static void refreshViewSkin(@NonNull View view) {

    }

    public static void syncViewSkin(@NonNull View view, @NonNull View sourceView) {

    }

    public static void setIgnoreSkinApply(@NonNull View view, boolean ignore) {

    }

    public static void setInterceptSkinDispatch(@NonNull View view, boolean intercept) {
    }

    public static void warnRuleNotSupport(View view, String rule) {

    }

}
