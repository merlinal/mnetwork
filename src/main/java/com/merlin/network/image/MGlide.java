package com.merlin.network.image;

import android.widget.ImageView;

import com.merlin.core.context.MContext;
import com.merlin.network.glide.GlideApp;

/**
 * @author merlin
 */

public class MGlide {


    private void test() {
        GlideApp.with(MContext.app())
                .load("ss")
                .centerInside()
                .load("")
        .into(new ImageView(MContext.app()))
        ;

    }

}
