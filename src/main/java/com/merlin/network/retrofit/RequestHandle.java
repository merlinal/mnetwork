package com.merlin.network.retrofit;

import com.merlin.core.util.MLog;
import com.merlin.network.http.IRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by ncm on 16/12/9.
 */

public class RequestHandle implements IRequest {

    public Call<ResponseBody> call;

    public RequestHandle(Call<ResponseBody> call) {
        this.call = call;
    }

    @Override
    public void cancel() {
        MLog.e("************* cancel request");
        if (!call.isCanceled()) {
            call.cancel();
        }
    }

}
