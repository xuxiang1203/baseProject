package com.xuxiang.other.base;
import com.xuxiang.xxlib.dialog.LoadingUtil;

public interface BaseView<T> {

    void onSuccess(T o);

    void onError(int code, String errorMsg);

    default void showLoading(Object context) {
        if(context instanceof BaseActivity) {
            LoadingUtil.showLoading((BaseActivity)context);
        } else if(context instanceof BaseFragment){
            LoadingUtil.showLoading(((BaseFragment) context).getContext());
        }
    }

    default void hideLoading() {
        LoadingUtil.hide();
    }
}
