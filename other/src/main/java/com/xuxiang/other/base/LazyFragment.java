package com.xuxiang.other.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.fragment.app.Fragment;

import com.xuxiang.xxlib.dialog.LoadSuccessAndFailDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created  on 2018/1/17
 * @describe 不会从新执行onCreateView方法
 */
public abstract class LazyFragment extends Fragment {

    // 检测声明周期中，是否已经构建视图
    private boolean mViewCreated = false;

    // 占位图
    private ViewStubCompat mViewStub;

    private boolean mUserVisible = false;
    Unbinder unbinder;

    /**
     * 配合{@link } onCreateView 方法只会执行一次
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Context context = inflater.getContext();
        FrameLayout root = new FrameLayout(context);
        mViewStub = new ViewStubCompat(context, null);
        mViewStub.setLayoutResource(getResId());
        root.addView(mViewStub, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        root.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT));
        mViewCreated = true;
        if (mUserVisible) {
            realLoad();
        }
        return root;
    }

    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mUserVisible = isVisibleToUser;
        if (mUserVisible && mViewCreated) {
            realLoad();
        }
    }

    // 判断是否已经加载
    private boolean mLoaded = false;

    /**
     * 控制只允许加载一次
     */
    @SuppressLint("RestrictedApi")
    private void realLoad() {
        if (mLoaded) {
            return;
        }
        mLoaded = true;
        View view = mViewStub.inflate();
        unbinder =  ButterKnife.bind(this,view);
        onRealLoaded();
    }

    @Override
    public void onDestroyView() {
        mViewCreated = false;
        if(unbinder != null)
        unbinder.unbind();
        super.onDestroyView();
    }

    /**
     * 获取真正的数据视图
     *
     * @return
     */
    protected abstract int getResId();

    /**
     * 当视图真正加载时调用
     */
    protected abstract void onRealLoaded();


    protected void showToast(String mes) {
        LoadSuccessAndFailDialog.showFail(getActivity(), mes);
    }

    protected void showSuccessToast(String mes) {
        LoadSuccessAndFailDialog.showSuccess(getActivity(), mes);
    }

    public void startActivity(Class cla) {
        Intent intent = new Intent(getContext(), cla);
        super.startActivity(intent);
    }

}
