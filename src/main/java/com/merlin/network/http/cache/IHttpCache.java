package com.merlin.network.http.cache;

/**
 * @author merlin
 */

public interface IHttpCache {

    /**
     * 加载缓存
     */
    void loadCache();

    /**
     * 获取缓存数据,主要是get获取的数据
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 获取缓存文件路径,主要书download的文件
     *
     * @param filePath
     * @param lifeTime
     * @return
     */
    String getFilePath(String filePath, long lifeTime);

    /**
     * 数据添加到缓存
     *
     * @param key
     * @param content
     * @param lifeTime
     */
    void add(String key, String content, long lifeTime);

    /**
     * 清除缓存
     */
    void clear();

}
