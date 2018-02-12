package com.merlin.network.image;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Administrator on 2018/2/12.
 */

public interface ILoaderContext {

    ILoaderType with(Activity activity);

    ILoaderType with(Context context);

    ILoaderType with(android.support.v4.app.FragmentActivity activity);

    ILoaderType with(android.support.v4.app.Fragment fragment);

    ILoaderType with(android.app.Fragment fragment);

}
