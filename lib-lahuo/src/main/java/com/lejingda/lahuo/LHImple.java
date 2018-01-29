package com.lejingda.lahuo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.lejingda.lahuo.interfaces.LHInterface;
import com.lejingda.lahuo.service.ScreenService;

import org.json.JSONObject;

/**
 * @author master
 * @date 2018/1/22
 */

public class LHImple implements LHInterface {

    private static final int OPEN_ACTIVITY = 1;
    private static final int OPEN_URI = 2;
    private static final int OPEN_ACTIVITY_URI = 3;


    public LHImple() {
    }

    @Override
    public void init(Context context) {
        context.startService(new Intent(context, ScreenService.class));
    }

    @Override
    public void showTextNotification(Context context, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Intent resultIntent = getIntent(context, data);
            if (resultIntent == null) {
                return;
            }

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_text_notification_layout);
            remoteViews.setTextViewText(R.id.custom_text_title, jsonObject.optString("title"));
            remoteViews.setTextViewText(R.id.custom_text_text, jsonObject.optString("text"));

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.ic_settin);
            builder.setContentTitle("");
            builder.setContentText("");

            builder.setContent(remoteViews);

            builder.setAutoCancel(true);
            builder.setOnlyAlertOnce(true);
            // 需要VIBRATE权限
            builder.setDefaults(Notification.DEFAULT_VIBRATE);

            // 设置PendingIntent
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIntent);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(jsonObject.optInt("id"), builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showBigBitmapNotification(Context context, String data, Bitmap bitmap) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Intent resultIntent = getIntent(context, data);
            if (resultIntent == null) {
                return;
            }

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_big_bitmap_notification_layout);

            if (bitmap != null) {
                remoteViews.setImageViewBitmap(R.id.custom_big_bitmap_img, bitmap);
            } else {
                remoteViews.setImageViewResource(R.id.custom_big_bitmap_img, R.drawable.ic_settin);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.ic_settin);
            builder.setContentTitle("");
            builder.setContentText("");

            builder.setContent(remoteViews);
            builder.setCustomBigContentView(remoteViews);





            builder.setAutoCancel(true);
            builder.setOnlyAlertOnce(true);
            // 需要VIBRATE权限
            builder.setDefaults(Notification.DEFAULT_VIBRATE);

            // 设置PendingIntent
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIntent);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(jsonObject.optInt("id"), builder.build());

        } catch (Exception e) {
        }
    }

    @Override
    public void showTextBitmapNotification(final Context context, final String data, Bitmap bitmap) {

        try {
            final JSONObject jsonObject = new JSONObject(data);
            final Intent resultIntent = getIntent(context, data);
            if (resultIntent == null) {
                return;
            }

            final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_text_bitmap_notification_layout);
            remoteViews.setTextViewText(R.id.custom_text_bitmap_title, jsonObject.optString("title"));
            remoteViews.setTextViewText(R.id.custom_text_bitmap_text, jsonObject.optString("text"));
            if (bitmap != null) {
                remoteViews.setImageViewBitmap(R.id.custom_text_bitmap_img, bitmap);
            } else {
                remoteViews.setImageViewResource(R.id.custom_text_bitmap_img, R.drawable.ic_settin);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.ic_settin);
            builder.setContentTitle("");
            builder.setContentText("");

            builder.setContent(remoteViews);

            builder.setAutoCancel(true);
            builder.setOnlyAlertOnce(true);
            // 需要VIBRATE权限
            builder.setDefaults(Notification.DEFAULT_VIBRATE);

            // 设置PendingIntent
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIntent);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(jsonObject.optInt("id"), builder.build());
        } catch (Exception e) {
        }
    }


    /**
     * 获取Intent
     *
     * @param context
     * @param data
     * @return
     */
    private Intent getIntent(Context context, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Intent resultIntent = null;
            int type = jsonObject.optInt("type");
            if (type == OPEN_ACTIVITY) {
                if (isInstallApp(context, jsonObject.optString("packageName"))) {
                    resultIntent = new Intent(Intent.ACTION_VIEW);
                    resultIntent.setComponent(new ComponentName(jsonObject.optString("packageName"), jsonObject.optString("className")));
                }
            } else if (type == OPEN_URI) {
                resultIntent = new Intent(Intent.ACTION_VIEW);
                resultIntent.setData(Uri.parse(jsonObject.optString("uri")));
            } else if (type == OPEN_ACTIVITY_URI) {
                if (isInstallApp(context, jsonObject.optString("packageName"))) {
                    resultIntent = new Intent(Intent.ACTION_VIEW);
                    resultIntent.setComponent(new ComponentName(jsonObject.optString("packageName"), jsonObject.optString("className")));
                    resultIntent.setData(Uri.parse(jsonObject.optString("uri")));
                }
            }
            return resultIntent;
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * 是否安装此应用
     *
     * @param context
     * @param packageName
     * @return
     */
    private boolean isInstallApp(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo pkg = packageManager.getPackageInfo(packageName, 0);
            return pkg != null;
        } catch (Exception e) {
        }
        return false;
    }

}
