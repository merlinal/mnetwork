package com.merlin.network.http;

/**
 * @author merlin
 */

public interface IRequest {

    /**
     * 取消
     */
    void cancel();

    /**
     * 是否已取消
     */
    boolean isCanceled();

}
