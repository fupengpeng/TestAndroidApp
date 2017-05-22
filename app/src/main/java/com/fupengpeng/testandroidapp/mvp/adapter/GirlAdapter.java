package com.fupengpeng.testandroidapp.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fupengpeng.testandroidapp.R;
import com.fupengpeng.testandroidapp.mvp.bean.Girl;
import com.fupengpeng.testandroidapp.mvp.utils.DataUtils;
import com.fupengpeng.testandroidapp.textview.TextViewActivity;

import java.util.List;

/**
 * Created by fp on 2017/5/21.
 */

public class GirlAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Girl> data;

    public GirlAdapter(Context context,List<Girl> data){
        layoutInflater  = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size()==0?0:data.size();
    }


    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_girl_list, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
            viewHolder.textView01 = (TextView) view.findViewById(R.id.textView1);
            viewHolder.textView03 = (TextView) view.findViewById(R.id.textView3);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(data.get(i).getPhoto());
        viewHolder.textView01.setText(data.get(i).getEvaluation());
        viewHolder.textView03.setText(data.get(i).getIntroduce());
        return view;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView01;
        TextView textView03;
    }

}
