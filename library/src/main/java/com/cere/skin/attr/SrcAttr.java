package com.cere.skin.attr;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.cere.skin.SkinResources;

/**
 * Created by CheRevir on 2021/3/30
 */
public class SrcAttr extends SkinAttr {
    @Override
    public void apply(@NonNull View view, @NonNull SkinResources resources, @NonNull Attr attr) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if (attr.isColor()) {
                imageView.setImageDrawable(new ColorDrawable(resources.getColor(attr.getAttrValueRefId())));
            } else if (attr.isDrawable()) {
                imageView.setImageDrawable(resources.getDrawable(attr.getAttrValueRefId()));
            }
        }
    }

    @NonNull
    @Override
    public String getAttrName() {
        return "src";
    }

    @Override
    public int getAttrId() {
        return android.R.attr.src;
    }
}
