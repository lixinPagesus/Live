package com.lixin.idcard;

import com.lixin.phone.PhoneBean;

/**
 * 处理业务需要哪些方法
 */
public interface IDCardView {

    void getDataSuccess(IDCardBean model);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();
}
