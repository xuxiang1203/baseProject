package com.xuxiang.other.ui;

import android.widget.ImageView;
import android.widget.TextView;

import com.xuxiang.other.BuildConfig;
import com.xuxiang.other.R;
import com.xuxiang.other.app.GlideApp;
import com.xuxiang.other.base.ActivityInfo;
import com.xuxiang.other.base.BaseActivity;
import com.xuxiang.other.bean.AppUpdateBean;
import com.xuxiang.other.presenter.AppUpdatePresenter;
import com.xuxiang.other.ui.view.IUpdateView;

import butterknife.BindView;

@ActivityInfo(layout = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IUpdateView {

    @BindView(R.id.version)
    TextView tvVersion;
    @BindView(R.id.img)
    ImageView img;

    private AppUpdatePresenter updatePresenter;

    @Override
    protected void initView() {
        updatePresenter = new AppUpdatePresenter();
        updatePresenter.attachView(this);

        updatePresenter.getAppUpdateInfo(BuildConfig.VERSION_NAME);
        GlideApp.with(this).load(R.mipmap.ic_launcher).into(img);
    }


    @Override
    public void onSuccess(AppUpdateBean bean) {
        tvVersion.setText(bean.getAndroid().getVersion());
    }

    @Override
    public void onError(int code, String errorMsg) {

    }

    @Override
    protected void onDestroy() {
        if (updatePresenter != null)
            updatePresenter.detachView(this);
        super.onDestroy();
    }
}
