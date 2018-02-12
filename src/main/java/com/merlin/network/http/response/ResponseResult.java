package com.merlin.network.http.response;

import com.merlin.network.http.IResponse;

/**
 * @author merlin
 */

public abstract class ResponseResult<T> implements IResponse<Result<T>> {

    @Override
    public void onSuccess(Result<T> result) {
        if (result == null) {
            onFailure(null, null);
        }
        if (result.getCode() == null && result.getData() != null) {
            onResultSuccess(result.getData());
        }
        if (result.isSuccess()) {
            onResultSuccess(result.getData());
        } else {
            onFailure(result.getCode(), result.getMessage());
        }
    }

    @Override
    public void onFailure(String code, String msg) {

    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onProgress(long total, long finished) {

    }

    public abstract void onResultSuccess(T t);

}
