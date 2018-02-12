package com.merlin.network.http.response;

import com.merlin.network.http.HttpStatus;

/**
 * Created by ncm on 16/12/9.
 */

public class Result<T> {
    private String code;
    private String message;
    private Error error;
    private T data;

    public boolean isSuccess(){
        return code != null && code.equals(HttpStatus.OK);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
