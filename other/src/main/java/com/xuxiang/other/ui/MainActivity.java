package com.xuxiang.other.ui;

import android.os.SystemClock;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.xuxiang.other.R;
import com.xuxiang.other.base.ActivityInfo;
import com.xuxiang.other.base.BaseActivity;
import com.xuxiang.other.base.BaseFragmentPageAdapter;
import com.xuxiang.other.bean.AppUpdateBean;
import com.xuxiang.other.ui.fragment.EmptyFragment;
import com.xuxiang.other.ui.view.IUpdateView;
import com.xuxiang.xxlib.view.NoScrollViewPager;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@ActivityInfo(layout = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IUpdateView {

    @BindView(R.id.bnve)
    BottomNavigationViewEx bottomNavigationViewEx;
    @BindView(R.id.vp)
    NoScrollViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void initView() {
        viewPager.post(() -> initFragment());
    }

    private void initFragment() {
        fragments.add(new EmptyFragment());
        fragments.add(new EmptyFragment());
        fragments.add(new EmptyFragment());
        fragments.add(new EmptyFragment());

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextSize(11);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new BaseFragmentPageAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        viewPager.setCurrentItem(0, false);
        bottomNavigationViewEx.getMenu().findItem(R.id.menu_show).setChecked(true);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(menuItem -> {
            int position = 0;
            switch (menuItem.getItemId()) {
                case R.id.menu_show:
                    position = 0;
                    break;
                case R.id.menu_message:
                    position = 1;
                    break;
                case R.id.menu_tool:
                    position = 2;
                    break;
                case R.id.menu_mine:
                    position = 3;
                    break;

            }

            viewPager.setCurrentItem(position, false);
//            if (position == 3 && TextUtils.isEmpty(AppConfig.getInstance().getToken())) {
//
//            }
//            if (position == 1) {
//                RxEventProcessor.get().post(AppCode.UPDATE_MESSAGE);
//            }
            return true;
        });
        bottomNavigationViewEx.setItemIconTintList(null);
    }


    @Override
    public void onSuccess(AppUpdateBean bean) {

    }

    @Override
    public void onError(int code, String errorMsg) {

    }



    /**
     * 上一次点击退出的时间戳
     */
    private long m_nLastExitTimeStamp;

    @Override
    public void onBackPressed() {
        if (SystemClock.uptimeMillis() - m_nLastExitTimeStamp < 2500) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
            super.onBackPressed();
        } else {
            m_nLastExitTimeStamp = SystemClock.uptimeMillis();
            Toast.makeText(this, "再次返回，退出应用", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
