package com.cere.skin.attr;

import android.util.ArrayMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Map;

/**
 * Created by CheRevir on 2021/3/30
 */
public class SkinAttrFactory {
    public static final Map<Integer, SkinAttr> SKIN_ATTR_ID_MAP = new ArrayMap<>();
    private static final Map<String, SkinAttr> SKIN_ATTR_NAME_MAP = new ArrayMap<>();

    static {
        addSupportSkinAttr(new SrcAttr());
    }

    public static void addSupportSkinAttr(@NonNull SkinAttr attr) {
        SKIN_ATTR_ID_MAP.put(attr.getAttrId(), attr);
        SKIN_ATTR_NAME_MAP.put(attr.getAttrName(), attr);
    }

    public static boolean isSupportSkinAttr(int attrId) {
        return SKIN_ATTR_ID_MAP.containsKey(attrId);
    }

    public static boolean isSupportSkinAttr(String attrName) {
        return SKIN_ATTR_NAME_MAP.containsKey(attrName);
    }

    @Nullable
    public static SkinAttr getSkinAttr(int attrId) {
        return SKIN_ATTR_ID_MAP.get(attrId);
    }

    @Nullable
    public static SkinAttr getSkinAttr(String attrName) {
        return SKIN_ATTR_NAME_MAP.get(attrName);
    }

    @NonNull
    public static Map<Integer, SkinAttr> getSkinAttrIdMap() {
        return SKIN_ATTR_ID_MAP;
    }

    @NonNull
    public static Map<String, SkinAttr> getSkinAttrNameMap() {
        return SKIN_ATTR_NAME_MAP;
    }
}
