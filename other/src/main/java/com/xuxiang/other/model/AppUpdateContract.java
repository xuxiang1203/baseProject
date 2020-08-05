package com.xuxiang.other.model;

import com.xuxiang.other.bean.AppUpdateBean;
import com.xuxiang.other.net.back.HttpCallBack;

public interface AppUpdateContract {

    void getUpdateInfo(String version, HttpCallBack<AppUpdateBean> callBack);

}
