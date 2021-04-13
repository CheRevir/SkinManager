package com.cere.skin.load;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cere.skin.SkinConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by CheRevir on 2021/3/31
 */
public class SkinAssetsLoaderStrategy extends SkinExternalLoaderStrategy {
    @Nullable
    @Override
    public String getSkinFilePath(@NonNull Context context, @NonNull String path) {
        File file = context.getExternalFilesDir(path);
        if (file != null) {
            if (file.exists()) {
                FileInputStream fileInputStream = null;
                try (InputStream inputStream = context.getAssets().open(path)) {
                    fileInputStream = new FileInputStream(file);
                    if (getMD5(inputStream).equals(getMD5(fileInputStream))) {
                        return super.getSkinFilePath(context, file.getPath());
                    } else if (file.delete() && copyAssetsToExternal(context, path, file)) {
                        return super.getSkinFilePath(context, file.getPath());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (copyAssetsToExternal(context, path, file)) {
                return super.getSkinFilePath(context, file.getPath());
            }
        }
        return null;
    }

    @Override
    public int getStrategy() {
        return SkinConfig.SKIN_ASSETS_LOADER_STRATEGY;
    }

    private boolean copyAssetsToExternal(Context context, String fileName, File toFile) {
        boolean result;
        FileOutputStream outputStream = null;
        try (InputStream inputStream = context.getAssets().open(fileName)) {
            outputStream = new FileOutputStream(toFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String getMD5(InputStream inputStream) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[1024];
            int length;
            MessageDigest md = MessageDigest.getInstance("MD5");
            while ((length = inputStream.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            bi = new BigInteger(1, md.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        if (bi != null) {
            return bi.toString(16);
        }
        return "";
    }
}
