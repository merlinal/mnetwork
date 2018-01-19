package com.merlin.network.http.progress;

import android.content.Context;

/**
 * Created by ncm on 17/2/9.
 */

public interface IProgress {

    void showProgress(Context context, ProgressVo progress);

    void hideProgress();

    void updateProgress(long total, long finished);

}
