package com.lejingda.lahuo.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.lejingda.lahuo.R;
import com.lejingda.lahuo.service.ClockService;
import com.lejingda.lahuo.util.LogUtil;

import java.text.SimpleDateFormat;

/**
 * @author master
 * @date 2018/1/25
 */

public class WidgetProvider extends AppWidgetProvider {

    private static final String CLICK_ACTION = "com.lejingda.lahuo.widget.action.CLICK";


    public WidgetProvider() {
        super();
    }

    /**
     * 接收窗口小部件点击时发送的广播
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        LogUtil.e("On Receive");
        if (CLICK_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "@#%￥#@#￥%", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 每次窗口小部件被更新都调用一次该方法
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        LogUtil.e("On Update");

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        remoteViews.setTextViewText(R.id.clock_tv, getCurrentDate());

        Intent intent = new Intent(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.widget_img, pendingIntent);
        for (int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }

        context.startService(new Intent(context, ClockService.class));

    }

    /**
     * 当小部件大小改变时
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetId
     * @param newOptions
     */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);

        LogUtil.e("On AppWidgetOptionsChanged");

    }

    /**
     * 每删除一次窗口小部件就调用一次
     *
     * @param context
     * @param appWidgetIds
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        LogUtil.e("On Deleted");

    }

    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法
     *
     * @param context
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

        LogUtil.e("On Enabled");

    }

    /**
     * 当最后一个该窗口小部件删除时调用该方法
     *
     * @param context
     */
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);

        LogUtil.e("On Disabled");

    }

    /**
     * 当小部件从备份恢复时调用该方法
     *
     * @param context
     * @param oldWidgetIds
     * @param newWidgetIds
     */
    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);

        LogUtil.e("On Restored");

    }

    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String result = simpleDateFormat.format(System.currentTimeMillis());
        return result.split(" ")[1] + "\n" + result.split(" ")[0];
    }


}
