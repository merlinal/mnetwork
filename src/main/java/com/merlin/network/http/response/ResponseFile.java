package com.merlin.network.http.response;

import com.merlin.network.http.IResponse;

/**
 * @author merlin
 */

public abstract class ResponseFile implements IResponse<Integer> {

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onFailure(String code, String msg) {

    }

    @Override
    public void onProgress(long total, long finished) {

    }

}
