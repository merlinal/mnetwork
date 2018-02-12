package com.merlin.network.image;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.merlin.network.glide.GlideApp;
import com.merlin.network.glide.GlideRequest;
import com.merlin.network.glide.GlideRequests;

import java.io.File;

/**
 * @author merlin
 */

public class MImage implements ILoaderContext, ILoaderType, ILoaderSetting, ILoaderListener,
        ILoaderStatus, ILoaderTarget {

    public static ILoaderContext newInst() {
        return new MImage();
    }

    private GlideRequests glideRequests;
    private GlideRequest glideRequest;

    @Override
    public ILoaderType with(Activity activity) {
        glideRequests = GlideApp.with(activity);
        return this;
    }

    @Override
    public ILoaderType with(Context context) {
        glideRequests = GlideApp.with(context);
        return this;
    }

    @Override
    public ILoaderType with(FragmentActivity activity) {
        glideRequests = GlideApp.with(activity);
        return this;
    }

    @Override
    public ILoaderType with(Fragment fragment) {
        glideRequests = GlideApp.with(fragment);
        return this;
    }

    @Override
    public ILoaderType with(android.app.Fragment fragment) {
        glideRequests = GlideApp.with(fragment);
        return this;
    }

    //*****************************************************


    @Override
    public ILoaderSetting asGif() {
        glideRequest = glideRequests.asGif();
        return this;
    }

    @Override
    public ILoaderSetting asBitMap() {
        glideRequest = glideRequests.asBitmap();
        return this;
    }

    @Override
    public ILoaderSetting asDrawable() {
        glideRequest = glideRequests.asDrawable();
        return this;
    }

    @Override
    public ILoaderSetting asFile() {
        glideRequest = glideRequests.asFile();
        return this;
    }

    @Override
    public ILoaderSetting load(Object o) {
        glideRequest = glideRequests.load(o);
        return this;
    }

    //****************************************


    @Override
    public ILoaderSetting thumbnail(float sizeMultiplier) {
        return this;
    }

    @Override
    public ILoaderSetting load(@Nullable String string) {
        return this;
    }

    @Override
    public ILoaderSetting load(@Nullable Uri uri) {
        return this;
    }

    @Override
    public ILoaderSetting load(@Nullable File file) {
        return this;
    }

    @Override
    public ILoaderSetting load(@Nullable Integer resourceId) {
        return this;
    }

    @Override
    public ILoaderSetting override(int size) {
        return this;
    }

    @Override
    public ILoaderSetting override(int width, int height) {
        return this;
    }

    @Override
    public ILoaderSetting centerInside() {
        return this;
    }

    @Override
    public ILoaderSetting optionalCenterCrop() {
        return this;
    }

    @Override
    public ILoaderSetting centerCrop() {
        return this;
    }

    @Override
    public ILoaderSetting optionalFitCenter() {
        return this;
    }

    @Override
    public ILoaderSetting fitCenter() {
        return this;
    }

    @Override
    public ILoaderSetting optionalCenterInside() {
        return this;
    }

    @Override
    public ILoaderSetting optionalCircleCrop() {
        return this;
    }

    @Override
    public ILoaderListener into(@Nullable ImageView target) {
        return this;
    }
}
