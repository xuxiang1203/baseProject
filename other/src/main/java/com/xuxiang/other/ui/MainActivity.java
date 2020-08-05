package com.xuxiang.other.ui;

import android.widget.TextView;

import com.xuxiang.other.BuildConfig;
import com.xuxiang.other.R;
import com.xuxiang.other.base.BaseActivity;
import com.xuxiang.other.bean.AppUpdateBean;
import com.xuxiang.other.presenter.AppUpdatePresenter;
import com.xuxiang.other.ui.view.IUpdateView;


public class MainActivity extends BaseActivity implements IUpdateView {

    private TextView tvVersion;

    private AppUpdatePresenter updatePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tvVersion = findViewById(R.id.version);
        updatePresenter = new AppUpdatePresenter();
        updatePresenter.attachView(this);

        updatePresenter.getAppUpdateInfo(BuildConfig.VERSION_NAME);
    }


    @Override
    public void onSuccess(AppUpdateBean bean) {
        tvVersion.setText(bean.getAndroid().getVersion());
    }

    @Override
    public void onError(int code, String errorMsg) {

    }

    @Override
    public void showLoading() {
        tvVersion.setText("加载中");
    }

    @Override
    public void hideLoading() {
        tvVersion.setText("加载完成");
    }

    @Override
    protected void onDestroy() {
        if (updatePresenter != null)
            updatePresenter.detachView(this);
        super.onDestroy();
    }
}
