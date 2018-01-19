package com.merlin.network.test;

import com.merlin.core.util.MLog;
import com.merlin.core.util.MVerify;
import com.merlin.network.http.HttpStatus;
import com.merlin.network.http.IResponse;
import com.merlin.network.http.NetWorker;

import java.util.List;

/**
 * Created by ncm on 16/12/9.
 */

public abstract class NetResponse<T> implements IResponse<Result<T>> {

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onSuccess(Result<T> tResult) {
        if (tResult.getStatus_code() == HttpStatus.OK) {
            onOk(tResult.getData());
        } else {
            onFailure(tResult.getStatus_code(), tResult.getMessage(), null);
        }
    }

    @Override
    public void onSuccess(List<Result<T>> results) {
    }

    @Override
    public void onFailure(int code, String msg, Throwable t) {
        if (!MVerify.isBlank(msg)) {
            onFail(msg);
        }
        if (!MVerify.isNull(t)) {
            MLog.wtf(t);
        }
    }

    @Override
    public void onProgress(long total, long finished) {
        NetWorker.inst().progress().updateProgress(total, finished);
//        LogUtil.e(String.format("Progress %d from %d (%2.0f%%)", finished, total, (total > 0) ? (finished * 1.0 / total) * 100 : -1));
    }

    public abstract void onOk(T t);

    public void onFail(String msg) {
//        UiUtil.toast(msg);
    }


}
