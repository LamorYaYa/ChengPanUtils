package com.lejingda.lahuo.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lejingda.lahuo.receiver.ScreenBroadcastReceiver;

/**
 *
 * @author master
 * @date 2018/1/26
 */

public class ScreenService extends Service {

    private ScreenBroadcastReceiver screenBroadcastReceiver = new ScreenBroadcastReceiver();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG","-- -- -- -- -- -- -- -- -- -- -- -- --");
        ScreenBroadcastReceiver screenBroadcastReceiver = new ScreenBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        getApplicationContext().registerReceiver(screenBroadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
