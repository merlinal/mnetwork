package com.merlin.network.http.cache;

import android.os.AsyncTask;

import com.merlin.core.util.MLog;
import com.merlin.core.util.MVerify;
import com.merlin.network.http.HttpMethod;
import com.merlin.network.http.IRequest;
import com.merlin.network.http.MRequest;
import com.merlin.network.http.NetTool;
import com.merlin.network.http.NetWorker;


/**
 * Created by ncm on 16/12/15.
 */

public class CacheTask extends AsyncTask<String, Void, String> implements IRequest {

    private MRequest request;
    private IHttpCache iHttpCache;

    public CacheTask(MRequest request, IHttpCache iHttpCache) {
        this.request = request;
        this.iHttpCache = iHttpCache;
    }

    @Override
    protected String doInBackground(String... strings) {
        if (request.getMethod() == HttpMethod.GET) {
            return iHttpCache.get(request.getKey());
        } else if (request.getMethod() == HttpMethod.DOWNLOAD) {
            return iHttpCache.getFilePath(request.getFilePath(), request.getCacheTime());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (MVerify.isBlank(result)) {
            request.cacheTime(0);
            NetWorker.inst().execute(request);
        } else {
            MLog.e("from cache ... " + result);
            request.requestHandle(this);
            NetWorker.inst().progress().updateProgress(100, 100);
            if (request.getMethod() == HttpMethod.GET) {
                NetTool.parseJson(result, request.getResponseType(), request.getListener());
            } else if (request.getMethod() == HttpMethod.DOWNLOAD) {
                request.getListener().onSuccess(null);
            }
        }
    }

    @Override
    public void cancel() {
        cancel(true);
    }

}
