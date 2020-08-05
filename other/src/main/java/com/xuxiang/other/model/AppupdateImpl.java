package com.xuxiang.other.model;

import com.xuxiang.other.bean.AppUpdateBean;
import com.xuxiang.other.net.HttpHeper;
import com.xuxiang.other.net.back.HttpCallBack;

public class AppupdateImpl implements AppUpdateContract {

    @Override
    public void getUpdateInfo(String version, HttpCallBack<AppUpdateBean> callBack) {
        HttpHeper.get().baseRequest()
                .checkUpdate(version)
                .compose(HttpHeper.getThread())
                .subscribe(callBack);
    }
}
