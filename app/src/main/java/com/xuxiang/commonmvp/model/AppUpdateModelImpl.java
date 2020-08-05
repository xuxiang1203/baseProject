package com.xuxiang.commonmvp.model;

import com.xuxiang.commonmvp.bean.AppUpdateBean;
import com.xuxiang.commonmvp.contract.AppUpdateContract;
import com.xuxiang.commonmvp.net.HttpHeper;
import com.xuxiang.commonmvp.net.back.HttpCallBack;

public class AppUpdateModelImpl implements AppUpdateContract.AppUpdateModel {

    @Override
    public void getData(String version, HttpCallBack<AppUpdateBean> callBack) {
        HttpHeper.get().baseRequest()
                .checkUpdate(version)
                .compose(HttpHeper.getThread())
                .subscribe(callBack);
    }
}
