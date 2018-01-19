package com.merlin.network.http.cache;


import com.merlin.core.worker.FileWorker;
import com.merlin.core.util.MVerify;
import com.merlin.network.http.NetWorker;

/**
 * Created by ncm on 16/12/15.
 */

public class CacheContent {

    private String key;
    private long modifyTime;
    private long lifeTime;
    private String path;
    private String content;

    public CacheContent(String key, long modifyTime, long lifeTime, String path, String content) {
        this.key = key;
        this.modifyTime = modifyTime > 0 ? modifyTime : System.currentTimeMillis();
        this.lifeTime = lifeTime > 0 ? lifeTime : NetWorker.inst().config().getCacheTime();
        this.content = content;
        this.path = path;
    }

    public int size() {
        StringBuffer sb = new StringBuffer();
        if (!MVerify.isBlank(key)) {
            sb.append(key);
        }
        sb.append(modifyTime).append(lifeTime);
        if (!MVerify.isBlank(path)) {
            sb.append(path);
        }
        if (!MVerify.isBlank(content)) {
            sb.append(content);
        }
        return sb.toString().getBytes().length;
    }

    public boolean isAlive() {
        boolean isAlive = modifyTime + lifeTime > System.currentTimeMillis();
        if (!isAlive) {
            remove();
        }
        return isAlive;
    }

    private void remove() {
        FileWorker.inst().remove(path);
    }

    public String getKey() {
        return key;
    }

    public String getContent() {
        return content;
    }

    public String getPath() {
        return path;
    }

}
