package com.merlin.network.http;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.merlin.core.util.MGson;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * @author merlin
 */

public class MRequest {

    /**
     * 上下文（显示进度需要）
     */
    private Activity context;
    private int method;
    private String url;
    /**
     * 参数
     */
    private HashMap<String, String> params;
    /**
     * 返回的数据类型
     */
    private Type responseType;
    /**
     * 请求响应器
     */
    private IResponse listener;
    /**
     * 缓存时间，单位ms
     */
    private long cacheTime;
    /**
     * 请求控制器
     */
    private IRequest iRequest;
    /**
     * 上传文件地址
     */
    private HashMap<String, String> files;
    /**
     * 下载位置 download
     */
    private String filePath;
    /**
     * 请求是否已取消
     */
    private boolean isCancel = false;

    public static MRequest newInstance(Activity activity) {
        return new MRequest(activity);
    }

    public static MRequest newInstance(Fragment fragment) {
        return new MRequest(fragment.getActivity());
    }

    private MRequest(Activity activity) {
        this.context = activity;
        this.cacheTime = NetWorker.inst().config().getCacheTime();
        params = new HashMap<>();
        files = new HashMap<>();
    }

    public Type getResponseType() {
        return responseType;
    }

    public int getMethod() {
        return method;
    }

    public MRequest get(String url) {
        this.url = url;
        this.method = HttpMethod.GET;
        return this;
    }

    public MRequest post(String url) {
        this.url = url;
        this.method = HttpMethod.POST;
        return this;
    }

    public MRequest patch(String url) {
        this.url = url;
        this.method = HttpMethod.PATCH;
        return this;
    }

    public MRequest download(String url) {
        this.url = url;
        this.method = HttpMethod.DOWNLOAD;
        return this;
    }

    public MRequest upload(String url) {
        this.url = url;
        this.method = HttpMethod.UPLOAD;
        return this;
    }

    public MRequest type(Type responseType) {
        this.responseType = responseType;
        return this;
    }

    public MRequest params(String key, Object value) {
        if (value instanceof String) {
            this.params.put(key, (String) value);
        } else {
            this.params.put(key, MGson.toJson(value));
        }
        return this;
    }

    public MRequest filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public MRequest files(String key, String filePath) {
        files.put(key, filePath);
        return this;
    }

    public <E> MRequest listener(IResponse<E> listener) {
        this.listener = listener;
        return this;
    }

    public MRequest cacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
        return this;
    }

    public MRequest execute() {
        NetWorker.inst().execute(this);
        return this;
    }

    /**
     * 取消请求
     */
    public void cancel() {
        if (iRequest != null && !isCancel) {
            iRequest.cancel();
            isCancel = true;
        }
    }

    public boolean isCancel() {
        return isCancel;
    }

    public Activity getContext() {
        return context;
    }


    public String getUrl() {
        return url;
    }

    public IResponse getListener() {
        return listener;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<String, String> getFiles() {
        return files;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public void requestHandle(IRequest iRequest) {
        this.iRequest = iRequest;
    }

    public String getKey() {
        return String.valueOf((url + params.hashCode() + method).hashCode());
    }

}
