package com.merlin.network.http;

import java.util.HashMap;

/**
 * @author merlin
 */

public class HttpConfig {

    private String baseUrl;
    private long connectTimeout;
    private long readTimeout;
    private long writeTimeout;
    private HashMap<String, String> headers;
    private long cacheTime;

    public HttpConfig() {
        this.connectTimeout = 30000L;
        this.readTimeout = 15000L;
        this.writeTimeout = 15000L;
        this.headers = new HashMap<>();
        this.baseUrl = "http://www.baidu.com";
        this.cacheTime = 0;
        addHeaderDefault();
    }

    private void addHeaderDefault() {
        header(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
        header(HTTP.USER_AGENT, "");
    }

    public HttpConfig baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public HttpConfig connectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public HttpConfig readTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public HttpConfig writeTimeout(long whiteTimeout) {
        this.writeTimeout = whiteTimeout;
        return this;
    }

    public HttpConfig cacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
        return this;
    }

    public HttpConfig header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }

    private OnConfigChangedListener onConfigChangedListener;

    public void setOnConfigChangedListener(OnConfigChangedListener onConfigChangedListener) {
        this.onConfigChangedListener = onConfigChangedListener;
    }

    public void update() {
        if (onConfigChangedListener != null) {
            onConfigChangedListener.notifyChanged();
        }
    }

}
