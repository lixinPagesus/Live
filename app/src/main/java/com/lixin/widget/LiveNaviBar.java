package com.lixin.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixin.live.R;


/**
 * Created by jonas on 15/4/3.
 */
public class LiveNaviBar extends RelativeLayout {


    private TextView tvNaviLeft;
    private TextView tvNaviTitle;
    private TextView tvNaviRight;
    private RelativeLayout relativeLayout;
    private View topnaviLine;
    private NaviBarClickedListener mNaviBarClickedListener;

    public static final int LEFT_BTN = 0;
    public static final int RIGHT_BTN = 1;

    public interface NaviBarClickedListener {
        void onClickedLeftBtn();

        void onClickedRightBtn();
    }

    public void setNaviOnClickedListener(NaviBarClickedListener naviOnClickedListener) {
        this.mNaviBarClickedListener = naviOnClickedListener;
    }

    public LiveNaviBar(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_topnavi, this);
        initUI();
    }

    public LiveNaviBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_topnavi, this);
        initUI();
    }

    public LiveNaviBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_topnavi, this);
        initUI();
    }

    public void setMyBackground(int id){
        relativeLayout.setBackgroundColor(id);
    }
    public void setLineVisibility(boolean visibility){
        if(visibility) {
            topnaviLine.setVisibility(VISIBLE);
        }else{
            topnaviLine.setVisibility(GONE);
        }
    }

    private void initUI() {
        tvNaviLeft = (TextView) findViewById(R.id.tvNavLeft);
        tvNaviTitle = (TextView) findViewById(R.id.tvNavTitle);
        tvNaviRight = (TextView) findViewById(R.id.tvNavRight);
        relativeLayout = (RelativeLayout) findViewById(R.id.topRelativeLayout);
        topnaviLine = (View) findViewById(R.id.topnavi_line);
        tvNaviLeft.setOnClickListener(clickListener);
        tvNaviRight.setOnClickListener(clickListener);
    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {


            if (null != mNaviBarClickedListener) {
                switch (v.getId()) {
                    case R.id.tvNavLeft:
                        mNaviBarClickedListener.onClickedLeftBtn();
                        break;
                    case R.id.tvNavRight:
                        mNaviBarClickedListener.onClickedRightBtn();
                        break;
                    default:
                        break;
                }
            }

        }
    };

    public void setLeftBtnImage(int resId) {
        this.tvNaviLeft.setBackgroundResource(resId);
    }

    public void setRightBtnImage(int resId) {
        this.tvNaviRight.setBackgroundResource(resId);
    }

    public void setNaviTitle(String title) {
        this.tvNaviTitle.setText(title);
    }


    public void hideButton(int button) {
        if (button == LEFT_BTN) {
            tvNaviLeft.setVisibility(View.GONE);
        } else {
            tvNaviRight.setVisibility(View.GONE);
        }
    }

    public void showButton(int button) {
        if (button == LEFT_BTN) {
            tvNaviLeft.setVisibility(View.VISIBLE);
        } else {
            tvNaviRight.setVisibility(View.VISIBLE);
        }
    }

    public void setRightBtnTextAndColor(String text, int color) {
        tvNaviRight.setText(text);
        tvNaviRight.setTextColor(color);
        tvNaviRight.setCompoundDrawables(null, null, null, null);

    }
    public void setRightBtnTextAndColor(String text, int color,float textsize) {
        tvNaviRight.setText(text);
        tvNaviRight.setTextColor(color);
        tvNaviRight.setTextSize(textsize);
    }

    public void setLeftBtnTextAndColor(String text, int color,float textsize) {
        tvNaviLeft.setText(text);
        tvNaviLeft.setTextColor(getResources().getColor(color));
        tvNaviLeft.setTextSize(textsize);
    }

    public void setNaviTitle(String title, int color) {
        this.tvNaviTitle.setText(title);
        tvNaviTitle.setTextColor(color);
    }

    public void setRightBtnTextAndLeftDrawable(String text, int resId, int color) {
        tvNaviRight.setText(text);
        Drawable drawable= getResources().getDrawable(resId);
/// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvNaviRight.setCompoundDrawables(drawable, null, null, null);
        tvNaviRight.setTextColor(color);
    }

}
