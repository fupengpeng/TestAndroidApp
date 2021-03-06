package com.fupengpeng.shopcartselect.test;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.fupengpeng.shopcartselect.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener {

    private static final int INITIALIZE = 0;

    private ListView mListView;// 列表

    private ListAdapter mListAdapter;// adapter

    private List<DataBean> mListData = new ArrayList<DataBean>();// 数据

    private boolean isBatchModel;// 是否可删除模式

    private RelativeLayout mBottonLayout;
    private CheckBox mCheckAll; // 全选 全不选
    private TextView mEdit; // 切换到删除模式

    private TextView mPriceAll; // 商品总价

    // private TextView mSelectNum; // 选中数量

    private TextView mFavorite; // 移到收藏夹,分享

    private Button mDelete; // 删除 结算

    private double totalPrice = 0; // 商品总价

    private int totaNum = 0;
    /** 批量模式下，用来记录当前选中状态 */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();
    private ImageView back;
    private boolean flag = true; // 全选或全取消

    boolean isSelect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        loadData();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);

        mBottonLayout = (RelativeLayout) findViewById(R.id.cart_rl_allprie_total);
        mCheckAll = (CheckBox) findViewById(R.id.cb_fragment_shopping_cart_select_all);
        mEdit = (TextView) findViewById(R.id.tv_title_activity_right);
        mPriceAll = (TextView) findViewById(R.id.tv_fragment_shopping_cart_total);
        // mSelectNum = (TextView) findViewById(R.id.tv_cart_select_num);
        mFavorite = (TextView) findViewById(R.id.tv_cart_move_favorite);
        mDelete = (Button) findViewById(R.id.btn_fragment_shopping_cart_settlement);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setSelector(R.drawable.list_selector);

    }

    private void initListener() {
        mEdit.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mCheckAll.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void loadData() {
//        new LoadDataTask().execute(new Params(INITIALIZE));
        mListData = getData();
        refreshListView();
    }

    private void refreshListView() {
        if (mListAdapter == null) {
            mListAdapter = new ListAdapter();
            mListView.setAdapter(mListAdapter);
            mListView.setOnItemClickListener(mListAdapter);

        } else {
            mListAdapter.notifyDataSetChanged();

        }
    }

    private List<DataBean> getData() {
        int maxId = 0;
        if (mListData != null && mListData.size() > 0)
            maxId = mListData.get(mListData.size() - 1).getId();
        List<DataBean> result = new ArrayList<DataBean>();
        DataBean data = null;
        for (int i = 0; i < 20; i++) {
            data = new DataBean();
            data.setId(maxId + i + 1);// 从最大Id的下一个开始
            data.setShopName("我的" + (maxId + 1 + i) + "店铺");
            data.setContent("我的购物车里面的第" + (maxId + 1 + i) + "个商品");
            data.setCarNum(1);
            data.setPrice(305.00);
            result.add(data);
        }
        return result;
    }

//    class Params {
//        int op;
//
//        public Params(int op) {
//            this.op = op;
//        }
//
//    }
//
//    class Result {
//        int op;
//        List<DataBean> list;
//    }
//
//    private class LoadDataTask extends AsyncTask<Params, Void, Result> {
//        @Override
//        protected Result doInBackground(Params... params) {
//            Params p = params[0];
//            Result result = new Result();
//            result.op = p.op;
//            try {// 模拟耗时
//                Thread.sleep(500L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            result.list = getData();
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(Result result) {
//            super.onPostExecute(result);
//            if (result.op == INITIALIZE) {
//                mListData = result.list;
//            } else {
//                mListData.addAll(result.list);
//                Toast.makeText(getApplicationContext(), "添加成功！",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//
//        }
//
//    }



    private class ListAdapter extends BaseAdapter implements
            OnItemClickListener {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        ViewHolder holder = null;

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                holder = new ViewHolder();
                view = LayoutInflater.from(MainActivity.this).inflate(
                        R.layout.cart_list_item, null);
                holder.checkBox = (CheckBox) view.findViewById(R.id.cb_item_fragment_shopping_cart_commodity_list_select);
                // shopName = (TextView) view.findViewById(R.id.tv_source_name);
                holder.image = (ImageView) view
                        .findViewById(R.id.iv_adapter_list_pic);
                holder.content = (TextView) view.findViewById(R.id.tv_item_fragment_shopping_cart_commodity_list_name);
                holder.carNum = (TextView) view.findViewById(R.id.tv_item_fragment_shopping_cart_commodity_list_number);
                holder.price = (TextView) view.findViewById(R.id.tv_item_fragment_shopping_cart_commodity_list_price);
                holder.add = (TextView) view.findViewById(R.id.tv_item_fragment_shopping_cart_commodity_list_number_add);
                holder.red = (TextView) view.findViewById(R.id.tv_item_fragment_shopping_cart_commodity_list_number_reduce);
                holder.button = (Button) view.findViewById(R.id.btn_delete);
                holder.frontView = view.findViewById(R.id.item_left);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            final DataBean data = mListData.get(position);
            bindListItem(holder, data);

            if (data != null) {
                // 判断是否选择
                if (data.isChoose()) {
                    holder.checkBox.setChecked(true);
                } else {
                    holder.checkBox.setChecked(false);
                }

                // 选中操作
                holder.checkBox.setOnClickListener(new CheckBoxOnClick(data));
                // 减少操作
                holder.red.setOnClickListener(new ReduceOnClick(data,
                        holder.carNum));

                // 增加操作
                holder.add.setOnClickListener(new AddOnclick(data,
                        holder.carNum));

            }
            return view;
        }

        class CheckBoxOnClick implements OnClickListener {
            DataBean shopcartEntity;

            public CheckBoxOnClick(DataBean shopcartEntity) {
                this.shopcartEntity = shopcartEntity;
            }

            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                if (cb.isChecked()) {
                    shopcartEntity.setChoose(true);
                } else {
                    shopcartEntity.setChoose(false);
                }
                if (isBatchModel){
                    number();
                }else {
                    count();
                }

                select();



            }

        }

        private class AddOnclick implements OnClickListener {
            DataBean shopcartEntity;
            TextView shopcart_number_btn;

            private AddOnclick(DataBean shopcartEntity,
                               TextView shopcart_number_btn) {
                this.shopcartEntity = shopcartEntity;
                this.shopcart_number_btn = shopcart_number_btn;
            }

            @Override
            public void onClick(View arg0) {
                shopcartEntity.setChoose(true);
                String numberStr = shopcart_number_btn.getText().toString();
                if (!TextUtils.isEmpty(numberStr)) {
                    int number = Integer.parseInt(numberStr);

                    int currentNum = number + 1;
                    // 设置列表
                    shopcartEntity.setCarNum(currentNum);
                    holder.carNum.setText("" + currentNum);
                    notifyDataSetChanged();
                }
                count();
            }

        }

        private class ReduceOnClick implements OnClickListener {
            DataBean shopcartEntity;
            TextView shopcart_number_btn;

            private ReduceOnClick(DataBean shopcartEntity,
                                  TextView shopcart_number_btn) {
                this.shopcartEntity = shopcartEntity;
                this.shopcart_number_btn = shopcart_number_btn;
            }

            @Override
            public void onClick(View arg0) {
                shopcartEntity.setChoose(true);
                String numberStr = shopcart_number_btn.getText().toString();
                if (!TextUtils.isEmpty(numberStr)) {
                    int number = Integer.parseInt(numberStr);
                    if (number == 1) {
                        // Toast.makeText(CartListActivity.this, "不能往下减少了",
                        // Toast.LENGTH_SHORT).show();
                    } else {
                        int currentNum = number - 1;
                        // 设置列表
                        shopcartEntity.setCarNum(currentNum);

                        holder.carNum.setText("" + currentNum);
                        notifyDataSetChanged();

                    }

                }
                count();
            }

        }

        private void bindListItem(ViewHolder holder, DataBean data) {

            // holder.shopName.setText(data.getShopName());
            holder.content.setText(data.getContent());
            holder.price.setText("￥" + data.getPrice());
            holder.carNum.setText(data.getCarNum() + "");
            int _id = data.getId();

            boolean selected = mSelectState.get(_id, false);
            holder.checkBox.setChecked(selected);

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            DataBean bean = mListData.get(position);

            ViewHolder holder = (ViewHolder) view.getTag();
            int _id = (int) bean.getId();

            boolean selected = !mSelectState.get(_id, false);
            holder.checkBox.toggle();

            // 将CheckBox的选中状况记录下来
            mListData.get(position).setChoose(holder.checkBox.isChecked());
            // 调整选定条目
            if (holder.checkBox.isChecked() == true) {
                totalPrice += bean.getCarNum() * bean.getPrice();

                totaNum += bean.getCarNum();

            } else {
                mSelectState.delete(position);
                totalPrice -= bean.getCarNum() * bean.getPrice();

                totaNum += bean.getCarNum();
            }
            mPriceAll.setText("￥" + totalPrice + "");

            mDelete.setText("结算（"+totaNum+"）");

            if (mSelectState.size() == mListData.size()) {
                mCheckAll.setChecked(true);
            } else {
                mCheckAll.setChecked(false);
            }

        }

        class ViewHolder {
            CheckBox checkBox;

            ImageView image;
            TextView shopName;
            TextView content;
            TextView carNum;
            TextView price;
            TextView add;
            TextView red;
            Button button; // 用于执行删除的button
            View frontView;
            LinearLayout item_right, item_left;

        }

    }



    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.tv_title_activity_right:
                isBatchModel = !isBatchModel;
                if (isBatchModel) {
                    mEdit.setText(getResources().getString(R.string.menu_enter));
                    mDelete.setText(getResources().getString(R.string.menu_del));
                    mBottonLayout.setVisibility(View.GONE);
                    mFavorite.setVisibility(View.VISIBLE);

                    number();

                } else {
                    mEdit.setText(getResources().getString(R.string.menu_edit));

                    mFavorite.setVisibility(View.GONE);
                    mBottonLayout.setVisibility(View.VISIBLE);
                    mDelete.setText(getResources().getString(R.string.menu_sett));
                    count();

                }

                break;

            case R.id.cb_fragment_shopping_cart_select_all:
                totalPrice = 0;

                totaNum = 0;
                if (mCheckAll.isChecked()) {
                    for (int i = 0; i < mListData.size(); i++) {
                        mListData.get(i).setChoose(true);
                        // 如果为选中
                        if (mListData.get(i).isChoose()) {
                            totalPrice = totalPrice + mListData.get(i).getCarNum()
                                    * mListData.get(i).getPrice();

                            totaNum = totaNum + mListData.get(i).getCarNum();

                        }
                    }

                    // 刷新
                    mListAdapter.notifyDataSetChanged();
                    // 显示
                    mPriceAll.setText(totalPrice + "元");


                    if (isBatchModel){
                        mDelete.setText("删除（"+totaNum+"）");
                    }else {
                        mDelete.setText("结算（"+totaNum+"）");
                    }
                } else {
                    for (int i = 0; i < mListData.size(); i++) {
                        mListData.get(i).setChoose(false);

                        // // 刷新
                        mListAdapter.notifyDataSetChanged();
                    }
                    mPriceAll.setText(totalPrice + "元");

                    if (isBatchModel){
                        mDelete.setText("删除（"+totaNum+"）");
                    }else {
                        mDelete.setText("结算（"+totaNum+"）");
                    }
                }
                break;

            case R.id.btn_fragment_shopping_cart_settlement:

                if (isBatchModel) {

                    Iterator it = mListData.iterator();
                    while (it.hasNext()) {
                        // 得到对应集合元素
                        DataBean g = (DataBean) it.next();
                        // 判断
                        if (g.isChoose()) {
                            // 从集合中删除上一次next方法返回的元素
                            it.remove();
                            number();
                        }
                    }

                    // 刷新
                    mListAdapter.notifyDataSetChanged();

                } else {
                    if (totalPrice != 0) {
                        //
                        // Intent intent = new Intent();
                        // intent.setClass(MainActivity.this,
                        // OrderFillActivity.class);
                        // startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "请选择要支付的商品",
                                Toast.LENGTH_SHORT).show();
                        mListAdapter.notifyDataSetChanged();
                        return;
                    }
                }

                break;
            case R.id.back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    /**
     * 计算价格
     */
    public void count() {

        totalPrice = 0;// 人民币

        totaNum = 0;
        if (mListData != null && mListData.size() > 0) {
            for (int i = 0; i < mListData.size(); i++) {
                if (mListData.get(i).isChoose()) {

                    totalPrice = totalPrice + mListData.get(i).getCarNum()
                            * mListData.get(i).getPrice();

                    totaNum = totaNum + mListData.get(i).getCarNum();


                }
            }
            mPriceAll.setText("￥" + totalPrice + "");

            mDelete.setText("结算（"+totaNum+"）");

        }

    }

    /**
     * 计算数量
     */
    public void number() {

        totalPrice = 0;// 人民币
        totaNum = 0;
        if (mListData != null && mListData.size() > 0) {
            for (int i = 0; i < mListData.size(); i++) {
                if (mListData.get(i).isChoose()) {


                    totalPrice = totalPrice + mListData.get(i).getCarNum()
                            * mListData.get(i).getPrice();
                    totaNum = totaNum + mListData.get(i).getCarNum();

                }
            }

            mPriceAll.setText("￥" + totalPrice + "");

            mDelete.setText("删除（"+totaNum+"）");
        }

    }



    public void select() {
        int count = 0;
        for (int i = 0; i < mListData.size(); i++) {
            if (mListData.get(i).isChoose()) {
                count++;
            }
        }
        if (count == mListData.size()) {
            mCheckAll.setChecked(true);
        } else {
            isSelect = true;
            mCheckAll.setChecked(false);
        }

    }

}