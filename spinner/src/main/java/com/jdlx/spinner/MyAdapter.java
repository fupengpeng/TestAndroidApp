package com.jdlx.spinner;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;




/**
 * 自定义的适配器类
 * @author hbj403
 */
public class MyAdapter extends BaseAdapter {

    private List<Person> mList;
    private Context mContext;

    public MyAdapter(Context pContext,List<Person> pList) {
        this.mContext=pContext;
        this.mList=pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //最主要代码
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
        convertView=_LayoutInflater.inflate(R.layout.me_layout, null);

        if(convertView!=null){
            TextView _TextView1=(TextView)convertView.findViewById(R.id.textView1);
            TextView _TextView2=(TextView)convertView.findViewById(R.id.textView2);
            _TextView1.setText(mList.get(position).getPersonName());
            _TextView2.setText(mList.get(position).getPersonAddress());
        }
        return convertView;
    }
}