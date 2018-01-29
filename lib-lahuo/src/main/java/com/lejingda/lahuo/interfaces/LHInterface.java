package com.lejingda.lahuo.interfaces;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * @author master
 * @date 2018/1/22
 */

public interface LHInterface {
    /**
     * 初始化
     *
     * @param context
     */
    void init(Context context);

    /**
     * 文字通知
     *
     * @param context
     * @param data
     */
    void showTextNotification(Context context, String data);

    /**
     * 大图通知
     *
     * @param context
     * @param data
     */
    void showBigBitmapNotification(Context context, String data, Bitmap bitmap);

    /**
     * 图文通知
     *
     * @param context
     * @param data
     */
    void showTextBitmapNotification(Context context, String data, Bitmap bitmap);
}
