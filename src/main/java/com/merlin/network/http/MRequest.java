package com.merlin.network.http;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.merlin.core.util.MGson;
import com.merlin.network.http.progress.ProgressVo;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * @author merlin
 */

public class MRequest<T> {

    private Activity context;  //显示进度需要
    private int method;
    private String url;
    private HashMap<String, String> params;  //参数
    private Type responseType;  //返回的数据类型
    private IResponse listener;  //请求响应器
    private long cacheTime;  //ms
    private IRequest iRequest;  //请求控制器
    private HashMap<String, String> files;  //upload
    private String filePath;  //download
    private ProgressVo progress = null;  //进度条设置
    private boolean isCancel = false;  //请求是否已取消

    public MRequest(Activity activity) {
        this.context = activity;
        this.cacheTime = NetWorker.inst().config().getCacheTime();
        params = new HashMap<>();
        files = new HashMap<>();
        progress = new ProgressVo();
        get();  //默认get
    }

    public MRequest(Fragment fragment) {
        this(fragment.getActivity());
    }

    public Type getResponseType() {
        return responseType;
    }

    public int getMethod() {
        return method;
    }

    public MRequest<T> get() {
        this.method = HttpMethod.GET;
        httpSetting();
        return this;
    }

    public MRequest<T> post() {
        this.method = HttpMethod.POST;
        httpSetting();
        return this;
    }

    public MRequest<T> patch() {
        this.method = HttpMethod.PATCH;
        httpSetting();
        return this;
    }

    public MRequest<T> download() {
        this.method = HttpMethod.DOWNLOAD;
        fileSetting();
        return this;
    }

    public MRequest<T> upload() {
        this.method = HttpMethod.UPLOAD;
        fileSetting();
        return this;
    }

    public MRequest<T> type(Type responseType) {
        this.responseType = responseType;
        return this;
    }

    public MRequest<T> url(String url) {
        this.url = url;
        return this;
    }

    public MRequest<T> params(String key, Object value) {
        if (value instanceof String) {
            this.params.put(key, (String) value);
        } else {
            this.params.put(key, MGson.toJson(value));
        }
        return this;
    }

    public MRequest<T> filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public MRequest<T> files(String key, String filePath) {
        files.put(key, filePath);
        return this;
    }

    public <E> MRequest<T> listener(IResponse<E> listener) {
        this.listener = listener;
        return this;
    }

    public MRequest<T> cacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
        return this;
    }

    public MRequest<T> execute() {
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

    /**
     * 进度条设置
     */

    public MRequest<T> progress() {
        return this.progress(null, null, 0, 0, false, true);
    }

    public MRequest<T> progress(String title, String content) {
        return this.progress(title, content, 0, 0, false, true);
    }

    public MRequest<T> progress(String title, String content, long maxShowTime, long minShowTime, boolean showPercent, boolean cancelable) {
        progress.setTitle(title)
                .setContent(content)
                .setMaxTime(maxShowTime)
                .setMinTime(minShowTime)
                .setShowPercent(showPercent)
                .setCancelable(cancelable);
        return this;
    }

    public Activity getContext() {
        return context;
    }

    public ProgressVo getProgress() {
        return progress;
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

    //http默认设置
    private void httpSetting() {
        if (progress.getMaxTime() <= 0) {
            progress.setMaxTime(NetWorker.inst().config().getProgressMaxTime());
        }
        if (progress.getMinTime() <= 0) {
            progress.setMinTime(NetWorker.inst().config().getProgressMinTime());
        }
        progress.setShowPercent(false);
    }

    //上传&下载 默认设置
    private void fileSetting() {
        if (progress.getMaxTime() <= 0) {
            progress.setMaxTime(NetWorker.inst().config().getProgressFileMaxTime());
        }
        if (progress.getMinTime() <= 0) {
            progress.setMinTime(NetWorker.inst().config().getProgressFileMinTime());
        }
        progress.setShowPercent(true);
    }

}
