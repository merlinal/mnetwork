package com.merlin.network.image;

import android.graphics.Bitmap;

/**
 * common_params
 * Created by ncm on 16/12/22.
 */

public abstract class ImageParam<T> {

    private int resLoading;
    private int resError;
    private boolean memoryCache;
    private int width;
    private int height;
    private Bitmap.Config config;
    private Bitmap.CompressFormat format;
    private Object tag;
    private String baseUrl;

    protected abstract T getT();

    public T setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return getT();
    }

    public T setResLoading(int resLoading) {
        this.resLoading = resLoading;
        return getT();
    }

    public T setResError(int resError) {
        this.resError = resError;
        return getT();
    }

    public T setMemoryCache(boolean memoryCache) {
        this.memoryCache = memoryCache;
        return getT();
    }

    public T setWidth(int width) {
        this.width = width;
        return getT();
    }

    public T setHeight(int height) {
        this.height = height;
        return getT();
    }

    public T setConfig(Bitmap.Config config) {
        this.config = config;
        return getT();
    }

    public T setTag(Object tag) {
        this.tag = tag;
        return getT();
    }

    public T setFormat(Bitmap.CompressFormat format) {
        this.format = format;
        return getT();
    }

    public int getResLoading() {
        return resLoading;
    }

    public int getResError() {
        return resError;
    }

    public boolean isMemoryCache() {
        return memoryCache;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Bitmap.Config getConfig() {
        return config;
    }

    public Object getTag() {
        return tag;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Bitmap.CompressFormat getFormat() {
        return format;
    }

}
