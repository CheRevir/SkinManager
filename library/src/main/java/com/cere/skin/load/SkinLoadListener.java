package com.cere.skin.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cere.skin.SkinResources;

/**
 * Created by CheRevir on 2021/4/2
 */
public interface SkinLoadListener {
    void onStart();

    void onSuccess(@NonNull SkinResources resources);

    void onFailure(@Nullable String error);
}
