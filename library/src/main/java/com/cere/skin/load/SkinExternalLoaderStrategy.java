package com.cere.skin.load;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cere.skin.SkinConfig;

/**
 * Created by CheRevir on 2021/3/30
 */
public class SkinExternalLoaderStrategy implements SkinLoaderStrategy {
    @Nullable
    @Override
    public String getSkinFilePath(@NonNull Context context, @NonNull String path) {
        return path;
    }

    @Override
    public int getStrategy() {
        return SkinConfig.SKIN_EXTERNAL_LOADER_STRATEGY;
    }
}
