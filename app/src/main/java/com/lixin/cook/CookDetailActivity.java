package com.lixin.cook;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lixin.BaseActivity;
import com.lixin.live.R;

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

    CookListBean.ResultBean.ListBean bean;
    @BindView(R.id.cook_sumary)
    TextView cookSumary;
    @BindView(R.id.cook_title)
    TextView cookTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_detail);
        ButterKnife.bind(this);
        getIntentData();
        initUI();

    }

    private void getIntentData() {
        bean = (CookListBean.ResultBean.ListBean) getIntent().getSerializableExtra("bean");

    }


    private void initUI() {

        if (bean != null) {
            acCookImage.setImageURI(bean.getThumbnail());
            collapsingToolbar.setTitle(bean.getName());
            toolbar.setTitle(bean.getName());

            cookSumary.setText(bean.getRecipe().getSumary());
            cookTitle.setText(bean.getRecipe().getTitle());
        }
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.text_grey));
        toolbar.setSubtitleTextColor(ContextCompat.getColor(this, R.color.text_grey));
        collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.text_grey));
//        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this,R.color.text_grey));
        appBarLayout.addOnOffsetChangedListener(this);

        //set the toolbar
//        CollapsingToolbarLayout.LayoutParams params = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
//        params.gravity = Gravity.TOP;
//        params.bottomMargin = 10;
//        params.height = ConstantLive.NaviBarHeight;
//        toolbar.setLayoutParams(params);


        setUpCommonBackTooblBar(R.id.toolbar, "");

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        float alpha = (float) Math.abs(verticalOffset) / (float) appBarLayout.getTotalScrollRange() * 1.0f;
//        toolbar.setAlpha(alpha);
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
