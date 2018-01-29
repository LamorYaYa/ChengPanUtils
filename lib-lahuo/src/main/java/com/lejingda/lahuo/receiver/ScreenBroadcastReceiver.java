package com.lejingda.lahuo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lejingda.lahuo.util.LogUtil;

/**
 * @author master
 * @date 2018/1/23
 */

public class ScreenBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.e("广播Action = " + action);
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            LogUtil.e("锁屏");
        } else if (action.equals(Intent.ACTION_SCREEN_ON)) {
            LogUtil.e("解锁");
        }else if(action.equals(Intent.ACTION_USER_PRESENT)){
            LogUtil.e("开屏");
        }
    }
}
