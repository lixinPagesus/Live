package com.lixin.cook;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lixin.BaseActivity;
import com.lixin.live.R;
import com.lixin.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CookDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.ac_cook_image)
    SimpleDraweeView acCookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_detail);
        ButterKnife.bind(this);

        initUI();

        acCookImage.setImageURI("http://img3.imgtn.bdimg.com/it/u=1819305524,3742145549&fm=21&gp=0.jpg");
    }


    private void initUI() {

        collapsingToolbar.setTitleEnabled(false);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //set the toolbar
        int toolbar_hight = Utils.getToolbarHeight(this);

        CollapsingToolbarLayout.LayoutParams params = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
        params.height = toolbar_hight;
        toolbar.setLayoutParams(params);

        setUpCommonBackTooblBar(R.id.toolbar, "菜单详情");


    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        mSwipeRefreshLayout.setEnabled(i == 0);
        float alpha = (float) Math.abs(verticalOffset) / (float) appBarLayout.getTotalScrollRange() * 1.0f;
        toolbar.setAlpha(alpha);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }
}
