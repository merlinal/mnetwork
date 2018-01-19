package com.merlin.network.retrofit;


import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ncm on 16/12/9.
 */

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
//        HashMap<String, String> headers = NetWorker.inst().getHttpConfig().getHeaders();
//        for (String key : headers.keySet()) {
//            builder.addHeader(key, headers.get(key));
//        }
        return chain.proceed(builder.build());
    }

}
