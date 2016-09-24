package com.lixin.cook;


/**
 * 处理业务需要哪些方法
 */
public interface CookView {

    void getCookCategorySuccess(CookCategoryBean model);

    void getCookListSuccess(CookListBean model);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();
}
