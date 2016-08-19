package com.lixin.main;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.lixin.idcard.IDCardActivity;
import com.lixin.mvp.BasePresenter;
import com.lixin.phone.PhoneActivity;
import com.lixin.utils.LogUtil;

/**
 * Created by lixin on 16/8/12.
 */
public class MainPresenter extends BasePresenter<MainView> {

    private final String PHONE = "手机号码查询";
    private final String CARD = "身份证查询";
    private final String WEATHER = "天气预报";
    private final String CATEGORY = "菜谱大全";
    private final String FLIGHT = "航班信息查询";
    private final String CAR = "汽车信息查询";

    private String[] tags = {PHONE,CARD,WEATHER,CATEGORY,FLIGHT,CAR};

    public MainPresenter(MainView view) {
        attachView(view);

    }

    public void showData(){
        mvpView.showData(tags);
    }

    public void openDetail(int position,Context context) {

        Intent intent = null;
        switch (tags[position]){
            case PHONE:
                intent = new Intent(context, PhoneActivity.class);
                break;
            case CARD:
                intent = new Intent(context, IDCardActivity.class);
                break;
            case WEATHER:
                Toast.makeText(context,"不想开发了",Toast.LENGTH_SHORT).show();
                break;
            case CATEGORY:
                Toast.makeText(context,"不想开发了",Toast.LENGTH_SHORT).show();
                break;
            case FLIGHT:
                Toast.makeText(context,"不想开发了",Toast.LENGTH_SHORT).show();
                break;
            case CAR:
                Toast.makeText(context,"不想开发了",Toast.LENGTH_SHORT).show();
                break;
        }
        if(intent != null) {
            context.startActivity(intent);
        }
    }


}
