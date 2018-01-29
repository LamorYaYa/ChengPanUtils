package com.lhzcpan.http;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import com.lhzcpan.http.interfaces.OnDownloadListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by master on 2017/12/4.
 */

public class DownLoad implements Serializable {

    private static final int START = 1;                 // 开始下载
    private static final int PUBLISH = 2;               // 更新进度
    private static final int ERROR = 3;                 // 下载错误
    private static final int SUCCESS = 4;               // 下载成功

    private OnDownloadListener mListener;               // 监听器

    private static ExecutorService mThreadPool;         // 线程池

    static {
        mThreadPool = Executors.newFixedThreadPool(3);  // 默认3个
    }

    private static final String path = Environment.getExternalStorageDirectory().getPath() + "/aaa/";

    /**
     * 添加下载任务
     */
    public DownLoad(List<Bean> list, OnDownloadListener listener) {
        mListener = listener;
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case START:
                        mListener.onStart((Bean) msg.obj, msg.arg1);
                        break;
                    case PUBLISH:
                        mListener.onPublish((Bean) msg.obj, msg.arg1);
                        break;
                    case ERROR:
                        mListener.onError((Bean) msg.obj);
                        break;
                    case SUCCESS:
                        mListener.onSuccess((Bean) msg.obj);
                        break;
                }
            }
        };
        for (Bean bean : list) {
            start(bean, handler);
        }
    }


    /**
     * 开始下载
     *
     * @param bean
     * @param handler
     */
    public synchronized void start(Bean bean, Handler handler) {
        mThreadPool.execute(new IRunnable(bean, handler));
    }

    /**
     * 关闭下载线程池
     */
    public static void closeDownloadThread() {
        if (null != mThreadPool) {
            mThreadPool.shutdownNow();
        }
    }

    class IRunnable implements Runnable {

        Bean bean;
        Handler handler;

        public IRunnable(Bean bean, Handler handler) {
            this.bean = bean;
            this.handler = handler;
        }

        @Override
        public void run() {
            download(bean, handler);
        }
    }

    /**
     * 下载方法
     *
     * @param bean
     * @param handler
     */
    private void download(final Bean bean, final Handler handler) {
        Message msg = null;
        HttpURLConnection mHttpURLConnection = null;
        FileOutputStream fos = null;
        try {
            URL mURL = new URL(bean.getUrl());
            mHttpURLConnection = (HttpURLConnection) mURL.openConnection();
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setConnectTimeout(3000);

            msg = handler.obtainMessage();
            msg.what = START;
            msg.obj = bean;

            int iResponseCode = mHttpURLConnection.getResponseCode();
            if (iResponseCode == 200) {
                int filesize = mHttpURLConnection.getContentLength();

                msg.arg1 = filesize;
                handler.sendMessage(msg);

                InputStream in = mHttpURLConnection.getInputStream();
                fos = new FileOutputStream(new File(bean.getPath() + bean.getAdid() + ".zip"));
                byte[] bytes = new byte[1024 * 4];
                int len = -1;
                int current = 0;
                while (-1 != (len = in.read(bytes))) {
                    fos.write(bytes, 0, len);
                    current += len;
                    msg = handler.obtainMessage();
                    msg.what = PUBLISH;
                    msg.obj = bean;
                    msg.arg1 = ((int) (current * 100L / filesize));
                    handler.sendMessage(msg);
                }
                fos.flush();
                mHttpURLConnection.disconnect();

                msg = handler.obtainMessage();
                msg.what = SUCCESS;
                msg.obj = bean;
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
            // 发送下载错误的消息
            msg = handler.obtainMessage();
            msg.what = ERROR;
            msg.obj = bean;
            handler.sendMessage(msg);
        }
    }


}
