package com.lhzcpan;

import android.util.Log;

/**
 * Created by master on 2017/12/26.
 */

public class LogUtils {

    private static final boolean a = true;
    private static final String TAG = "MASTER";

    public static void e(String msg) {
        if (a) {
            Log.e(TAG, msg);
        }
    }


}
