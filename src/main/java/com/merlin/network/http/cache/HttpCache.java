package com.merlin.network.http.cache;

import com.merlin.core.context.MContext;
import com.merlin.core.worker.FileWorker;
import com.merlin.core.util.MLog;

import java.io.File;

/**
 * @author merlin
 */

public class HttpCache implements IHttpCache {

    private IHttpMemoryCache iMemoryCache;
    private IHttpDiskCache iDiskCache;

    public HttpCache() {
        iMemoryCache = new HttpMemoryCache();
        iMemoryCache.init();

        iDiskCache = new HttpDiskCache();
        iDiskCache.init();

        loadCache();
    }

    @Override
    public void loadCache() {
        File[] cacheFiles = iDiskCache.loadCache();
        if (cacheFiles != null && cacheFiles.length > 0) {
            for (File file : cacheFiles) {
                if (iMemoryCache.freeSize() < file.getTotalSpace()) {
                    break;
                }
                if (!file.getName().startsWith(MContext.appName())) {
                    continue;
                }
                CacheContent content = iDiskCache.getCacheContentFromFile(file.getPath());
                if (content != null) {
                    iMemoryCache.add(content.getKey(), content);
                }
            }
        }
    }

    @Override
    public String get(String key) {
        CacheContent content = iMemoryCache.get(key);
        if (content == null) {
            //从文件中读取
            content = iDiskCache.get(key);
        }
        if (content == null || !content.isAlive()) {
            return null;
        }
        return content.getContent();
    }

    @Override
    public String getFilePath(String filePath, long lifeTime) {
        if (FileWorker.inst().existFile(filePath)) {
            File file = new File(filePath);
            MLog.i("【HTTP】HttpCache getFilePath ---- " + file.lastModified() + " / " + lifeTime + " / " + System.currentTimeMillis());
            if (file.lastModified() + lifeTime > System.currentTimeMillis()) {
                return filePath;
            }
        }
        return null;
    }

    @Override
    public void add(String key, String content, long lifeTime) {
        CacheContent cacheContent = new CacheContent(key, 0, lifeTime, iDiskCache.buildFilePath(key), content);
        iMemoryCache.add(key, cacheContent);
        iDiskCache.add(cacheContent);
    }

    @Override
    public void clear() {
        iMemoryCache.clear();
        iDiskCache.clear();
    }

}
