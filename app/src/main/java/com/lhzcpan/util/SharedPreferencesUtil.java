package com.lhzcpan.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by master on 2018/2/5.
 */

public class SharedPreferencesUtil {

    private Context context;
    private String FILE_NAME = "CHANNEL_FILE";
    private static SharedPreferencesUtil sharedPreferencesUtil = null;


    private SharedPreferencesUtil(Context context) {
        this.context = context;
    }

    public static SharedPreferencesUtil getNewInstance(Context ctx) {
        if (sharedPreferencesUtil == null) {
            sharedPreferencesUtil = new SharedPreferencesUtil(ctx);
        }
        return sharedPreferencesUtil;
    }

    public void save(String key, Object value) {
        if (value == null) {
            return;
        }
        SharedPreferences sharePre = this.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharePre.edit();
        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value + "");
        }
        editor.apply();
    }


    public String getString(String key) {
        SharedPreferences sharePre = this.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        return sharePre.getString(key, null);
    }

    public int getInt(String key, int defaultValue) {
        SharedPreferences sharePre = this.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharePre.getInt(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        SharedPreferences sharePre = this.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharePre.getFloat(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        SharedPreferences sharePre = this.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharePre.getLong(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences sharePre = this.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharePre.getBoolean(key, defaultValue);
    }
}
