package com.lixin.cook;

import android.content.Intent;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CookActivity extends MvpActivity<CookPresenter> implements CookView, XListView.IXListViewListener, AdapterView.OnItemClickListener {


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
    CooklistAdapeter cooklistAdapeter;
    @BindView(R.id.cook_navibar)
    LiveNaviBar cookNavibar;
    private List<CookCategoryBean.ResultBean.ChildsBean> childsBeanList1 = new ArrayList<>();
    private List<CookCategoryBean.ResultBean.ChildsBean.ChildsBean2> childsBeanList2 = new ArrayList<>();
    private int currentPage = 1;
    private int maxPage = 1;
    private int pageSize = 20;//每页返回数量

    private CookListBean cookListBean = new CookListBean();
    private boolean isRefresh = true;

    private int currentPosition = 0;
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

        cooklistAdapeter = new CooklistAdapeter(cookListBean,this);
        listview1.setAdapter(cooklistAdapeter);

        listview1.setXListViewListener(this);
        listview1.setPullRefreshEnable(true);
        listview1.setPullLoadEnable(true);

        listview1.setOnItemClickListener(this);


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
        isRefresh = true;

        getCookList(currentPosition,String.valueOf(currentPage));
    }

    @Override
    public void onXListViewLoadMore() {

        isRefresh = false;

        if(currentPage < maxPage){
            currentPage+=1;
            getCookList(currentPosition,String.valueOf(currentPage));
            if(currentPage == maxPage){
                listview1.setPullLoadEnable(false);
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent inent = new Intent(CookActivity.this,CookDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", (CookListBean.ResultBean.ListBean) cooklistAdapeter.getItem(position-1));
        inent.putExtras(bundle);
        startActivity(inent);
    }

    private class myOnItemSelectedListener1 implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            childsBeanList2 = childsBeanList1.get(position).getChilds();
//            categoryAdapter2.replaceData(childsBeanList2);
//            categoryAdapter2.notifyDataSetChanged();
            categoryAdapter2 = new CategoryAdapter2(childsBeanList2, CookActivity.this);
            spinnerCategory2.setAdapter(categoryAdapter2);
            spinnerCategory2.setOnItemSelectedListener(new myOnItemSelectedListener2());
            currentPage = 1;

            if(spinnerCategory2.getCount() > 0) {
                spinnerCategory2.post(new Runnable() {
                    @Override
                    public void run() {
                        spinnerCategory2.setSelection(0);
                    }
                });

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


    private class myOnItemSelectedListener2 implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            isRefresh = true;
            currentPosition = position;
            getCookList(position,String.valueOf(currentPage));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void getCookList(int position,String currentPage ) {

        if(childsBeanList2 == null || childsBeanList2.size() == 0){
            listview1.stopRefresh();
            return;
        }

        Map<String,String> params = new HashMap<>();
        params.put("key", ConstantLive.APPKEY);
        params.put("cid",childsBeanList2.get(position).getCategoryInfo().getCtgId());
//        params.put("name",childsBeanList2.get(position).getCategoryInfo().getName()); 用于搜索
        params.put("page",currentPage);

        mvpPresenter.getCookList(params);


    }


    @Override
    public void getCookListSuccess(CookListBean model) {
        if(model != null){
            LogUtil.log("getCookListSuccess", model.getResult().getTotal());
            if(isRefresh) {
                listview1.stopRefresh();

                maxPage = (model.getResult().getTotal()-1)/pageSize+1;
                cooklistAdapeter.replaceData(model);
                listview1.setSelection(0);
                if(currentPage == maxPage){
                    listview1.setPullLoadEnable(false);
                }else{
                    listview1.setPullLoadEnable(true);
                }
            }else{
                listview1.stopLoadMore();
                cooklistAdapeter.addData(model);
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        listview1.stopRefresh();
        listview1.stopLoadMore();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
