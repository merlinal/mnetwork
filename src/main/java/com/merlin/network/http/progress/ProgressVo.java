package com.merlin.network.http.progress;

/**
 * Created by ncm on 17/2/9.
 */

public class ProgressVo {

    private boolean isShow;
    private String title;
    private String content;
    private long maxTime;
    private long minTime;
    private boolean showPercent;
    private boolean cancelable;

    public ProgressVo() {
        this(null, null, 0, 0, false, true);
        isShow = true;
    }

    public ProgressVo(String title, String content, long maxTime, long minTime, boolean showPercent, boolean cancelable) {
        this.title = title;
        this.content = content;
        this.maxTime = maxTime;
        this.minTime = minTime;
        this.showPercent = showPercent;
        this.cancelable = cancelable;
    }

    public String getTitle() {
        return title;
    }

    public ProgressVo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ProgressVo setContent(String content) {
        this.content = content;
        return this;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public ProgressVo setMaxTime(long maxTime) {
        this.maxTime = maxTime;
        return this;
    }

    public long getMinTime() {
        return minTime;
    }

    public ProgressVo setMinTime(long minTime) {
        this.minTime = minTime;
        return this;
    }

    public boolean isShowPercent() {
        return showPercent;
    }

    public ProgressVo setShowPercent(boolean showPercent) {
        this.showPercent = showPercent;
        return this;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public ProgressVo setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public boolean isShow() {
        return isShow;
    }

    public ProgressVo setShow(boolean show) {
        isShow = show;
        return this;
    }
}
