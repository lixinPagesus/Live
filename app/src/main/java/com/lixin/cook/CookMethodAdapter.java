package com.lixin.cook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lixin.live.R;
import com.lixin.utils.LogUtil;
import com.lixin.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lixin on 16/10/7.
 */

public class CookMethodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<CookMethodRes.MethodsBean> methods;
    private LayoutInflater layoutInflater;

    public CookMethodAdapter(Context context,List<CookMethodRes.MethodsBean>  method) {
        this.context = context;
        this.methods = method;
        LogUtil.log("method.size()",method.size());
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_cook_method, parent,
                false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
//        LogUtil.log("onBindViewHolder",method.get(position).get("img").toString());
        String img = methods.get(position).getImg() ;
        if(Utils.stringIsNotEmpty(img)) {
            viewHolder.itemCookmethodIv.setVisibility(View.VISIBLE);
            viewHolder.itemCookmethodIv.setImageURI(img);
        }else{
//            viewHolder.itemCookmethodIv.setVisibility(View.GONE);
        }

        viewHolder.itemCookmethodTitle.setText(methods.get(position).getStep());

    }



    @Override
    public int getItemCount() {
        return methods != null ? methods.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_cookmethod_title)
        TextView itemCookmethodTitle;
        @BindView(R.id.item_cookmethod_iv)
        SimpleDraweeView itemCookmethodIv;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
