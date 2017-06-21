package com.fupengpeng.selectall.selectallperfect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.fupengpeng.selectall.R;
import com.fupengpeng.selectall.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class PerfectSelectAllActivity extends AppCompatActivity
        implements View.OnClickListener ,
        CompoundButton.OnCheckedChangeListener,
        AdapterView.OnItemClickListener {

    private static final String TAG = "PerfectSelectAllActivity";
    private TextView tv = null;
    private ListView lv = null;
    private String name[] = { "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9",
            "G10", "G11", "G12", "G13", "G14",
            "G15", "G16", "G17", "G18", "G19", "G20", "G21", "G22", "G23",  "G24", "G25"};

    private List<Item> list;
    private List<String> data;
    private PerfectSelectAllAdapter adapter;
    private CheckBox checkBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_select_all);
        initView();
        init();

    }

    private void initView() {

        Log.e(TAG, "initView: "+"----001----" );
        tv = (TextView) this.findViewById(R.id.tv);

        lv = (ListView) this.findViewById(R.id.lv);
        lv.setOnItemClickListener(this);

        checkBox = (CheckBox) findViewById(R.id.selectall);
        checkBox.setOnCheckedChangeListener(this);

        this.findViewById(R.id.inverseselect).setOnClickListener(this);
        this.findViewById(R.id.cancel).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inverseselect:
                Log.e(TAG, "onClick: "+"----006----" );
                for (Item item : list) {
                    item.setB(!item.isB());// 取反
                }


                break;
            case R.id.cancel:
                Log.e(TAG, "onClick: "+"----007----" );
                for (Item item : list) {
                    item.setB(false);
                }
                break;
        }
        initAdapter();
    }

    // 数据初始化
    private void init() {

        Log.e(TAG, "init: "+"----002----" );
        list = new ArrayList<Item>();
        if (data == null)
            data = new ArrayList<String>();
        for (String s : name) {
            list.add(new Item(s, false));
        }
        initAdapter();
    }

    // 刷新适配器
    public void initAdapter() {
        Log.e(TAG, "initAdapter: "+"----003----" );
        if (adapter == null) {
            adapter = new PerfectSelectAllAdapter(this,list);
            lv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        data.clear();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isB())
                // count++;
                data.add(name[i]);
            else
                data.remove(name[i]);
        }
        // tv.setText("已选中 "+count+" 项");
        tv.setText("已选中 " + data.size() + " 项");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.selectall:

                Log.e(TAG, "onCheckedChanged: "+"----005----" );
                if (isChecked){
                    for (Item item : list) {
                        item.setB(true);
                    }
                }else {
                    for (Item item : list) {
                        item.setB(false);
                    }
                }



                initAdapter();
                break;

            case R.id.item_cb:

                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.e(TAG, "onItemClick: "+"----004----" );
        Item item = list.get(position);
        item.setB(!item.isB());// 取反

        for (int i = 0; i < list.size(); i++) {

        }

        initAdapter();
    }
}
