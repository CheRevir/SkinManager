package com.cere.skin.load;

import android.os.AsyncTask;

import com.cere.skin.Skin;
import com.cere.skin.SkinConfig;
import com.cere.skin.SkinResources;
import com.cere.skin.util.PackageUtils;

/**
 * Created by CheRevir on 2021/4/1
 */
@SuppressWarnings("deprecation")
public class SkinResourceLoader extends AsyncTask<Skin, Void, SkinResources> {
    private final SkinConfig mSkinConfig;
    private final SkinLoadListener mSkinLoadListener;

    public SkinResourceLoader(SkinConfig config, SkinLoadListener loadListener) {
        this.mSkinConfig = config;
        this.mSkinLoadListener = loadListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mSkinLoadListener.onStart();
    }

    @Override
    protected SkinResources doInBackground(Skin... skins) {
        Skin skin = skins.length > 0 ? skins[0] : null;
        if (skin == null) {
            mSkinLoadListener.onFailure("Skin null");
            return null;
        }
        SkinLoaderStrategy strategy = mSkinConfig.getSkinLoaderStrategy(skin.getLoadStrategy());
        if (strategy == null) {
            mSkinLoadListener.onFailure("SkinLoaderStrategy null");
            return null;
        }
        String path = strategy.getSkinFilePath(mSkinConfig.getContext(), skin.getPath());
        if (path == null) {
            mSkinLoadListener.onFailure("Skin path null");
            return null;
        }
        return new SkinResources(mSkinConfig.getContext(),
                PackageUtils.getResources(mSkinConfig.getContext(), path),
                PackageUtils.getPackageName(mSkinConfig.getContext(), path));
    }

    @Override
    protected void onPostExecute(SkinResources resources) {
        super.onPostExecute(resources);
        if (resources != null) {
            mSkinLoadListener.onSuccess(resources);
        }
    }
}
