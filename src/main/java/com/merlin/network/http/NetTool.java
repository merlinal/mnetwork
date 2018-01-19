package com.merlin.network.http;


import com.merlin.core.util.MGson;
import com.merlin.core.util.MVerify;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ncm on 16/12/12.
 */

public class NetTool {

    public static <T> void parseJson(String response, Type type, IResponse<T> iResponse) {
        if (iResponse == null) {
            return;
        }
        if (MVerify.isEmpty(response)) {
            iResponse.onFailure(HttpStatus.EMPTY, NetWorker.inst().config().getMessage(HttpStatus.EMPTY), null);
        } else {
            try {
                if (response.startsWith("{")) {
                    T t = MGson.toObject(response, type);
                    iResponse.onSuccess(t);
                } else if (response.startsWith("[")) {
                    List<T> ts = MGson.toList(response, type);
                    iResponse.onSuccess(ts);
                } else {
                    iResponse.onFailure(HttpStatus.UNPARSE, NetWorker.inst().config().getMessage(HttpStatus.UNPARSE), null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                iResponse.onFailure(HttpStatus.EXCEPTION, NetWorker.inst().config().getMessage(HttpStatus.EMPTY), e);
            }
        }
    }

}
