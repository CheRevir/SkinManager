package com.cere.skin.attr;

import androidx.annotation.NonNull;

/**
 * Created by CheRevir on 2021/3/30
 */
public class Attr {
    private static final String RES_TYPE_COLOR = "color";
    private static final String RES_TYPE_DIMENSION = "dimen";
    private static final String RES_TYPE_DRAWABLE = "drawable";
    private static final String RES_TYPE_MIPMAP = "mipmap";

    private final int attrValueRefId;
    private final String attrValueRefName;
    private final String attrValueType;

    public Attr(int attrValueRefId, @NonNull String attrValueRefName, @NonNull String attrValueType) {
        this.attrValueRefId = attrValueRefId;
        this.attrValueRefName = attrValueRefName;
        this.attrValueType = attrValueType;
    }

    public int getAttrValueRefId() {
        return attrValueRefId;
    }

    @NonNull
    public String getAttrValueRefName() {
        return attrValueRefName;
    }

    @NonNull
    public String getAttrValueType() {
        return attrValueType;
    }

    public boolean isColor() {
        return RES_TYPE_COLOR.equals(attrValueType);
    }

    public boolean isDimension() {
        return RES_TYPE_DIMENSION.equals(attrValueType);
    }

    public boolean isDrawable() {
        return RES_TYPE_DRAWABLE.equals(attrValueType) || RES_TYPE_MIPMAP.equals(attrValueType);
    }

    @NonNull
    @Override
    public String toString() {
        return "Attr{" +
                "attrValueRefId=" + attrValueRefId +
                ", attrValueRefName='" + attrValueRefName + '\'' +
                ", attrValueType='" + attrValueType + '\'' +
                '}';
    }
}
