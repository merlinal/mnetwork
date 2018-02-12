package com.merlin.network.http;

/**
 * @author merlin
 */

public interface IResponse<T> {

    /**
     * 成功回调
     *
     * @param t
     */
    void onSuccess(T t);

    /**
     * 失败回调
     *
     * @param code 返回编码
     * @param msg  消息
     */
    void onFailure(String code, String msg);

    /**
     * 请求执行之前
     */
    void onPreExecute();

    /**
     * 请求进度
     *
     * @param total    全部
     * @param finished 完成
     */
    void onProgress(long total, long finished);

}
