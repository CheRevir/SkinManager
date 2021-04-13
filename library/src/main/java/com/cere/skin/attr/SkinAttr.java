package com.cere.skin.attr;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cere.skin.SkinResources;

/**
 * Created by CheRevir on 2021/3/29
 */
public abstract class SkinAttr {

    public abstract void apply(@NonNull View view, @NonNull SkinResources resources, @NonNull Attr attr);

    @NonNull
    public abstract String getAttrName();

    public abstract int getAttrId();

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SkinAttr)) return false;
        SkinAttr attr = (SkinAttr) obj;
        return this.getAttrName().equals(attr.getAttrName()) && this.getAttrId() == attr.getAttrId();
    }
}
