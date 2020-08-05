package com.xuxiang.other.base;

import android.content.Context;

import com.xuxiang.other.CommonApplication;
import com.xuxiang.xxlib.dialog.LoadingUtil;

public interface BaseView<T> {

    void onSuccess(T o);

    void onError(int code, String errorMsg);

    default void showLoading(Context context) {
        LoadingUtil.showLoading(context);
    };

    default void hideLoading() {
        LoadingUtil.hide();
    };
}
