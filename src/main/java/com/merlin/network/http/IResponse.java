package com.merlin.network.http;

import java.util.List;

/**
 * Created by ncm on 16/12/8.
 */

public interface IResponse<T> {

    void onPreExecute();

    void onSuccess(T t);

    void onSuccess(List<T> ts);

    void onFailure(int code, String msg, Throwable t);

    void onProgress(long total, long finished);

}
