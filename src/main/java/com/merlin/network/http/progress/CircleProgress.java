package com.merlin.network.http.progress;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.os.Handler;
//
//import com.merlin.http.R;
//
//import okhttp3.internal.Util;
//
///**
// * Created by ncm on 17/2/9.
// */
//
//public class CircleProgress implements IProgress {
//
//    private Dialog progressDialog;
//    private MCircleProgressView mCircleProgressView;
//    private long startTime, minTime = 1000l;  //ms
//    private Handler handler = new Handler();
//
//    @Override
//    public void showProgress(Context context, ProgressVo progress) {
//        if (context == null) {
//            return;
//        }
//        this.minTime = Math.max(this.minTime, progress.getMinTime());
//        if (progressDialog == null) {
//            mCircleProgressView = (MCircleProgressView) Util.inflater().inflate(R.layout.view_circle_progress, null);
//            mCircleProgressView.setTitle(progress.getTitle());
//            mCircleProgressView.setContent(progress.getContent());
//            mCircleProgressView.showPercent(progress.isShowPercent());
//            mCircleProgressView.setOnCounterFinishedListener(new CounterView.OnCounterFinishedListener() {
//                @Override
//                public void onCountFinished(float nowValue) {
//                    LogUtil.e("***** onCountFinished");
//                    progressDialog.dismiss();
//                }
//            });
//            progressDialog = UiUtil.showDialog(context, mCircleProgressView, 0, 0, 0, 0, 0, R.style.anim_progress, 0, progress.isCancelable());
//            progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialogInterface) {
//                    mCircleProgressView.reset();
//                }
//            });
//        } else {
//            progressDialog.show();
//        }
//        startTime = System.currentTimeMillis();
//        hideProgress(progress.getMaxTime() > 0 ? progress.getMaxTime() : 30000l);
//    }
//
//    @Override
//    public void hideProgress() {
//        if (progressDialog != null && progressDialog.isShowing()) {
//            if (System.currentTimeMillis() - startTime > minTime) {
//                LogUtil.e("***** minTime");
//                progressDialog.dismiss();
//            } else {
//                hideProgress(minTime - System.currentTimeMillis() + startTime);
//            }
//        }
//    }
//
//    @Override
//    public void updateProgress(long total, long finished) {
//        if (mCircleProgressView != null) {
//            mCircleProgressView.setProgress(total, finished);
//        }
//    }
//
//    private void hideProgress(long delayTime) {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                hideProgress();
//            }
//        }, delayTime);
//    }
//
//}
