package com.xuxiang.commonmvp.contract;

import com.xuxiang.commonmvp.base.BaseModel;
import com.xuxiang.commonmvp.base.BasePresenter;
import com.xuxiang.commonmvp.base.BaseView;
import com.xuxiang.commonmvp.bean.AppUpdateBean;
import com.xuxiang.commonmvp.net.back.HttpCallBack;

public interface AppUpdateContract {
    interface AppUpdateModel extends BaseModel {
        void getData(String version, HttpCallBack<AppUpdateBean> callBack);
    }

    interface AppUpdateView extends BaseView {
        void setData(AppUpdateBean bean);
    }

    abstract class AppUpdatePresenter extends BasePresenter<AppUpdateModel, AppUpdateView> {
        protected abstract void loadData(String version);
    }
}
