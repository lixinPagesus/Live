package com.lixin.cook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lixin.live.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lixin on 16/9/26.
 */

public class CooklistAdapeter extends BaseAdapter {

    private CookListBean cookListBean;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<CookListBean.ResultBean.ListBean> listBeen;
    public CooklistAdapeter(CookListBean cookListBean, Context context) {
        this.cookListBean = cookListBean;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

        if(listBeen == null){
            listBeen = new ArrayList<>();
        }else{
            listBeen = cookListBean.getResult().getList();
        }
    }

    @Override
    public int getCount() {
        return listBeen != null ? listBeen.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listBeen != null ? listBeen.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_cooklist, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.itemCookName.setText(listBeen.get(position).getName());
        viewHolder.itemCookTag.setText(listBeen.get(position).getCtgTitles().replace(","," "));
        viewHolder.itemCookImage.setImageURI(listBeen.get(position).getThumbnail());

        return convertView;
    }


    static class ViewHolder {

        @BindView(R.id.item_cook_image)
        SimpleDraweeView itemCookImage;
        @BindView(R.id.item_cook_name)
        TextView itemCookName;
        @BindView(R.id.item_cook_tag)
        TextView itemCookTag;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void addData(CookListBean cookListBean){

        listBeen.addAll(cookListBean.getResult().getList());
        notifyDataSetChanged();
    }

    public void replaceData(CookListBean cookListBean){

        listBeen = cookListBean.getResult().getList();
        notifyDataSetChanged();
    }

}
