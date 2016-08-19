package com.lixin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixin.live.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lixin on 16/8/11.
 */
public class MainAcGridviewAdapter extends BaseAdapter {


    private Context context;
    private String[] datas;
    private LayoutInflater layoutInflater;

    public MainAcGridviewAdapter(Context context, String[] datas) {
        this.context = context;
        this.datas = datas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int i) {
        return datas[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.item_mainac_gridview, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.itemMainacGridviewContent.setText(datas[i]);

        return view;
    }


    static class ViewHolder {
        @BindView(R.id.item_mainac_gridview_content)
        TextView itemMainacGridviewContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void repalceData(String[] datas){
       this.datas = datas;
        notifyDataSetChanged();
    }
}
