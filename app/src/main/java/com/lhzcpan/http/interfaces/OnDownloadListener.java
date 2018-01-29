package com.lhzcpan.http.interfaces;

import com.lhzcpan.http.Bean;

/**
 * @author master
 * @date 2017/12/20
 */

public interface OnDownloadListener {
    /**
     * 开始下载
     *
     * @param id       标识值
     * @param fileSize 需要下载的文件长度
     */
    void onStart(Bean id, int fileSize);

    /**
     * 更新进度
     *
     * @param id      标识值
     * @param percent 当前下载量
     */
    void onPublish(Bean id, int percent);

    /**
     * 下载成功
     *
     * @param id 标识值
     */
    void onSuccess(Bean id);

    /**
     * 下在失败
     *
     * @param id 标识值
     */
    void onError(Bean id);
}
