package com.lixin.cook;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;

import com.lixin.BaseActivity;
import com.lixin.live.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CookDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_cook_detail)
    CoordinatorLayout activityCookDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_detail);
        ButterKnife.bind(this);

        initUI();

    }

    private void initUI() {

        toolbar.setTitle("title");

    }
}
