package com.lixin.http;

import com.lixin.idcard.IDCardBean;
import com.lixin.phone.PhoneBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
    Observable<IDCardBean> loadIDCardData(@Query("key") String key, @Query("cardno") String phone);
}
