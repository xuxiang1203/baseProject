package com.xuxiang.other.presenter;

import android.app.Activity;
import android.content.Context;

import com.xuxiang.other.base.BaseActivity;
import com.xuxiang.other.base.BaseFragment;
import com.xuxiang.other.base.BasePresenter;
import com.xuxiang.other.bean.AppUpdateBean;
import com.xuxiang.other.model.AppupdateImpl;
import com.xuxiang.other.net.back.HttpCallBack;
import com.xuxiang.other.ui.view.IUpdateView;

public class AppUpdatePresenter extends BasePresenter<IUpdateView> {

    AppupdateImpl impl;

    public AppUpdatePresenter() {
        impl = new AppupdateImpl();
    }

    public void getAppUpdateInfo(String version) {
        mvpView.showLoading(mvpView);
        impl.getUpdateInfo(version, new HttpCallBack<AppUpdateBean>() {
            @Override
            public void onSuccess(AppUpdateBean appUpdateBean) {
                mvpView.hideLoading();
                mvpView.onSuccess(appUpdateBean);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.onError(code, msg);
            }
        });
    }
}
