package com.merlin.network.http.cache;

import com.google.gson.reflect.TypeToken;
import com.merlin.core.context.MContext;
import com.merlin.core.util.MGson;
import com.merlin.core.worker.FileWorker;
import com.merlin.core.util.MVerify;

import java.io.File;

/**
 * @author merlin
 */

public class HttpDiskCache implements IHttpDiskCache {

    public static final String CACHE_FOLDER = "httpCache";

    private String cachePath;

    @Override
    public void init(String... cachePath) {
        if (cachePath != null && cachePath.length > 0 && MVerify.isBlank(cachePath[0])) {
            this.cachePath = cachePath[0];
        } else {
            this.cachePath = FileWorker.inst().newFolder(CACHE_FOLDER);
        }
    }

    @Override
    public File[] loadCache() {
        return new File(cachePath).listFiles();
    }

    @Override
    public CacheContent get(String key) {
        CacheContent cacheContent = null;
        File[] cacheFiles = loadCache();
        if (cacheFiles != null && cacheFiles.length > 0) {
            for (File file : cacheFiles) {
                if (!file.getName().equals(key)) {
                    continue;
                }
                if ((cacheContent = getCacheContentFromFile(file.getPath())) != null) {
                    break;
                }
            }
        }
        return cacheContent;
    }

    @Override
    public void add(CacheContent content) {
        FileWorker.inst().save(content.getPath(), MGson.toJson(content));
    }

    @Override
    public void clear() {
        if (MVerify.isBlank(cachePath)) {
            cachePath = FileWorker.inst().newFolder(CACHE_FOLDER);
        }
        FileWorker.inst().remove(cachePath);
    }

    @Override
    public CacheContent getCacheContentFromFile(String filePath) {
        if (MVerify.isBlank(filePath)) {
            return null;
        }
        String fileContent = FileWorker.inst().getString(filePath);
        if (MVerify.isBlank(fileContent)) {
            return null;
        }
        CacheContent cacheContent = MGson.toObject(fileContent, new TypeToken<CacheContent>() {
        }.getType());
        if (cacheContent == null || !cacheContent.isAlive()) {
            return null;
        }
        return cacheContent;
    }

    @Override
    public String buildFilePath(String key) {
        return cachePath + File.separator + getFileName(key);
    }

    private String getFileName(String key) {
        return MContext.appName() + key;
    }

}
