package com.cere.skin;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cere.skin.attr.Attr;
import com.cere.skin.attr.SkinAttrFactory;
import com.cere.skin.interfac.ISkinUpdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CheRevir on 2021/3/31
 */
public class SkinLayoutInflater implements LayoutInflater.Factory2 {
    private static final String NAMESPACE = "http://schemas.android.com/apk/skin";
    private static final String SUPPORT_SKIN = "supportSkin";
    private final ISkinUpdate mISkinUpdate;
    private final List<SkinItem> mSkinItems;

    public SkinLayoutInflater(@NonNull ISkinUpdate iSkinUpdate) {
        this.mISkinUpdate = iSkinUpdate;
        this.mSkinItems = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = mISkinUpdate.getAppCompatDelegate().createView(parent, name, context, attrs);
        if (view != null) {
            boolean supportSkin = attrs.getAttributeBooleanValue(NAMESPACE, SUPPORT_SKIN, false);
            if (supportSkin) {
                parseSkinAttr(context, view, attrs);
            }
        }
        return view;
    }

    private void parseSkinAttr(Context context, View view, AttributeSet attrs) {
        List<Attr> skinAttr = new ArrayList<>();
        Integer[] ids = SkinAttrFactory.SKIN_ATTR_ID_MAP.keySet().toArray(new Integer[0]);
        int[] styleAttrs = Arrays.stream(ids).mapToInt(i -> i).toArray();
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, styleAttrs, 0, 0);
        for (int i = 0; i < array.length(); i++) {
            int id = array.getResourceId(i, -1);
            if (id != -1) {
                String entry = context.getResources().getResourceEntryName(id);
                String type = context.getResources().getResourceTypeName(id);
                Attr attr = new Attr(id, entry, type);
                skinAttr.add(attr);
            }
        }
        array.recycle();
        if (skinAttr.size() > 0) {
            SkinItem skinItem = new SkinItem(view, skinAttr);
            mSkinItems.add(skinItem);
            SkinResources resources = SkinManager.getInstance().getSkinResources();
            if (resources != null && skinItem.apply(resources)) {
                mISkinUpdate.onSkinUpdated(view, resources);
            }
        }
    }

    public void applySkin(@NonNull SkinResources resources) {
        for (SkinItem item : mSkinItems) {
            if (item.apply(resources)) {
                mISkinUpdate.onSkinUpdated(item.getView(), resources);
            }
        }
    }
}
