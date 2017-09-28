package com.xr.wallpaper.Service;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

import android.support.annotation.Nullable;

import com.xr.wallpaper.R;

import java.io.IOException;

/**
 * Created by 16271 on 2017/9/27.
 */

public class ChangeService extends Service {
    WallpaperManager wallpaperManager;
    int current=0;
    int[] wallpaper={
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6
    };
    @Override
    public void onCreate() {
        super.onCreate();
        wallpaperManager=WallpaperManager.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (current>5){
            current=0;
        }
        try {
            wallpaperManager.setResource(wallpaper[current++]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
