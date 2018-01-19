package com.merlin.network.image;

import android.widget.ImageView;

/**
 * Created by ncm on 16/12/22.
 */

public class MLoader extends ImageParam<MLoader> {

    public MLoader() {
    }

    public MLoader(ImageView imageView) {
        this.imageView = imageView;
    }

    private ImageView imageView;

    public MLoader setImageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    @Override
    protected MLoader getT() {
        return this;
    }

    public void show() {
        checkParams();
        ImageWorker.inst().display(this);
    }

    private void checkParams() {
        if (imageView != null) {
            if (getWidth() == 0) {
                setWidth(imageView.getWidth());
            }
            if (getHeight() == 0) {
                setHeight(imageView.getHeight());
            }
        }
    }

}
