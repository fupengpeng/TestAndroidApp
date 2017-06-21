package com.fupengpeng.selectall.selectallperfect;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fupengpeng.selectall.R;
import com.fupengpeng.selectall.entity.Item;

import java.util.List;

/**
 * Created by fupengpeng on 2017/6/21 0021.
 */

public class PerfectSelectAllAdapter extends BaseAdapter {

    private Context context;
    private List<Item> list;
    public PerfectSelectAllAdapter(Context context, List<Item> list){
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list == null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.listviewitem, null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
            holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Item item = (Item) getItem(position);

        holder.tv.setText(item.getName());
        holder.cb.setChecked(item.isB());


        return convertView;
    }


    class ViewHolder {
        public TextView tv = null;
        public CheckBox cb = null;
    }



}
