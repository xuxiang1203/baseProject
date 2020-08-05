package com.xuxiang.other.base;

public interface BaseView<T> {

    void onSuccess(T o);

    void onError(int code, String errorMsg);

    void showLoading();

    void hideLoading();
}
