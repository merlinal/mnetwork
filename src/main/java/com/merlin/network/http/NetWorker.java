package com.merlin.network.http;

import com.merlin.network.http.cache.HttpCache;
import com.merlin.network.http.cache.IHttpCache;
import com.merlin.network.http.progress.IProgress;
import com.merlin.network.retrofit.RetrofitClient;

/**
 * Created by ncm on 16/12/8.
 */

public class NetWorker {

    public static NetWorker inst() {
        return InstHolder.netWorker;
    }

    private static class InstHolder {
        private static final NetWorker netWorker = new NetWorker();
    }

    private NetWorker() {
        iClient = RetrofitClient.inst();
        httpConfig = new HttpConfig();
        iHttpCache = new HttpCache();
    }

    private IClient iClient;  //网络请求
    private HttpConfig httpConfig;  //网络设置
    private IProgress iProgress;  //进度条
    private IHttpCache iHttpCache;  //缓存

    public NetWorker build() {
        iClient.addHttpConfig(httpConfig);
        return inst();
    }

    public HttpConfig config(HttpConfig httpConfig) {
        return this.httpConfig = httpConfig;
    }

    public HttpConfig config() {
        return httpConfig;
    }

    public IProgress progress(IProgress iProgress) {
        return this.iProgress = iProgress;
    }

    public IProgress progress() {
        return iProgress;
    }

    public IHttpCache cache(IHttpCache iHttpCache) {
        return this.iHttpCache = iHttpCache;
    }

    public IHttpCache cache() {
        return iHttpCache;
    }

    public MRequest execute(MRequest request) {
        iProgress.showProgress(request.getContext(), request.getProgress());
        return iClient.start(request);
    }

    public void clearCache() {
        iHttpCache.clear();
    }

}
