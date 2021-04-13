package com.cere.skin;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cere.skin.attr.Attr;
import com.cere.skin.attr.SkinAttr;
import com.cere.skin.attr.SkinAttrFactory;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

/**
 * Created by CheRevir on 2021/3/30
 */
public class SkinItem {
    private final WeakReference<View> mViewWeakReference;
    private final List<Attr> mAttrs;

    public SkinItem(@NonNull View view, @NonNull List<Attr> list) {
        this.mViewWeakReference = new WeakReference<>(view);
        this.mAttrs = list;
    }

    @Nullable
    public View getView() {
        return mViewWeakReference.get();
    }

    @NonNull
    public List<Attr> getAttrs() {
        return mAttrs;
    }

    public boolean apply(SkinResources resources) {
        View view = getView();
        if (view != null) {
            for (Attr attr : mAttrs) {
                SkinAttr attr1 = SkinAttrFactory.getSkinAttr(attr.getAttrValueRefId());
                if (attr1 != null && attr1.getAttrName().equals(attr.getAttrValueRefName())) {
                    attr1.apply(view, resources, attr);
                } else {
                    SkinAttr attr2 = SkinAttrFactory.getSkinAttr(attr.getAttrValueRefName());
                    if (attr2 != null && attr2.getAttrId() == attr.getAttrValueRefId()) {
                        attr2.apply(view, resources, attr);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkinItem)) return false;
        SkinItem item = (SkinItem) o;
        return Objects.equals(mViewWeakReference, item.mViewWeakReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mViewWeakReference);
    }

    @NonNull
    @Override
    public String toString() {
        return "SkinItem{" +
                "mViewWeakReference=" + mViewWeakReference +
                ", mAttrs=" + mAttrs +
                '}';
    }
}
