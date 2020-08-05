package com.xuxiang.commonmvp.ui;

import android.widget.TextView;

import com.xuxiang.commonmvp.BuildConfig;
import com.xuxiang.commonmvp.R;
import com.xuxiang.commonmvp.base.BaseMvpActivity;
import com.xuxiang.commonmvp.bean.AppUpdateBean;
import com.xuxiang.commonmvp.contract.AppUpdateContract;
import com.xuxiang.commonmvp.model.AppUpdateModelImpl;
import com.xuxiang.commonmvp.presenter.AppUpdatePresenterImpl;

public class MainActivity extends BaseMvpActivity<AppUpdatePresenterImpl, AppUpdateModelImpl> implements AppUpdateContract.AppUpdateView {

    private TextView tvVersion;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tvVersion = findViewById(R.id.version);
    }

    @Override
    protected void loadData() {
        mPresenter.loadData(BuildConfig.VERSION_NAME);
    }

    @Override
    public void setData(AppUpdateBean bean) {
        tvVersion.setText(bean.getAndroid().toString());
    }

    @Override
    public void showLoading() {
        tvVersion.setText("加载中");
    }

    @Override
    public void showError() {
        tvVersion.setText("加载失败");
    }

    @Override
    public void hideLoading() {

    }
}
