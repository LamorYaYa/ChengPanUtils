package com.lejingda.lahuo.service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.lejingda.lahuo.R;
import com.lejingda.lahuo.util.LogUtil;
import com.lejingda.lahuo.widget.WidgetProvider;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author master
 * @date 2018/1/25
 */

public class ClockService extends Service {

    private Timer mTimer;
//    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null == mTimer) {
            mTimer = new Timer();
        }
        mTimer.schedule(new ITimerTask(), 0, 1000);




        return START_STICKY;
    }

    class ITimerTask extends TimerTask {

        @Override
        public void run() {
            try {
                AppWidgetManager widgetManager = AppWidgetManager.getInstance(getApplicationContext());
                RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.widget_layout);
                remoteView.setTextViewText(R.id.clock_tv, getCurrentDate());
                ComponentName componentName = new ComponentName(getApplicationContext(), WidgetProvider.class);
                widgetManager.updateAppWidget(componentName, remoteView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String result = simpleDateFormat.format(System.currentTimeMillis());
        return result.split(" ")[1] + "\n" + result.split(" ")[0];
    }
}
