package com.lixin.http;

import com.lixin.cook.CookCategoryBean;
import com.lixin.cook.CookListBean;
import com.lixin.idcard.IDCardBean;
import com.lixin.phone.PhoneBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lixin on 16/8/16.
 */
public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://apicloud.mob.com/";

    @GET("v1/mobile/address/query")
    Observable<PhoneBean> loadPhoneData(@Query("key") String key, @Query("phone") String phone);

    @GET("idcard/query")
    Observable<IDCardBean> loadIDCardData(@Query("key") String key, @Query("cardno") String num);

    @GET("v1/cook/category/query")
    Observable<CookCategoryBean> loadCookCategory(@Query("key") String key);

    @GET("v1/cook/menu/search")
    Observable<CookListBean> loadCookList(@QueryMap Map<String,String> params);



}
