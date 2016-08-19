package com.lixin.phone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lixin.BaseActivity;
import com.lixin.live.R;
import com.lixin.mvp.MvpActivity;
import com.lixin.utils.ConstantLive;
import com.lixin.utils.LogUtil;
import com.lixin.widget.LiveNaviBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneActivity extends MvpActivity<PhonePresenter> implements PhoneView, View.OnClickListener {

    @BindView(R.id.phoneac_navibar)
    LiveNaviBar phoneacNavibar;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.tvProvince)
    TextView tvProvince;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvCityCode)
    TextView tvCityCode;
    @BindView(R.id.tvMobileNumber)
    TextView tvMobileNumber;
    @BindView(R.id.tvZipCode)
    TextView tvZipCode;
    @BindView(R.id.tvOperator)
    TextView tvOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        ButterKnife.bind(this);
        initNaviBar();
        initListener();
    }

    private void initNaviBar() {
        phoneacNavibar.setNaviTitle("号码归属地查询");
        phoneacNavibar.setLeftBtnImage(R.mipmap.ic_back);
        phoneacNavibar.setNaviOnClickedListener(new LiveNaviBar.NaviBarClickedListener() {
            @Override
            public void onClickedLeftBtn() {
                finish();
            }

            @Override
            public void onClickedRightBtn() {

            }
        });
    }

    private void initListener() {
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.btnSearch:
               LogUtil.log("btnSearch","btnSearch");
               mvpPresenter.loadData(ConstantLive.APPKEY,etPhone.getText().toString());
               break;
       }
    }

    @Override
    protected PhonePresenter createPresenter() {
        return new PhonePresenter(this);
    }

    @Override
    public void getDataSuccess(PhoneBean model) {
        if(!model.getRetCode().equals("200")){
            toastShow(model.getMsg());
            clearData();
            return;
        }
        setData(model);
    }

    private void clearData() {
        tvCity.setText("");
        tvCityCode.setText("");
        tvMobileNumber.setText("");
        tvOperator.setText("");
        tvProvince.setText("");
        tvZipCode.setText("");
    }

    private void setData(PhoneBean model) {
        tvCity.setText(model.getResult().getCity());
        tvCityCode.setText(model.getResult().getCityCode());
        tvMobileNumber.setText(model.getResult().getMobileNumber());
        tvOperator.setText(model.getResult().getOperator());
        tvProvince.setText(model.getResult().getProvince());
        tvZipCode.setText(model.getResult().getZipCode());
    }


    @Override
    public void getDataFail(String msg) {
        LogUtil.log("getDataFail",msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
//        tvCity.setText("结束");
    }


}
