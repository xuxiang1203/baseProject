package com.xuxiang.other.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xuxiang.other.R;
import com.xuxiang.xxlib.bar.StatusBarUtil;
import com.xuxiang.xxlib.dialog.LoadSuccessAndFailDialog;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
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



    /**
     * 点击返回上一级
     */
    public void onBack(View v) {
        onBackPressed();
    }

    /**
     * 设置每个页面的title
     */
    public TextView setTitle(String title) {
        TextView tv = (TextView) findViewById(R.id.tv_title_name);
        if (tv != null) {
            tv.setText(title);
        }
        return tv;
    }

    protected void showToast(String mes) {
        LoadSuccessAndFailDialog.showFail(this, mes);
    }

    protected void showSuccessToast(String mes) {
        LoadSuccessAndFailDialog.showSuccess(this, mes);
    }

    /**
     * 点击键盘以外区域，收起键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (isShouldHideInput(v, ev)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return super.dispatchTouchEvent(ev);
            }
            // 必不可少，否则所有的组件都不会有TouchEvent了
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return onTouchEvent(ev);
        }
    }

    protected boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                //使EditText触发一次失去焦点事件
                v.setFocusable(false);
                v.setFocusableInTouchMode(true);
                return true;
            }
        }
        return false;
    }


    /**
     * 跳转
     *
     * @param cla
     */
    public void startActivity(Class cla) {
        Intent intent = new Intent(this, cla);
        super.startActivity(intent);
    }

    Disposable disposable;

    public void clearFinish() {
        disposable = Observable.timer(1001, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    finish();
                }).subscribe();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        unbinder.unbind();
    }
}
