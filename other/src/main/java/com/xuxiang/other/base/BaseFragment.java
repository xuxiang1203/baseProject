package com.xuxiang.other.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.xuxiang.xxlib.dialog.LoadSuccessAndFailDialog;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 纯净Fragment
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    Unbinder unbinder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getResId(), container, false);
        unbinder = ButterKnife.bind(this, v);
        onRealLoaded();
        return v;
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

    @Override
    public void onDestroyView() {
        try {
            if (unbinder != null) unbinder.unbind();
        } catch (Exception e) {

        }
        super.onDestroyView();
    }


    protected void showToast(String mes) {
        LoadSuccessAndFailDialog.showFail(mActivity, mes);
    }

    protected void showSuccessToast(String mes) {
        LoadSuccessAndFailDialog.showSuccess(mActivity, mes);
    }
}
