package com.cere.skin.app;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;

import com.cere.skin.SkinLayoutInflater;
import com.cere.skin.SkinManager;
import com.cere.skin.SkinResources;
import com.cere.skin.interfac.ISkinUpdate;

/**
 * Created by CheRevir on 2021/3/29
 */
public class SkinActivity extends AppCompatActivity implements ISkinUpdate {
    private SkinLayoutInflater mFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), mFactory);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinManager.getInstance().attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
    }

    @Override
    public final void onSkinUpdate(@NonNull SkinResources resources) {
        mFactory.applySkin(resources);
    }

    @Override
    public void onSkinUpdated(@NonNull View view, @NonNull SkinResources resources) {
    }

    @NonNull
    @Override
    public AppCompatDelegate getAppCompatDelegate() {
        return getDelegate();
    }

    @NonNull
    public SkinLayoutInflater getSkinLayoutInflater() {
        return mFactory;
    }
}
