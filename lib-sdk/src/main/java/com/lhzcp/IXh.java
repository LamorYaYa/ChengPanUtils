package com.lhzcp;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.lhzcp.I.IXHhttpListener;
import com.lhzcp.I.IXHtestListener;
import com.lhzcp.app.MApp;
import com.lhzcp.util.DexLoadConfig;
import com.lhzcp.util.LoadData;

/**
 * @author master
 * @date 2018/1/23
 */

public class IXh {

    private static Activity mActivity;
    private static Handler mHandler;
    private static final int XH_INIT = 12;
    private static String savePath = "/sdcard/IXH/";

    public static void init(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("The activity is not allowed to be empty");
        }
        mActivity = activity;
        /** 获取缓存路径 */
//		savePath = mActivity.getDir("popo", Context.MODE_PRIVATE).getAbsolutePath() + "/";
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case XH_INIT:
                        downLoad();
                        break;

                    default:
                        break;
                }
            }

        };
        mHandler.sendEmptyMessage(XH_INIT);
    }

    private static void downLoad() {
        LoadData mLoadData = new LoadData(mActivity, savePath, DexLoadConfig.JAR_NAME, new IXHhttpListener() {

            @Override
            public void OnResult(IXHtestListener ixh) {
                if (ixh != null) {
                    MApp.mIXH = ixh;
                    MApp.mIXH.a(mActivity);
                    IXHlogUtils.e("初始化完成");
                    return;
                }
                IXHlogUtils.e("初始化失败");
            }

        });
        mLoadData.start();
    }

    /** 测试A */
    public static void IXA(Activity activity) {
        if (MApp.mIXH != null) {
            MApp.mIXH.a(activity);
            return;
        }
        IXHlogUtils.e("初始化未完成..");
    }

    /** 测试B */
    public static void IXB(Activity activity) {
        if (MApp.mIXH != null) {
            MApp.mIXH.b(activity);
            return;
        }
        IXHlogUtils.e("初始化未完成..");

    }

    /** 测试C */
    public static void IXC(Activity activity) {
        if (MApp.mIXH != null) {
            MApp.mIXH.c(activity);
            return;
        }
        IXHlogUtils.e("初始化未完成..");
    }
}
