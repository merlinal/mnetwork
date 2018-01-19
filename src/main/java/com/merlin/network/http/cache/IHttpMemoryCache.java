package com.merlin.network.http.cache;

/**
 * Created by ncm on 17/2/9.
 */

public interface IHttpMemoryCache {

    void init(int... maxSize);

    int freeSize();

    CacheContent get(String key);

    void add(String key, CacheContent cacheContent);

    void clear();

}
