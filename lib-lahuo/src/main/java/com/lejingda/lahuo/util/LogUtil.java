package com.lejingda.lahuo.util;

import android.util.Log;

/**
 * @author master
 * @date 2018/1/23
 */

public class LogUtil {
    private static final String TAG = "MASTER";
    private static final boolean isPrint = true;

    public static void e(String msg) {
        if (msg == null || msg.length() == 0) {
            return;
        }
        if (isPrint) {
            Log.e(TAG, msg);
        }
    }
}
