package com.lixin.http;

/**
 * Created by lixin on 16/8/16.
 */
public interface ApiCallback<T> {

    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();

}
