package com.xuxiang.commonmvp.presenter;

import com.xuxiang.commonmvp.bean.AppUpdateBean;
import com.xuxiang.commonmvp.contract.AppUpdateContract;
import com.xuxiang.commonmvp.net.back.HttpCallBack;

public class AppUpdatePresenterImpl extends AppUpdateContract.AppUpdatePresenter {

    @Override
    public void loadData(String version) {
        AppUpdateContract.AppUpdateView view = getView();
        if (view == null) {
            return;
        }

        view.showLoading();

        mModel.getData(version, new HttpCallBack<AppUpdateBean>() {

            @Override
            public void onSuccess(AppUpdateBean appUpdateBean) {
                view.hideLoading();
                view.setData(appUpdateBean);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.hideLoading();
                view.showError();
            }
        });
    }
}
