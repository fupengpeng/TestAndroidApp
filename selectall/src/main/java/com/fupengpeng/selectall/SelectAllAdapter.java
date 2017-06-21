package com.fupengpeng.selectall;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fupengpeng.selectall.R;
import com.fupengpeng.selectall.SelectAllActivity;
import com.fupengpeng.selectall.ViewHolder;
import com.fupengpeng.selectall.entity.CommodityObject;
import com.fupengpeng.selectall.selectallperfect.PerfectSelectAllActivity;

import java.util.List;

/**
 * Created by fupengpeng on 2017/6/21 0021.
 */

public class SelectAllAdapter extends BaseAdapter {


    private Context context;
    private List<CommodityObject> list;
    public SelectAllAdapter(Context context, List<CommodityObject> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
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

        ViewHolder holder;
        if(convertView == null || (holder=(ViewHolder)convertView.getTag()) == null){
            convertView = View.inflate(context, R.layout.commoditylist, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.designate = (CheckBox) convertView.findViewById(R.id.checkBox);
            holder.number = (TextView) convertView.findViewById(R.id.tv_number);
            holder.commodityPic = (ImageView) convertView.findViewById(R.id.imageView);
            holder.price = (TextView) convertView.findViewById(R.id.tv_price);

            convertView.setTag(holder);
        }
        CommodityObject commodityObject = (CommodityObject) getItem(position);
        holder.name.setText(commodityObject.getName());
        holder.designate.setChecked(commodityObject.isDesignate());
        holder.number.setText(commodityObject.getNumber()+"");
        holder.commodityPic.setImageResource(commodityObject.getCommodityPic());
        holder.price.setText((int) commodityObject.getPrice()+"");

        holder.designate.setTag(position);
        holder.designate.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) context);

        return convertView;
    }

}
