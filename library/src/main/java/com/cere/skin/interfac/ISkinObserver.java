package com.cere.skin.interfac;

import androidx.annotation.NonNull;

import com.cere.skin.SkinResources;

/**
 * Created by CheRevir on 2021/4/1
 */
public interface ISkinObserver {
    void attach(ISkinUpdate observer);

    void detach(ISkinUpdate observer);

    void notifySkinUpdate(@NonNull SkinResources resources);
}
