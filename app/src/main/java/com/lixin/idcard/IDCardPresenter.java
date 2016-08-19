package com.lixin.idcard;


import com.lixin.http.ApiCallback;
import com.lixin.http.SubscriberCallBack;
import com.lixin.mvp.BasePresenter;
import com.lixin.phone.PhoneBean;
import com.lixin.phone.PhoneView;
import com.lixin.utils.LogUtil;

/**
 * Created by lixin on 16/8/16.
 */
public class IDCardPresenter extends BasePresenter<IDCardView>{



    public IDCardPresenter(IDCardView view) {
        attachView(view);
    }

    public void loadData(String key,String id) {
        mvpView.showLoading();
        LogUtil.log("loadData key",key+"  phone = "+id);
        addSubscription(apiStores.loadIDCardData(key,id),
                new SubscriberCallBack<>(new ApiCallback<IDCardBean>() {
                    @Override
                    public void onSuccess(IDCardBean model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                }));
    }
}
