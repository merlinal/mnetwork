package com.merlin.network.image;

import android.graphics.Bitmap;

import com.merlin.network.picasso.PicassoLoader;


/**
 * Created by ncm on 16/12/22.
 */

public class ImageWorker {

    public static ImageWorker inst() {
        return InstHolder.imageWorker;
    }

    private static class InstHolder {
        private final static ImageWorker imageWorker = new ImageWorker();
    }

    private ImageWorker() {
    }

    private ILoader iLoader;
    private MLoader imageConfig;

    public void init(MLoader imageConfig) {
        iLoader = new PicassoLoader();
        this.imageConfig = imageConfig;
    }

    public void display(MLoader imageConfig) {
        iLoader.display(imageConfig);
    }

    public Bitmap getBitMap(MLoader imageConfig) {
        return iLoader.getBitMap(imageConfig);
    }

}
