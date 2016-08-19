package com.lixin.phone;


import com.lixin.http.ApiCallback;
import com.lixin.http.SubscriberCallBack;
import com.lixin.mvp.BasePresenter;
import com.lixin.utils.LogUtil;

/**
 * Created by lixin on 16/8/16.
 */
public class PhonePresenter extends BasePresenter<PhoneView>{



    public PhonePresenter(PhoneView view) {
        attachView(view);
    }

    public void loadData(String key,String phone) {
        mvpView.showLoading();
        LogUtil.log("loadData key",key+"  phone = "+phone);
        addSubscription(apiStores.loadPhoneData(key,phone),
                new SubscriberCallBack<>(new ApiCallback<PhoneBean>() {
                    @Override
                    public void onSuccess(PhoneBean model) {
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
