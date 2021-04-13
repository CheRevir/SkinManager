package com.cere.skin;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Created by CheRevir on 2021/3/31
 */
public class Skin {
    private final int key;
    private final int loadStrategy;
    private final String path;

    /**
     * @param key 皮肤标识key，0为默认皮肤key
     * @param loadStrategy {@link SkinConfig#SKIN_EXTERNAL_LOADER_STRATEGY}, {@link SkinConfig#SKIN_ASSETS_LOADER_STRATEGY}
     * @param data 皮肤文件路径
     */
    public Skin(@IntRange(from = 1) int key, int loadStrategy, @NonNull String data) {
        this.key = key;
        this.loadStrategy = loadStrategy;
        this.path = data;
    }

    public int getKey() {
        return key;
    }

    public int getLoadStrategy() {
        return loadStrategy;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skin)) return false;
        Skin skin = (Skin) o;
        return key == skin.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @NonNull
    @Override
    public String toString() {
        return "Skin{" +
                "key=" + key +
                ", loadStrategy=" + loadStrategy +
                ", path='" + path + '\'' +
                '}';
    }
}
