package com.lhzcpan.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by master on 2017/12/27.
 */

public class IUtils {
    /**
     * 获取mac地址
     *
     * @param context
     * @return
     */
    private static String getMacAddress(Context context) {
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String str = info.getMacAddress();
            if (str == null) {
                str = "";
            }
            return str;
        } catch (Exception e2) {
        }
        return "";
    }

    /**
     * 是否是WIFI
     *
     * @param context
     * @return
     */
    public static boolean hasWifiConnection(Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //是否有网络并且已经连接
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    /**
     * 分辨率
     *
     * @param context
     * @return
     */
    public static String getDisplayFbi(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels + "*" + outMetrics.heightPixels;
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String getCurrentDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(time);
    }

    /**
     * 获取第三方app个数
     *
     * @param context
     * @return
     */
    public static void getInstallAppList(Context context) {
        PackageManager iPackageManager = context.getPackageManager();
        List<PackageInfo> iPackageInfoInfoList = iPackageManager.getInstalledPackages(0); // 返回已安装的包信息列表
        for (int i = 0; i < iPackageInfoInfoList.size(); i++) {
            PackageInfo packageInfo = iPackageInfoInfoList.get(i);
            if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                // 过滤掉系统App
                continue;
            }
            if (packageInfo.applicationInfo.loadIcon(iPackageManager) == null) {
                // 过滤掉没有图标的App
                continue;
            }

            if (packageInfo.packageName.equals(context.getPackageName())) {
                // 过滤自身
                continue;
            }
            String appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            String packageName = packageInfo.packageName;
            // TODO continue
        }
    }

}
