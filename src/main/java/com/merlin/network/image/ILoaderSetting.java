package com.merlin.network.image;

import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Administrator on 2018/2/12.
 */

public interface ILoaderSetting {

    ILoaderSetting thumbnail(float sizeMultiplier);

    ILoaderSetting load(@Nullable String string);

    ILoaderSetting load(@Nullable Uri uri);

    ILoaderSetting load(@Nullable File file);

    ILoaderSetting load(@RawRes @DrawableRes @Nullable Integer resourceId);

    ILoaderSetting override(int size);

    ILoaderSetting override(int width, int height);

    ILoaderSetting centerInside();

    ILoaderSetting optionalCenterCrop();

    ILoaderSetting centerCrop();

    ILoaderSetting optionalFitCenter();

    ILoaderSetting fitCenter();

    ILoaderSetting optionalCenterInside();

    ILoaderSetting optionalCircleCrop();

    ILoaderListener into(@Nullable ImageView target);


}
