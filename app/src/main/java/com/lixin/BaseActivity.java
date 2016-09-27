package com.lixin;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lixin.live.R;
import com.lixin.utils.LogUtil;
import com.lixin.utils.SystemBarTintManager;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {



    public Activity mActivity;
    public SystemBarTintManager tintManager ;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mActivity = this;


    }

    protected void setStateBarColor(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(resId);
            tintManager.setStatusBarDarkMode(true, this);
        }
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        mActivity = this;

    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        mActivity = this;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStateBarColor(R.color.line_bg_grey);
        LogUtil.log("baseac","onCreate");
    }


    @Override
    protected void onDestroy() {
        onUnsubscribe();
        super.onDestroy();
    }

    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
        }
    }

    public void addSubscription(Subscription subscription) {

        mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(subscription);
    }


    protected void setUpCommonBackTooblBar(int toolbarId, String title) {
        Toolbar mToolbar = (Toolbar) findViewById(toolbarId);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        toobarAsBackButton(mToolbar);
    }

    /**
     * toolbar点击返回，模拟系统返回
     * 设置toolbar 为箭头按钮
     * app:navigationIcon="?attr/homeAsUpIndicator"
     *
     * @param toolbar
     */
    public void toobarAsBackButton(Toolbar toolbar) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }
}
