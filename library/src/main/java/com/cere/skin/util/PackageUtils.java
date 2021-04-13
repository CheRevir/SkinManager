package com.cere.skin.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by CheRevir on 2021/3/30
 */
public class PackageUtils {

    @Nullable
    public static String getPackageName(@NonNull Context context, @NonNull String path) {
        PackageInfo info = context.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        return info == null ? null : info.packageName;
    }

    @Nullable
    public static Resources getResources(@NonNull Context context, @NonNull String path) {
        PackageInfo info = context.getPackageManager().getPackageArchiveInfo(path, 0);
        if (info != null) {
            info.applicationInfo.sourceDir = path;
            info.applicationInfo.publicSourceDir = path;
            try {
                Resources res = context.getPackageManager().getResourcesForApplication(info.applicationInfo);
                Resources superRes = context.getResources();
                return new Resources(res.getAssets(), superRes.getDisplayMetrics(), superRes.getConfiguration());
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
