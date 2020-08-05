package com.xuxiang.commonmvp.net.back;

import com.xuxiang.commonmvp.net.HttpConfig;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 用于网络请求数据的返回
 * @param <T>
 */
public abstract class HttpCallBack<T> implements Observer<BaseResponse<T>> {

    private final String TAG = "HttpCallBack";

    public abstract void onSuccess(T t);
    public abstract void onFailure(int code, String msg);

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        onFailure(-1, "from onError");
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if(response.getStatus() != HttpConfig.HTTP_SUCCESS) {
            onFailure(response.getStatus(), "from onNext");
            return;
        }
        onSuccess(response.getData());
    }
}


