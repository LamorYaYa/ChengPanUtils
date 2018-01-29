package com.lhzcpan.http.interfaces;

/**
 * @author master
 * @date 2017/11/8
 */

public interface OnHttpListener {
    /**
     * 网络请求完成
     *
     * @param response 响应内容
     */
    void onFinish(String response);

    /**
     * 网络请求失败
     *
     * @param e 异常信息
     */
    void onError(Exception e);
}
