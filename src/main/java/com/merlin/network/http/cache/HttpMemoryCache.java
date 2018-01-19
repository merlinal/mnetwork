package com.merlin.network.http.cache;

import android.support.v4.util.LruCache;

/**
 * Created by ncm on 17/2/9.
 */

public class HttpMemoryCache implements IHttpMemoryCache {

    private int cacheSize = 0;  //byte
    private LruCache<String, CacheContent> cache;

    @Override
    public void init(int... maxSize) {
        //缓存容量
        if (maxSize != null && maxSize.length > 0 && maxSize[0] > 0) {
            cacheSize = maxSize[0];
        } else {
            cacheSize = (int) Runtime.getRuntime().maxMemory() / 16;
        }
        //缓存容器
        cache = new LruCache<String, CacheContent>(cacheSize) {
            @Override
            protected int sizeOf(String key, CacheContent value) {
                return value.size();
            }
        };
    }

    @Override
    public int freeSize() {
        return cacheSize - cache.size();
    }


    @Override
    public CacheContent get(String key) {
        return cache.get(key);
    }

    @Override
    public void add(String key, CacheContent cacheContent) {
        cache.put(key, cacheContent);
    }

    @Override
    public void clear() {
        if (cache != null) {
            cache.evictAll();
        }
    }

}
