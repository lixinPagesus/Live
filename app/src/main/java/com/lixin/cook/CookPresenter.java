package com.lixin.cook;

import com.lixin.http.ApiCallback;
import com.lixin.http.SubscriberCallBack;
import com.lixin.mvp.BasePresenter;
import com.lixin.phone.PhoneBean;
import com.lixin.utils.LogUtil;

import java.util.Map;

/**
 * Created by lixin on 16/9/20.
 */

public class CookPresenter extends BasePresenter<CookView> {

    public CookPresenter(CookView cookView) {
        attachView(cookView);
    }

    public void getCookCategory(){

        mvpView.showLoading();
        addSubscription(apiStores.loadCookCategory(sharesdkKey),
                new SubscriberCallBack<>(new ApiCallback<CookCategoryBean>() {
                    @Override
                    public void onSuccess(CookCategoryBean model) {
                        mvpView.getCookCategorySuccess(model);
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


    public void getCookList(Map<String,String> params){

        mvpView.showLoading();
        addSubscription(apiStores.loadCookList(params),
                new SubscriberCallBack<>(new ApiCallback<CookListBean>() {
                    @Override
                    public void onSuccess(CookListBean model) {
                        mvpView.getCookListSuccess(model);
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
