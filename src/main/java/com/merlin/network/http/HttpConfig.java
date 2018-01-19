package com.merlin.network.http;

import android.util.SparseArray;

import com.merlin.network.OnConfigChangedListener;

import java.util.HashMap;

/**
 * Created by ncm on 16/12/9.
 */

public class HttpConfig {

    private String baseUrl;
    private long connectTimeout;
    private long readTimeout;
    private long writeTimeout;
    private HashMap<String, String> headers;
    private SparseArray<String> statusMessage;
    private long cacheTime;

    private long progressMaxTime = 30 * 1000L;
    private long progressMinTime = 1000L;
    private long progressFileMaxTime = 10 * 60 * 1000L;
    private long progressFileMinTime = 5 * 60 * 1000L;

    public HttpConfig() {
        this.connectTimeout = 30000L;
        this.readTimeout = 15000L;
        this.writeTimeout = 15000L;
        this.headers = new HashMap<>();
        this.baseUrl = "";
        this.statusMessage = new SparseArray<>();
        this.cacheTime = 30 * 60 * 1000;
        this.progressMaxTime = 30 * 1000L;
        this.progressMinTime = 1000L;
        this.progressFileMaxTime = 10 * 60 * 1000L;
        this.progressFileMinTime = 5 * 60 * 1000L;
        addHeaderDefault();
        addStatusDefault();
    }

    private void addHeaderDefault() {
        header(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
        header(HTTP.USER_AGENT, "");
    }

    private void addStatusDefault() {
        status(HttpStatus.NOT_FOUND, "请求不存在");
        status(HttpStatus.SERVER_ERROR, "服务器异常");
        status(HttpStatus.SERVICE_AVAILABLE, "服务不可用");
        status(HttpStatus.EMPTY, "返回数据为空");
        status(HttpStatus.UNPARSE, "返回数据未知");
        status(HttpStatus.EXCEPTION, "返回数据异常");
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

    public HttpConfig status(int status, String message) {
        this.statusMessage.put(status, message);
        return this;
    }

    public HttpConfig progressMaxTime(long progressMaxTime) {
        this.progressMaxTime = progressMaxTime;
        return this;
    }

    public HttpConfig progressMinTime(long progressMinTime) {
        this.progressMinTime = progressMinTime;
        return this;
    }

    public HttpConfig progressFileMaxTime(long progressFileMaxTime) {
        this.progressFileMaxTime = progressFileMaxTime;
        return this;
    }

    public HttpConfig progressFileMinTime(long progressFileMinTime) {
        this.progressFileMinTime = progressFileMinTime;
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

    public String getMessage(int status) {
        return this.statusMessage.get(status);
    }

    public long getProgressMaxTime() {
        return progressMaxTime;
    }

    public long getProgressMinTime() {
        return progressMinTime;
    }

    public long getProgressFileMaxTime() {
        return progressFileMaxTime;
    }

    public long getProgressFileMinTime() {
        return progressFileMinTime;
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
