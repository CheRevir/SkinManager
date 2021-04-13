package com.cere.skin;

import android.content.Context;
import android.util.ArrayMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cere.skin.attr.SkinAttr;
import com.cere.skin.attr.SkinAttrFactory;
import com.cere.skin.load.SkinAssetsLoaderStrategy;
import com.cere.skin.load.SkinExternalLoaderStrategy;
import com.cere.skin.load.SkinLoadListener;
import com.cere.skin.load.SkinLoaderStrategy;

import java.util.Map;

/**
 * Created by CheRevir on 2021/3/29
 */
public class SkinConfig {
    public static final int SKIN_EXTERNAL_LOADER_STRATEGY = 1;
    public static final int SKIN_ASSETS_LOADER_STRATEGY = 2;

    private final Context mContext;
    private final Map<Integer, SkinLoaderStrategy> mSkinLoaderStrategyMap;
    private final Map<Integer, Skin> mSkinMap;
    private int skinKey = 0;
    private SkinLoadListener mSkinLoadListener;

    private SkinConfig(@NonNull Builder builder) {
        this.mContext = builder.mContext;
        this.mSkinLoaderStrategyMap = builder.mSkinLoaderStrategyMap;
        this.mSkinMap = builder.mSkinMap;
        this.skinKey = builder.skinKey;
        this.mSkinLoadListener = builder.mSkinLoadListener;
    }

    public SkinConfig addSupportSkinAttr(@NonNull SkinAttr attr) {
        SkinAttrFactory.addSupportSkinAttr(attr);
        return this;
    }

    public SkinConfig addSkin(@NonNull Skin skin) {
        mSkinMap.put(skin.getKey(), skin);
        return this;
    }

    public SkinConfig removeSkin(int skinKey) {
        mSkinMap.remove(skinKey);
        return this;
    }

    protected SkinConfig setCurrentSkin(int skinKey) {
        this.skinKey = skinKey;
        return this;
    }

    @NonNull
    public Context getContext() {
        return mContext;
    }

    @NonNull
    public Map<Integer, Skin> getSkinMap() {
        return mSkinMap;
    }

    public int getCurrentSkinKey() {
        return skinKey;
    }

    @Nullable
    public Skin getSkin(int skinKey) {
        return mSkinMap.get(skinKey);
    }

    @Nullable
    public Skin getCurrentSkin() {
        return mSkinMap.get(skinKey);
    }

    @Nullable
    public SkinLoaderStrategy getSkinLoaderStrategy(int key) {
        return mSkinLoaderStrategyMap.get(key);
    }

    @NonNull
    public Map<Integer, SkinLoaderStrategy> getSkinLoaderStrategyMap() {
        return mSkinLoaderStrategyMap;
    }

    public void setSkinLoadListener(@Nullable SkinLoadListener loadListener) {
        this.mSkinLoadListener = loadListener;
    }

    @Nullable
    public SkinLoadListener getSkinLoadListener() {
        return mSkinLoadListener;
    }

    public static class Builder {
        private final Context mContext;
        private final Map<Integer, SkinLoaderStrategy> mSkinLoaderStrategyMap = new ArrayMap<>();
        private final Map<Integer, Skin> mSkinMap = new ArrayMap<>();
        private int skinKey = 0;
        private SkinLoadListener mSkinLoadListener;

        public Builder(@NonNull Context context) {
            this.mContext = context.getApplicationContext();
            mSkinLoaderStrategyMap.put(SKIN_EXTERNAL_LOADER_STRATEGY, new SkinExternalLoaderStrategy());
            mSkinLoaderStrategyMap.put(SKIN_ASSETS_LOADER_STRATEGY, new SkinAssetsLoaderStrategy());
        }

        public Builder addSkinLoaderStrategy(SkinLoaderStrategy strategy) {
            mSkinLoaderStrategyMap.put(strategy.getStrategy(), strategy);
            return this;
        }

        public Builder addSupportSkinAttr(@NonNull SkinAttr attr) {
            SkinAttrFactory.addSupportSkinAttr(attr);
            return this;
        }

        public Builder addSkin(@NonNull Skin skin) {
            mSkinMap.put(skin.getKey(), skin);
            return this;
        }

        public Builder setCurrentSkin(int skinKey) {
            this.skinKey = skinKey;
            return this;
        }

        public Builder setSkinLoaderListener(@NonNull SkinLoadListener loaderListener) {
            mSkinLoadListener = loaderListener;
            return this;
        }

        public SkinConfig build() {
            return new SkinConfig(this);
        }
    }
}
