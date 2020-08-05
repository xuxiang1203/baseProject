package com.xuxiang.other.base;

public interface Presenter<V> {
    void attachView(V view);

    void detachView(V view);

    String getName();
}
