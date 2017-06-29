package com.fupengpeng.shopcartselect.shopping01.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.fupengpeng.shopcartselect.template.DataBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 购物车Fragment
 */
public class ShoppingCartFragment extends Fragment
        implements View.OnClickListener
{




    @BindView(R.id.tv_title_activity_right)
    TextView tvTitleActivityRight;
    /**
     * 购物车商品列表
     */
    @BindView(R.id.lv_fragment_shopping_cart)
    ListView lvFragmentShoppingCart;
    /**
     * 购物车全选按钮
     */
    @BindView(R.id.cb_fragment_shopping_cart_select_all)
    CheckBox cbFragmentShoppingCartSelectAll;
    /**
     * 已选商品合计
     */
    @BindView(R.id.tv_fragment_shopping_cart_total)
    TextView tvFragmentShoppingCartTotal;
    /**
     * 已选商品总计
     */
    @BindView(R.id.tv_fragment_shopping_cart_amount)
    TextView tvFragmentShoppingCartAmount;
    /**
     * 结算
     */
    @BindView(R.id.btn_fragment_shopping_cart_settlement)
    Button btnFragmentShoppingCartSettlement;

    Unbinder unbinder;

    /**
     * 价格合计
     */
    @BindView(R.id.ll_fragment_shopping_cart_price_total)
    LinearLayout llFragmentShoppingCartPriceTotal;
    /**
     * Fragment布局
     */
    private View shoppingCartFragmentView;

    private static final String TAG = "ShoppingCartFragment";
    /**
     * 上下文
     */
    protected Context context = null;
    /**
     * 依附的MainActivity
     */
    protected Activity mainActivity = null;
    /**
     * 结算数量
     */
    private int number;

    /**
     * 获取Fragment依赖的MainActivity
     *
     * @return
     */
    public Activity getMainActivity() {
        mainActivity =  getActivity();
        context = mainActivity;
        return mainActivity;
    }

    private ShoppingCartFragmentAdapter shoppingCartFragmentAdapter;// adapter

    private List<DataBean> mListData = new ArrayList<DataBean>();// 数据

    private boolean isBatchModel;// 是否可删除模式

    private double totalPrice = 0; // 商品总价
    /**
     * 批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();

    boolean isSelect = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        shoppingCartFragmentView = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        unbinder = ButterKnife.bind(this, shoppingCartFragmentView);
        getActivity();
        initView();
        initListener();
        loadData();
        refreshListView();
//        EventBus.getDefault().register(this);//订阅


        return shoppingCartFragmentView;
    }


    private void initView() {
        Log.e(TAG, "initView: "+"----0001----" );
        // TODO: 2017/6/22 0022
        tvTitleActivityRight.setVisibility(View.VISIBLE);
        tvTitleActivityRight.setText("编辑");
        lvFragmentShoppingCart.setSelector(R.drawable.list_selector);
    }

    private void initListener() {
        Log.e(TAG, "initListener: "+"----0002----" );
        tvTitleActivityRight.setOnClickListener(this);
        btnFragmentShoppingCartSettlement.setOnClickListener(this);
        cbFragmentShoppingCartSelectAll.setOnClickListener(this);
    }

    private void loadData() {
        Log.e(TAG, "loadData: "+"----0003----");
        mListData = getData();

//        Log.e(TAG, "loadData:=======------------ "+isBatchModel );
//        ShoppingCartFragmentEvent shoppingCartFragmentEvent =
//                new ShoppingCartFragmentEvent(
//                        isBatchModel,
//                        btnFragmentShoppingCartSettlement,
//                        llFragmentShoppingCartPriceTotal,
//                        totalPrice,
//                        tvFragmentShoppingCartTotal);
//
//        EventBus.getDefault().post(shoppingCartFragmentEvent);

//        EventBus.getDefault().postSticky(shoppingCartFragmentEvent);//发送一个粘性事件

    }

    private void refreshListView() {
        Log.e(TAG, "refreshListView: "+"----0004----" );
        if (shoppingCartFragmentAdapter == null) {
            shoppingCartFragmentAdapter = new ShoppingCartFragmentAdapter(getMainActivity(),mListData);
            lvFragmentShoppingCart.setAdapter(shoppingCartFragmentAdapter);
            lvFragmentShoppingCart.setOnItemClickListener(shoppingCartFragmentAdapter);
        } else {
            shoppingCartFragmentAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取商品数据
     */
    private List<DataBean> getData() {
        Log.e(TAG, "getData: "+"----00005----" );
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.tv_title_activity_right:

                Log.e(TAG, "onClick: "+"----0008----" );
                isBatchModel = !isBatchModel;
                if (isBatchModel) {
                    tvTitleActivityRight.setText(getResources().getString(R.string.menu_enter));
                    btnFragmentShoppingCartSettlement.setText(getResources().getString(R.string.menu_del));
                    llFragmentShoppingCartPriceTotal.setVisibility(View.GONE);

                } else {
                    tvTitleActivityRight.setText(getResources().getString(R.string.menu_edit));

                    llFragmentShoppingCartPriceTotal.setVisibility(View.VISIBLE);
                    btnFragmentShoppingCartSettlement.setText(getResources().getString(R.string.menu_sett));
                    totalPrice = 0;
                    tvFragmentShoppingCartTotal.setText("￥" + totalPrice);

                }

                break;

            case R.id.cb_fragment_shopping_cart_select_all:
                Log.e(TAG, "onClick: "+"----0009----" );
                totalPrice = 0;

                if (cbFragmentShoppingCartSelectAll.isChecked()) {
                    for (int i = 0; i < mListData.size(); i++) {
                        mListData.get(i).setChoose(true);
                        // 如果为选中
                        if (mListData.get(i).isChoose()) {
                            totalPrice = totalPrice + mListData.get(i).getCarNum()
                                    * mListData.get(i).getPrice();
                            number = number + mListData.get(i).getCarNum();
                        }
                    }

                    // 刷新
                    shoppingCartFragmentAdapter.notifyDataSetChanged();
                    btnFragmentShoppingCartSettlement.setText("去结算（"+number+")");
                    // 显示
                    tvFragmentShoppingCartTotal.setText(totalPrice + "元");
                } else {
                    for (int i = 0; i < mListData.size(); i++) {
                        mListData.get(i).setChoose(false);

                        // 刷新
                        shoppingCartFragmentAdapter.notifyDataSetChanged();
                    }
                    tvFragmentShoppingCartTotal.setText(totalPrice + "元");
                }
                break;

            case R.id.btn_fragment_shopping_cart_settlement:

                Log.e(TAG, "onClick: "+"----0010----" );






                if (isBatchModel) {
                    Log.e(TAG, "onClick: "+isBatchModel+"-----------------------" );
                    Iterator it = mListData.iterator();
                    while (it.hasNext()) {
                        // 得到对应集合元素
                        DataBean g = (DataBean) it.next();
                        // 判断
                        if (g.isChoose()) {
                            // 从集合中删除上一次next方法返回的元素
                            it.remove();
                        }
                    }

                    // 刷新
                    shoppingCartFragmentAdapter.notifyDataSetChanged();

                } else {
                    if (totalPrice != 0) {
                        Toast.makeText(getMainActivity(), "跳转至结算界面",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getMainActivity(), "请选择要支付的商品",
                                Toast.LENGTH_SHORT).show();
                        shoppingCartFragmentAdapter.notifyDataSetChanged();
                        return;
                    }
                }

                break;
            default:
                break;
        }
    }
//    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
//    public void onDataSynEvent(ShoppingCartFragmentEvent shoppingCartFragmentEvent) {
//        isBatchModel = shoppingCartFragmentEvent.isBatchModel();
//        Log.e(TAG, "onDataSynEvent: ============"+isBatchModel );
//    }

    class ShoppingCartFragmentAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

        private LayoutInflater mInflater = null;
        private Context context;
        private List<DataBean> mListData;

        public ShoppingCartFragmentAdapter(Context context, List<DataBean> mListData) {
            //根据context上下文加载布局，这里的是Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
            this.context = context;
            this.mListData = mListData;
        }


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
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_fragment_shopping_cart_commodity_list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final DataBean data = mListData.get(position);
            bindListItem(holder, data);

            if (data != null) {
                // 判断是否选择
                if (data.isChoose()) {
                    holder.cbItemFragmentShoppingCartCommodityListSelect.setChecked(true);
                } else {
                    holder.cbItemFragmentShoppingCartCommodityListSelect.setChecked(false);
                }

                // 选中操作
                holder.cbItemFragmentShoppingCartCommodityListSelect.setOnClickListener(new CheckBoxOnClick(data));
                // 减少操作
                holder.tvItemFragmentShoppingCartCommodityListNumberReduce.setOnClickListener(new ReduceOnClick(data,
                        holder.tvItemFragmentShoppingCartCommodityListNumber));

                // 增加操作
                holder.tvItemFragmentShoppingCartCommodityListNumberAdd.setOnClickListener(new AddOnclick(data,
                        holder.tvItemFragmentShoppingCartCommodityListNumber));

            }
            return convertView;
        }

        class CheckBoxOnClick implements View.OnClickListener {
            DataBean shopcartEntity;

            public CheckBoxOnClick(DataBean shopcartEntity) {
                this.shopcartEntity = shopcartEntity;
            }

            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: "+"----0011----" );
                CheckBox cb = (CheckBox) view;
                if (cb.isChecked()) {
                    shopcartEntity.setChoose(true);
                } else {
                    shopcartEntity.setChoose(false);
                }
                count();
                select();

            }

        }

        private class AddOnclick implements View.OnClickListener {
            DataBean shopcartEntity;
            TextView shopcart_number_btn;

            private AddOnclick(DataBean shopcartEntity,
                               TextView shopcart_number_btn) {
                this.shopcartEntity = shopcartEntity;
                this.shopcart_number_btn = shopcart_number_btn;
            }

            @Override
            public void onClick(View arg0) {
                Log.e(TAG, "onClick: "+"----0014----" );
                shopcartEntity.setChoose(true);
                String numberStr = shopcart_number_btn.getText().toString();
                if (!TextUtils.isEmpty(numberStr)) {
                    int number = Integer.parseInt(numberStr);

                    int currentNum = number + 1;
                    // 设置列表
                    shopcartEntity.setCarNum(currentNum);
                    holder.tvItemFragmentShoppingCartCommodityListNumber.setText("" + currentNum);
                    notifyDataSetChanged();
                }
                count();
            }

        }

        private class ReduceOnClick implements View.OnClickListener {
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
                Log.e(TAG, "onClick: "+"----0015----" );
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

                        holder.tvItemFragmentShoppingCartCommodityListNumber.setText("" + currentNum);
                        notifyDataSetChanged();

                    }

                }
                count();
            }

        }

        private void bindListItem(ViewHolder holder, DataBean data) {

            Log.e(TAG, "bindListItem: "+"----0016----" );

            holder.tvItemFragmentShoppingCartCommodityListName.setText(data.getContent());
            holder.tvItemFragmentShoppingCartCommodityListPrice.setText("￥" + data.getPrice());
            holder.tvItemFragmentShoppingCartCommodityListNumber.setText(data.getCarNum() + "");
            int _id = data.getId();

            boolean selected = mSelectState.get(_id, false);
            holder.cbItemFragmentShoppingCartCommodityListSelect.setChecked(selected);

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            DataBean bean = mListData.get(position);

            ViewHolder holder = (ViewHolder) view.getTag();
            int _id = (int) bean.getId();

            boolean selected = !mSelectState.get(_id, false);
            holder.cbItemFragmentShoppingCartCommodityListSelect.toggle();

            Log.e(TAG, "onItemClick: "+"----0017----" );
            // 将CheckBox的选中状况记录下来
            mListData.get(position).setChoose(holder.cbItemFragmentShoppingCartCommodityListSelect.isChecked());
            // 调整选定条目
            if (holder.cbItemFragmentShoppingCartCommodityListSelect.isChecked() == true) {
                totalPrice += bean.getCarNum() * bean.getPrice();
            } else {
                mSelectState.delete(position);
                totalPrice -= bean.getCarNum() * bean.getPrice();
            }
            tvFragmentShoppingCartTotal.setText("￥" + totalPrice + "");
            if (mSelectState.size() == mListData.size()) {
                cbFragmentShoppingCartSelectAll.setChecked(true);
            } else {
                cbFragmentShoppingCartSelectAll.setChecked(false);
            }

        }

        class ViewHolder {
            @BindView(R.id.cb_item_fragment_shopping_cart_commodity_list_select)
            CheckBox cbItemFragmentShoppingCartCommodityListSelect;
            @BindView(R.id.iv_item_fragment_shopping_cart_commodity_list_pic)
            ImageView ivItemFragmentShoppingCartCommodityListPic;
            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_name)
            TextView tvItemFragmentShoppingCartCommodityListName;
            @BindView(R.id.ll_item_fragment_shopping_cart_commodity_list_name)
            LinearLayout llItemFragmentShoppingCartCommodityListName;
            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_standard)
            TextView tvItemFragmentShoppingCartCommodityListStandard;
            @BindView(R.id.ll_item_fragment_shopping_cart_commodity_list_standard)
            LinearLayout llItemFragmentShoppingCartCommodityListStandard;
            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_price)
            TextView tvItemFragmentShoppingCartCommodityListPrice;
            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_number_reduce)
            TextView tvItemFragmentShoppingCartCommodityListNumberReduce;
            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_number)
            TextView tvItemFragmentShoppingCartCommodityListNumber;
            @BindView(R.id.tv_item_fragment_shopping_cart_commodity_list_number_add)
            TextView tvItemFragmentShoppingCartCommodityListNumberAdd;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }


        }
    }

    /**
     * 计算价格
     */
    public void count() {
        Log.e(TAG, "count: "+"----0012----" );
        totalPrice = 0;// 人民币
        number = 0;
        if (mListData != null && mListData.size() > 0) {
            for (int i = 0; i < mListData.size(); i++) {
                if (mListData.get(i).isChoose()) {

                    Log.e(TAG, "count: "+"----0012--前--"+ totalPrice );
                    totalPrice =                              //商品合计价格
                            totalPrice +                      //前面的总价
                                    mListData.get(i).getCarNum()      //当前商品的数量
                                            * mListData.get(i).getPrice();    //当前商品的价格
                    Log.e(TAG, "count: "+"----0012--后--"+ totalPrice );

                    number = number + mListData.get(i).getCarNum();

                    Log.e(TAG, "count: "+"----0012----number = "+ number);


                }
            }
            //设置显示当前选定商品合计价格
            tvFragmentShoppingCartTotal.setText("￥" + totalPrice + "");

            btnFragmentShoppingCartSettlement.setText("去结算（"+ number +")");


        }

    }

    public void select() {
        Log.e(TAG, "select: "+"----0013----" );
        int count = 0;                                         //商品数量
        for (int i = 0; i < mListData.size(); i++) {
            if (mListData.get(i).isChoose()) {
                count++;
                Log.e(TAG, "select: "+"----0013----"+count );
            }
        }
        if (count == mListData.size()) {                       //选定商品数量等于所有数据数量
            Log.e(TAG, "select: "+"----0013--if==true--"+count );
            cbFragmentShoppingCartSelectAll.setChecked(true);  //设置全选按钮为选定状态
        } else {
            isSelect = true;                                   //选定商品数量不等于所有数据数量
            Log.e(TAG, "select: "+"----0013--if==false--"+count );
            cbFragmentShoppingCartSelectAll.setChecked(false); //设置当前条目为选定状态，全选为未选定状态
        }

    }




}
