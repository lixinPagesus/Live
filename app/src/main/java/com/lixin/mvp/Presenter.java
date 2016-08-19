package com.lixin.mvp;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
