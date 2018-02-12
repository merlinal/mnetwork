package com.merlin.network.http;

import com.merlin.core.util.MGson;
import com.merlin.core.util.MVerify;

import java.lang.reflect.Type;

/**
 * @author merlin
 */

public class NetTool {

    public static <T> void parseJson(String response, Type type, IResponse<T> iResponse) {
        if (iResponse == null) {
            return;
        }
        if (MVerify.isEmpty(response)) {
            iResponse.onFailure(HttpStatus.EMPTY, null);
        } else {
            try {
                if (type == null) {
                    iResponse.onSuccess((T) response);
                } else {
                    if (response.startsWith("{") || response.startsWith("[")) {
                        T t = MGson.toObject(response, type);
                        iResponse.onSuccess(t);
                    } else {
                        iResponse.onFailure(HttpStatus.UNPARSE, null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                iResponse.onFailure(HttpStatus.EXCEPTION, null);
            }
        }
    }

}
