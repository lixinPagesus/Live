package com.lixin.cook;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lixin.BaseActivity;
import com.lixin.live.R;
import com.lixin.utils.Utils;
import com.lixin.widget.MyLinearLayoutManager;
import com.mob.tools.utils.Hashon;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
    @BindView(R.id.cook_ingredients_title)
    TextView cookIngredientsTitle;
    @BindView(R.id.cook_ingredients)
    TextView cookIngredients;
    @BindView(R.id.cook_method_title)
    TextView cookMethodTitle;
    @BindView(R.id.cook_method_recyclerview)
    RecyclerView cookMethodRecyclerview;

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

        if (bean != null) {
            acCookImage.setImageURI(bean.getThumbnail());
            collapsingToolbar.setTitle(bean.getName());
            toolbar.setTitle(bean.getName());
            cookSumary.setText(bean.getRecipe().getSumary());
            cookTitle.setText(bean.getRecipe().getTitle());
            initCookIngredients(bean);
            initMethod(bean);
        }

    }

    private void initMethod(CookListBean.ResultBean.ListBean bean) {
        if (Utils.stringIsNotEmpty(bean.getRecipe().getMethod())) {
            CookMethodRes cookMethodBean = null;
            Gson gson = new Gson();
            Type type = new TypeToken<CookMethodRes>() {
            }.getType();
            cookMethodBean = gson.fromJson("{\"methods\":" + bean.getRecipe().getMethod() + "}", type);

            cookMethodRecyclerview.setLayoutManager(new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            cookMethodRecyclerview.setFocusable(false);
            cookMethodRecyclerview.setNestedScrollingEnabled(false);
//            SpacesItemDecoration decoration = new SpacesItemDecoration(16);
//            cookMethodRecyclerview.addItemDecoration(decoration);

            cookMethodRecyclerview.setAdapter(new CookMethodAdapter(this, cookMethodBean.getMethods()));



        } else {
            cookMethodTitle.setVisibility(View.GONE);
            cookMethodRecyclerview.setVisibility(View.GONE);
        }
    }



    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }

    private void initCookIngredients(CookListBean.ResultBean.ListBean bean) {
        if (Utils.stringIsNotEmpty(bean.getRecipe().getIngredients())) {
            Hashon hashon = new Hashon();
            ArrayList<String> ingredients = (ArrayList<String>) hashon.fromJson("{\"list\":" + bean.getRecipe().getIngredients() + "}").get("list");
            StringBuilder sb = new StringBuilder();
            if (ingredients != null) {
                for (String i : ingredients) {
                    sb.append('\n').append(i + "");
                }
                sb.deleteCharAt(0);
            }
            cookIngredients.setText(sb.toString());
        } else {
            cookIngredientsTitle.setVisibility(View.GONE);
            cookIngredients.setVisibility(View.GONE);
        }
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
