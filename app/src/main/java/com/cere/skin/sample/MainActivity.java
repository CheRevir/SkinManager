package com.cere.skin.sample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.cere.skin.Skin;
import com.cere.skin.SkinConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SkinConfig config = new SkinConfig.Builder(this).addSkin(new Skin(1, 1, "")).build();
        Log.e("TAG", "MainActivity -> onCreate: " + config.getSkins());
        Log.e("TAG", "MainActivity -> onCreate: " + new SkinConfig.Builder(this).build().getSkins());
    }
}