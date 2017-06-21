package com.fupengpeng.selectall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.fupengpeng.selectall.entity.CommodityObject;

import java.util.ArrayList;
import java.util.List;

public class SelectAllActivity extends AppCompatActivity implements View.OnClickListener ,CompoundButton.OnCheckedChangeListener ,AdapterView.OnItemClickListener {
    TextView tv = null;
    ListView lv = null;

    public static final String TAG = "SelectAllActivity";

    private List<String> data;


    private List<CommodityObject> list;
    private SelectAllAdapter selectAllAdapter;
    private String name[] = { "G1", "G2", "G3", "G4", "G5", "G6",
            "G7", "G8", "G9", "G10", "G11", "G12", "G13", "G14","G15" };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_all);


        tv = (TextView) this.findViewById(R.id.tv);
        lv = (ListView) this.findViewById(R.id.lv);

        this.findViewById(R.id.selectall).setOnClickListener(this);
        this.findViewById(R.id.inverseselect).setOnClickListener(this);
        this.findViewById(R.id.cancel).setOnClickListener(this);

        lv.setOnItemClickListener(this);

        init();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.selectall:
                int size1 = list.size();
                for(int i=0;i<size1;i++){
                    list.get(i).setDesignate(true);
                }
                Log.e(TAG, "onClick: "+"onclick"+"----001----" );
                break;
            case R.id.inverseselect:
                int size2 = list.size();
                for(int i=0;i<size2;i++){
                    CommodityObject commodityObject = list.get(i);
                    commodityObject.setDesignate(!commodityObject.isDesignate());//取反
                }
                Log.e(TAG, "onClick: "+"onclick"+"----002----" );
                break;
            case R.id.cancel:
                int size3 = list.size();
                for(int i=0;i<size3;i++){
                    list.get(i).setDesignate(false);
                }
                Log.e(TAG, "onClick: "+"onclick"+"----003----" );
                break;
        }
        initAdapter();
    }

    //数据初始化
    private void init(){

        if(data==null){
            data = new ArrayList<String>();

//            name = new String[list.size()];

            for (int i = 0; i < name.length; i++) {
                data.add(name[i]);
            }
        }

        if (list == null){
            list = new ArrayList<CommodityObject>();
        }else {
            list.clear();
        }
        for (int i = 0; i <15; i++) {
            CommodityObject commodityObject = new CommodityObject();
            commodityObject.setDesignate(false);
            commodityObject.setCommodityPic(R.drawable.nvshengtouxiang);
            commodityObject.setName("商品"+"----"+i+"----");
            commodityObject.setPrice(25.8f);
            commodityObject.setNumber(1);
            list.add(commodityObject);
        }
        initAdapter();
    }



    //刷新适配器
    public void initAdapter() {
        Log.e(TAG, "initAdapter: "+"----004----" );
        if(selectAllAdapter == null){
            selectAllAdapter = new SelectAllAdapter(this,list);
            lv.setAdapter(selectAllAdapter);
        }else{
            selectAllAdapter.notifyDataSetChanged();
        }

        // TODO: 2017/6/21 0021 统计数量待实现

        int count = 0;
        int size = list.size();
//        data.clear();
        for(int i=0;i<size;i++){
            if(list.get(i).isDesignate())
                //count++;
                data.add(name[i]);

            else
                data.remove(name[i]);
        }
        //tv.setText("已选中 "+count+" 项");
        tv.setText("已选中 "+data.size()+" 项");
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.e(TAG, "onCheckedChanged: "+"----006----" );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemClick: "+"----005----" );
        CommodityObject commodityObject = list.get(position);
        commodityObject.setDesignate(!commodityObject.isDesignate()); //取反
        initAdapter();
    }
}
