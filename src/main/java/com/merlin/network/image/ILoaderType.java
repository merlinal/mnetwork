package com.merlin.network.image;

/**
 * Created by Administrator on 2018/2/12.
 */

public interface ILoaderType {

    ILoaderSetting asGif();

    ILoaderSetting asBitMap();

    ILoaderSetting asDrawable();

    ILoaderSetting asFile();

    ILoaderSetting load(Object o);

}
