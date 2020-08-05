package com.xuxiang.xxlib.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.xuxiang.xxlib.R;

public abstract class BaseCenterDialog extends DialogFragment {
    private static final String TAG = "base_center_dialog";
    protected float DEFAULT_DIM = 0.2f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CenterDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(getCancelOutside());
        View v = inflater.inflate(getLayoutRes(), container, false);
        bindView();
        return v;
    }

    @Override
    public void onStart() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = getDimAmount();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height  =  getHeight() > 0?getHeight(): WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        super.onStart();
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void bindView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public int getHeight() {
        return 0;
    }

    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    public boolean getCancelOutside() {
        return true;
    }

    public String getFragmentTag() {
        return TAG;
    }



    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getFragmentTag());
    }
}
