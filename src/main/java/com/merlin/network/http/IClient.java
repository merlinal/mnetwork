package com.merlin.network.http;

import com.merlin.core.util.MLog;
import com.merlin.core.util.MVerify;
import com.merlin.network.OnConfigChangedListener;
import com.merlin.network.http.cache.CacheTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ncm on 16/12/9.
 */

public abstract class IClient<T> implements IMethod<T> {

    public IClient addHttpConfig(HttpConfig config) {
        config.setOnConfigChangedListener(new OnConfigChangedListener() {
            @Override
            public void notifyChanged() {
                buildClient();
            }
        });
        buildClient();
        return this;
    }

    public abstract void buildClient();

    public <E> MRequest<E> start(MRequest<E> request) {
        MLog.i("Http start ... ");
        if (request.getMethod() != HttpMethod.UPLOAD && request.getMethod() != HttpMethod.DOWNLOAD) {
            if (request.getListener() != null) {
                request.getListener().onProgress(100L, 68L);
            }
        }
        //先获取缓存,若无缓存; 则从网络获取;
        if (!callCache(request)) {
            MLog.i("from server ... ");
            request.requestHandle(switchHandle(dispatch(request), request));
        }
        return request;
    }

    private T dispatch(MRequest request) {
        switch (request.getMethod()) {
            case HttpMethod.GET:
                return get(request.getUrl(), request.getParams());
            case HttpMethod.POST:
                return post(request.getUrl(), request.getParams());
            case HttpMethod.PUT:
                return put(request.getUrl(), request.getParams());
            case HttpMethod.DELETE:
                return delete(request.getUrl(), request.getParams());
            case HttpMethod.UPLOAD:
                return upload(request);
            case HttpMethod.DOWNLOAD:
                return download(request.getUrl(), request.getParams());
            case HttpMethod.PATCH:
                return patch(request.getUrl(), request.getParams());
            default:
                return null;
        }
    }

    public abstract IRequest switchHandle(T t, MRequest request);

    private <E> boolean callCache(final MRequest<E> request) {
        if (request.getMethod() != HttpMethod.GET && request.getMethod() != HttpMethod.DOWNLOAD) {
            return false;
        }
        if (request.getCacheTime() > 0) {
            new CacheTask(request, NetWorker.inst().cache()).execute();
            return true;
        }
        return false;
    }

    protected boolean saveFile(MRequest request, InputStream inputStream, long fileSize) {
        try {
            if (MVerify.isBlank(request.getFilePath())) {
                MLog.e("请指定文件存储路径...");
                return false;
            }
            File file = new File(request.getFilePath());
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSizeDownloaded = 0;
                outputStream = new FileOutputStream(file);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    if (request.getListener() != null) {
                        request.getListener().onProgress(fileSize, fileSizeDownloaded);
                    }
                }
                outputStream.flush();
                MLog.i("success for file download ... ");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

}
