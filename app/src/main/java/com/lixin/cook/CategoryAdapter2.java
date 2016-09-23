package com.lixin.cook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lixin.live.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lixin on 16/9/22.
 */

public class CategoryAdapter2 extends BaseAdapter {

    private List<CookCategoryBean.ResultBean.ChildsBean.ChildsBean2> childsBeanList;
    private Context context;
    private LayoutInflater inflater;

    public CategoryAdapter2(List<CookCategoryBean.ResultBean.ChildsBean.ChildsBean2> childsBeanList, Context context) {
        this.childsBeanList = childsBeanList;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return childsBeanList != null ?childsBeanList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return childsBeanList != null ? childsBeanList.get(position) : null;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_cook_category1, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.itemCookCategory.setText(childsBeanList.get(position).getCategoryInfo().getName());

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.item_cook_category)
        TextView itemCookCategory;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void replaceData(List<CookCategoryBean.ResultBean.ChildsBean.ChildsBean2> childsBeanList){
        this.childsBeanList = childsBeanList;
    }
}
