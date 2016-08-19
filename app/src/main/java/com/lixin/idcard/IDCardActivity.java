package com.lixin.idcard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lixin.live.R;
import com.lixin.mvp.MvpActivity;
import com.lixin.utils.ConstantLive;
import com.lixin.utils.LogUtil;
import com.lixin.widget.LiveNaviBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IDCardActivity extends MvpActivity<IDCardPresenter> implements IDCardView, View.OnClickListener {

    @BindView(R.id.idcardac_navibar)
    LiveNaviBar idcardacNavibar;
    @BindView(R.id.etCardNumber)
    EditText etCardNumber;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.llResult)
    LinearLayout llResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcard);
        ButterKnife.bind(this);
        initNaviBar();
        initListener();
    }

    private void initListener() {
        btnSearch.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSearch:
                LogUtil.log("btnSearch","btnSearch");
                mvpPresenter.loadData(ConstantLive.APPKEY,etCardNumber.getText().toString());
                break;
        }
    }

    private void initNaviBar() {
        idcardacNavibar.setNaviTitle("身份证信息查询");
        idcardacNavibar.setLeftBtnImage(R.mipmap.ic_back);
        idcardacNavibar.setNaviOnClickedListener(new LiveNaviBar.NaviBarClickedListener() {
            @Override
            public void onClickedLeftBtn() {
                finish();
            }

            @Override
            public void onClickedRightBtn() {

            }
        });
    }

    @Override
    protected IDCardPresenter createPresenter() {
        return new IDCardPresenter(this);
    }

    @Override
    public void getDataSuccess(IDCardBean model) {
        if(!model.getRetCode().equals("200")){
            toastShow(model.getMsg());
            clearData();
            return;
        }
        setData(model);

    }

    private void setData(IDCardBean model) {
        tvArea.setText(model.getResult().getArea());
        tvBirthday.setText(model.getResult().getBirthday());
        tvSex.setText(model.getResult().getSex());
    }

    private void clearData() {
        tvArea.setText("");
        tvBirthday.setText("");
        tvSex.setText("");
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
