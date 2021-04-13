package com.cere.skin;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import com.cere.skin.interfac.ISkinObserver;
import com.cere.skin.interfac.ISkinUpdate;
import com.cere.skin.load.SkinLoadListener;
import com.cere.skin.load.SkinResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CheRevir on 2021/3/29
 */
public class SkinManager implements ISkinObserver, SkinLoadListener {
    private volatile static SkinManager sInstance = null;
    private SkinConfig mSkinConfig;
    private final List<ISkinUpdate> mObservers = new ArrayList<>();
    private SkinResources mSkinResources;

    private SkinManager() {
    }

    public static SkinManager getInstance() {
        if (sInstance == null) {
            synchronized (SkinManager.class) {
                if (sInstance == null) {
                    sInstance = new SkinManager();
                }
            }
        }
        return sInstance;
    }

    public SkinManager init(@NonNull Context context) {
        init(new SkinConfig.Builder(context).build());
        return this;
    }

    public SkinManager init(@NonNull SkinConfig config) {
        this.mSkinConfig = config;
        return this;
    }

    @NonNull
    public SkinConfig getSkinConfig() {
        if (mSkinConfig == null) {
            throw new NullPointerException("no init");
        }
        return mSkinConfig;
    }

    public void apply(int skinKey) {
        SkinConfig config = getSkinConfig();
        Skin skin = config.getSkin(skinKey);
        if (skin != null) {
            new SkinResourceLoader(config, this).execute(skin);
        }
    }

    public void restore() {
        SkinConfig config = getSkinConfig();
        config.setCurrentSkin(0);
        mSkinResources = null;
        Context context = config.getContext();
        notifySkinUpdate(new SkinResources(context, context.getResources(), context.getPackageName()));
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @Override
    public void attach(ISkinUpdate observer) {
        mObservers.add(observer);
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @Override
    public void detach(ISkinUpdate observer) {
        mObservers.remove(observer);
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @Override
    public void notifySkinUpdate(@NonNull SkinResources resources) {
        for (ISkinUpdate iSkinUpdate : mObservers) {
            iSkinUpdate.onSkinUpdate(resources);
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @Override
    public void onStart() {
        SkinLoadListener listener = getSkinConfig().getSkinLoadListener();
        if (listener != null) {
            listener.onStart();
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @Override
    public void onSuccess(@NonNull SkinResources resources) {
        SkinLoadListener listener = getSkinConfig().getSkinLoadListener();
        if (listener != null) {
            listener.onSuccess(resources);
        }
        notifySkinUpdate(resources);
        this.mSkinResources = resources;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @Override
    public void onFailure(@Nullable String error) {
        SkinLoadListener listener = getSkinConfig().getSkinLoadListener();
        if (listener != null) {
            listener.onFailure(error);
        }
    }

    @Nullable
    public SkinResources getSkinResources() {
        return mSkinResources;
    }
}
