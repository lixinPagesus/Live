package com.lixin.main;

import android.os.Bundle;
import android.os.HandlerThread;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lixin.adapter.MainAcGridviewAdapter;
import com.lixin.live.R;
import com.lixin.mvp.MvpActivity;
import com.lixin.utils.LogUtil;
import com.lixin.widget.LiveNaviBar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lixin.utils.ConstantLive.NaviBarHeight;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView, AdapterView.OnItemClickListener {


    @BindView(R.id.mainac_navibar)
    LiveNaviBar mainacNavibar;
    @BindView(R.id.mainac_gridview)
    GridView mainacGridview;

    MainAcGridviewAdapter  mainAcGridviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainacNavibar.post(new Runnable() {
            @Override
            public void run() {
                NaviBarHeight = mainacNavibar.getHeight();
                LogUtil.log("MainActivity mainacNavibar.getHeight()",NaviBarHeight);
            }
        });


        initView();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initView() {
        mainacNavibar.setNaviTitle("Live");
        mainAcGridviewAdapter = new MainAcGridviewAdapter(this,new String[]{});
        mainacGridview.setAdapter(mainAcGridviewAdapter);
        mainacGridview.setOnItemClickListener(this);
        mvpPresenter.showData();
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mvpPresenter.openDetail(i,MainActivity.this);
    }

    @Override
    public void showData(String[] data) {
        mainAcGridviewAdapter.repalceData(data);
    }
}
