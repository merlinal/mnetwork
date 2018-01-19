package com.merlin.network.http;

import java.util.HashMap;

/**
 * Created by ncm on 16/12/8.
 */

public interface IMethod<T> {

    T get(String url, HashMap map);

    T post(String url, HashMap map);

    T patch(String url, HashMap map);

    T put(String url, HashMap map);

    T delete(String url, HashMap map);

    T upload(MRequest request);

    T download(String url, HashMap map);

}
