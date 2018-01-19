package com.merlin.network.image;

import android.graphics.Bitmap;

/**
 * Created by ncm on 16/12/22.
 */

public interface ILoader {

    void display(MLoader imageConfig);

    Bitmap getBitMap(MLoader imageConfig);

}
