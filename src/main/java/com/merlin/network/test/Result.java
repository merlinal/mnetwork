package com.merlin.network.test;

/**
 * Created by ncm on 16/12/9.
 */

public class Result<T> {
    private int status_code;
    private String message;
    private Error error;
    private T data;
    private int member_status;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
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

    public int getMemberStatus() {
        return member_status;
    }

    public void setMemberStatus(int member_status) {
        this.member_status = member_status;
    }
}
