package com.cere.skin;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

/**
 * Created by CheRevir on 2021/3/30
 */
public class SkinResources {
    private final Context mContext;
    private final Resources mResources;
    private final String mSkinPackageName;

    public SkinResources(@NonNull Context context, @NonNull Resources res, @NonNull String skinPackageName) {
        this.mContext = context;
        this.mResources = res;
        this.mSkinPackageName = skinPackageName;
    }

    @ColorInt
    public int getColor(@ColorRes int resId) {
        int origin = ContextCompat.getColor(mContext, resId);
        int trueResId = getTargetResId(mContext, resId, mSkinPackageName);
        if (trueResId <= 0) {
            return origin;
        }
        return ResourcesCompat.getColor(mResources, trueResId, null);
    }

    @Nullable
    public Drawable getDrawable(@DrawableRes int resId) {
        Drawable origin = ContextCompat.getDrawable(mContext, resId);
        int trueResId = getTargetResId(mContext, resId, mSkinPackageName);
        if (trueResId <= 0) {
            return origin;
        }
        return ResourcesCompat.getDrawable(mResources, trueResId, null);
    }

    @Nullable
    public ColorStateList getColorStateList(@ColorRes int resId) {
        ColorStateList origin = ContextCompat.getColorStateList(mContext, resId);
        int trueResId = getTargetResId(mContext, resId, mSkinPackageName);
        if (trueResId <= 0) {
            return origin;
        }
        return ResourcesCompat.getColorStateList(mResources, trueResId, null);
    }

    public float getDimension(@DimenRes int resId) {
        float origin = mContext.getResources().getDimension(resId);
        int trueResId = getTargetResId(mContext, resId, mSkinPackageName);
        if (trueResId <= 0) {
            return origin;
        }
        return mResources.getDimension(trueResId);
    }

    public int getTargetResId(@NonNull Context context, int resId, @NonNull String skinPackageName) {
        String entry = context.getResources().getResourceEntryName(resId);
        String type = context.getResources().getResourceTypeName(resId);
        if (!TextUtils.isEmpty(entry) && !TextUtils.isEmpty(type)) {
            return mResources.getIdentifier(entry, type, skinPackageName);
        }
        return -1;
    }
}
