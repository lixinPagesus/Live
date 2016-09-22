package com.lixin.cook;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lixin.live.R;
import com.lixin.mvp.MvpActivity;
import com.lixin.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CookActivity extends MvpActivity<CookPresenter> implements CookView {


    @BindView(R.id.textView)
    TextView textView2;
    @BindView(R.id.spinner1)
    Spinner spinnerCategory1;
    @BindView(R.id.spinner2)
    Spinner spinnerCategory2;
    @BindView(R.id.listview1)
    ListView listview1;

    CategoryAdapter1 categoryAdapter1;
    CategoryAdapter2 categoryAdapter2;
    private List<CookCategoryBean.ResultBean.ChildsBean> childsBeanList1 = new ArrayList<>();
    private List<CookCategoryBean.ResultBean.ChildsBean.ChildsBean2> childsBeanList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);
        ButterKnife.bind(this);
        initUI();
        mvpPresenter.getCookCategory();
    }

    private void initUI() {

        categoryAdapter1 = new CategoryAdapter1(childsBeanList1,CookActivity.this);
        spinnerCategory1.setAdapter(categoryAdapter1);
        spinnerCategory1.setOnItemClickListener(new myOnItemClickListener1());


        categoryAdapter2 = new CategoryAdapter2(childsBeanList2,CookActivity.this);
        spinnerCategory2.setAdapter(categoryAdapter1);
        spinnerCategory2.setOnItemClickListener(new myOnItemClickListener2());
    }

    @Override
    protected CookPresenter createPresenter() {
        return new CookPresenter(this);
    }

    @Override
    public void getCookCategorySuccess(CookCategoryBean model) {
        if (model != null) {

            childsBeanList1 = model.getResult().getChilds();
            childsBeanList2 = model.getResult().getChilds().get(0).getChilds();
            categoryAdapter1.notifyDataSetChanged();
            categoryAdapter2.notifyDataSetChanged();




        }
    }

    private class myOnItemClickListener1 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    private class myOnItemClickListener2 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }


    @Override
    public void getCookListSuccess(CookListBean model) {

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
