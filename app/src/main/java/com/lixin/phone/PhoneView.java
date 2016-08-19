package com.lixin.phone;

/**
 * 处理业务需要哪些方法
 */
public interface PhoneView {

    void getDataSuccess(PhoneBean model);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();
}
