package com.merlin.network.http.cache;

import java.io.File;

/**
 * Created by ncm on 17/2/9.
 */

public interface IHttpDiskCache {

    void init(String... cachePath);

    File[] loadCache();

    CacheContent get(String key);

    void add(CacheContent cacheContent);

    void clear();

    /**
     * 生成缓存文件的path
     *
     * @param key
     * @return
     */
    String buildFilePath(String key);

    /**
     * 从文件中获取CacheContent
     *
     * @param filePath
     * @return
     */
    CacheContent getCacheContentFromFile(String filePath);
}
