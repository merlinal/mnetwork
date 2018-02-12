package com.merlin.network.http;

import com.merlin.network.http.cache.HttpCache;
import com.merlin.network.http.cache.IHttpCache;
import com.merlin.network.retrofit.RetrofitClient;

/**
 * @author merlin
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

    /**
     * 网络请求
     */
    private IClient iClient;
    /**
     * 网络设置
     */
    private HttpConfig httpConfig;
    /**
     * 缓存
     */
    private IHttpCache iHttpCache;

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

    public IHttpCache cache(IHttpCache iHttpCache) {
        return this.iHttpCache = iHttpCache;
    }

    public IHttpCache cache() {
        return iHttpCache;
    }

    public MRequest execute(MRequest request) {
        return iClient.start(request);
    }

    public void clearCache() {
        iHttpCache.clear();
    }

}
