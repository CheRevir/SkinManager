package com.cere.skin.load;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by CheRevir on 2021/3/30
 */
public interface SkinLoaderStrategy {
    @Nullable
    String getSkinFilePath(@NonNull Context context, @NonNull String path);

    int getStrategy();
}
