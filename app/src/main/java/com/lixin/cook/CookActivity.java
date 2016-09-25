package com.lixin.cook;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lixin.live.R;
import com.lixin.mvp.MvpActivity;
import com.lixin.utils.ConstantLive;
import com.lixin.utils.LogUtil;
import com.lixin.widget.LiveNaviBar;
import com.lixin.widget.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CookActivity extends MvpActivity<CookPresenter> implements CookView, XListView.IXListViewListener {


    @BindView(R.id.textView)
    TextView textView2;
    @BindView(R.id.spinner1)
    Spinner spinnerCategory1;
    @BindView(R.id.spinner2)
    Spinner spinnerCategory2;
    @BindView(R.id.listview1)
    XListView listview1;

    CategoryAdapter1 categoryAdapter1;
    CategoryAdapter2 categoryAdapter2;
    @BindView(R.id.cook_navibar)
    LiveNaviBar cookNavibar;
    private List<CookCategoryBean.ResultBean.ChildsBean> childsBeanList1 = new ArrayList<>();
    private List<CookCategoryBean.ResultBean.ChildsBean.ChildsBean2> childsBeanList2 = new ArrayList<>();
    private int currentPage = 1;
    private int maxPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);
        ButterKnife.bind(this);
        initUI();
        initNaviBar();
        mvpPresenter.getCookCategory();
    }

    private void initNaviBar() {
        cookNavibar.setNaviTitle("菜谱");
        cookNavibar.setLeftBtnImage(R.mipmap.ic_back);
        cookNavibar.setNaviOnClickedListener(new LiveNaviBar.NaviBarClickedListener() {
            @Override
            public void onClickedLeftBtn() {
                finish();
            }

            @Override
            public void onClickedRightBtn() {

            }
        });
    }


    private void initUI() {

        categoryAdapter1 = new CategoryAdapter1(childsBeanList1, CookActivity.this);
        spinnerCategory1.setAdapter(categoryAdapter1);
        spinnerCategory1.setOnItemSelectedListener(new myOnItemSelectedListener1());

        categoryAdapter2 = new CategoryAdapter2(childsBeanList2, CookActivity.this);
        spinnerCategory2.setAdapter(categoryAdapter2);
        spinnerCategory2.setOnItemSelectedListener(new myOnItemSelectedListener2());

        listview1.setXListViewListener(this);
        listview1.setPullRefreshEnable(true);
        listview1.setPullLoadEnable(true);


    }

    @Override
    protected CookPresenter createPresenter() {
        return new CookPresenter(this);
    }

    @Override
    public void getCookCategorySuccess(CookCategoryBean model) {
        if (model != null) {
//            currentPage = model.getResult().get


            childsBeanList1 = model.getResult().getChilds();
            childsBeanList2 = model.getResult().getChilds().get(0).getChilds();
            categoryAdapter1.replaceData(childsBeanList1);
            categoryAdapter2.replaceData(childsBeanList2);
            categoryAdapter1.notifyDataSetChanged();
            categoryAdapter2.notifyDataSetChanged();


        }
    }

    @Override
    public void onXListViewRefresh() {

    }

    @Override
    public void onXListViewLoadMore() {

    }

    private class myOnItemSelectedListener1 implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            childsBeanList2 = childsBeanList1.get(position).getChilds();
            categoryAdapter2.replaceData(childsBeanList2);
            categoryAdapter2.notifyDataSetChanged();
            if(spinnerCategory2.getCount() > 0) {
                spinnerCategory2.setSelection(0);
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


    private class myOnItemSelectedListener2 implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            getCookList(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void getCookList(int position) {
        Map<String,String> params = new HashMap<>();

        params.put("key", ConstantLive.APPKEY);
        params.put("cid",childsBeanList2.get(position).getCategoryInfo().getCtgId());
//        params.put("name",childsBeanList2.get(position).getCategoryInfo().getName()); 用于搜索
        params.put("page",String.valueOf(currentPage));

        mvpPresenter.getCookList(params);


    }


    @Override
    public void getCookListSuccess(CookListBean model) {
        if(model != null){
            LogUtil.log("getCookListSuccess", model.getResult().getTotal());
        }
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
