package com.merlin.network.test;

import com.merlin.core.util.MLog;
import com.merlin.core.util.MVerify;
import com.merlin.network.http.IResponse;
import com.merlin.network.http.NetWorker;

import java.util.List;

/**
 * Created by ncm on 16/12/19.
 */

public abstract class FileResponse implements IResponse<Integer> {

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onSuccess(Integer code) {
    }

    @Override
    public void onSuccess(List list) {
    }

    @Override
    public void onFailure(int code, String msg, Throwable t) {
        MLog.e("FileResponse onFailure");
        if (!MVerify.isBlank(msg)) {
            onFail(msg);
        }
        if (!MVerify.isNull(t)) {
            MLog.wtf(t);
        }
        NetWorker.inst().progress().hideProgress();
    }

    public abstract void onOk(int code);

    public void onFail(String msg) {
    }

    @Override
    public void onProgress(long total, long finished) {
        NetWorker.inst().progress().updateProgress(total, finished);
    }

}
