package com.xuxiang.other.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xuxiang.xxlib.bar.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity{

    public Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInfo activityInfo = getClass().getAnnotation(ActivityInfo.class);
        setContentView(getLayout() == -1 ? activityInfo.layout() : getLayout());
        unbinder = ButterKnife.bind(this);

        //         沉浸式状态栏设置 要在setContentView()之后执行
//        当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的padding,  为false时则可以用来设置图片视频沉浸
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
//        状态栏黑白主题切换
        StatusBarUtil.setStatusBarDarkTheme(this, true);
//        自定义状态栏颜色
//        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorWhite));
        initView();
    }


    protected abstract void initView();
    protected int getLayout() {
        return -1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
