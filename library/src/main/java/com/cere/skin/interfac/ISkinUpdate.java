package com.cere.skin.interfac;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.cere.skin.SkinResources;

/**
 * Created by CheRevir on 2021/4/1
 */
public interface ISkinUpdate {
    void onSkinUpdate(@NonNull SkinResources resources);

    void onSkinUpdated(@NonNull View view, @NonNull SkinResources resources);

    @NonNull
    AppCompatDelegate getAppCompatDelegate();
}
